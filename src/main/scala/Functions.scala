

import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.SparkContext
import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.MongoDBObject
import scala.util.Random

object Functions {
  def loadUserRatings(userId: Int, sc: SparkContext): RDD[Rating] = {
    val mongoRatesConnection = MongoClient();
    var mongoRatesColl = mongoRatesConnection("Acme-Supermarket")("rates");
    //Load my ratings
    val query = MongoDBObject("customer_id" -> userId);
    val fields = MongoDBObject("rate" -> 1, "product_id" -> 1);
    val rows = mongoRatesColl.find(query, fields).toSeq;
    
    val rows_rdd = sc.parallelize(rows);
    
    val ratings = rows_rdd.map { row =>
      Rating(Integer.parseInt(row.get("customer_id").toString()), 
              Integer.parseInt(row.get("product_id").toString()), 
              Integer.parseInt(row.get("rate").toString()));
    }.filter(_.rating > 0)
    if (ratings.isEmpty) {
      mongoRatesConnection.underlying.close();
      mongoRatesColl = null;
      sys.error("No ratings provided.")
    } else {
      mongoRatesConnection.underlying.close();
      mongoRatesColl = null;
      ratings
    }
  }
  
  def loadRatings(sc: SparkContext): RDD[(Long, Rating)] = {
    var r = Random;
    
    val mongoRatesConnection = MongoClient();
    var mongoRatesColl = mongoRatesConnection("Acme-Supermarket")("rates");
    //Load every other ratings
    val query = MongoDBObject.empty;
    val fields = MongoDBObject("rate" -> 1, "product_id" -> 1);
    val rows = mongoRatesColl.find(query, fields).toSeq;
    
    val rows_rdd = sc.parallelize(rows);
    
    val ratings = rows_rdd.map { row =>
      (r.nextLong() % 10, Rating(Integer.parseInt(row.get("customer_id").toString()), 
                                  Integer.parseInt(row.get("product_id").toString()), 
                                  Integer.parseInt(row.get("rate").toString())));
    }
    
    mongoRatesConnection.underlying.close();
    mongoRatesColl = null;
    ratings
  }
  
  def loadProducts(sc: SparkContext): Map[Int, String] = {
    val mongoProductConnection = MongoClient();
    var mongoProductColl = mongoProductConnection("Acme-Supermarket")("products");
    
    val query = MongoDBObject.empty;
    val fields = MongoDBObject("name" -> 1);
    
    val rows = mongoProductColl.find(query, fields).toSeq;
    
    val products = rows.map { row =>
      (Integer.parseInt(row.get("_id").toString()), row.get("name").toString())
    }.toMap
    
    mongoProductConnection.underlying.close();
    mongoProductColl = null;
    products
  }
  
  /** Compute RMSE (Root Mean Squared Error). */
  def computeRmse(model: MatrixFactorizationModel, data: RDD[Rating], n: Long): Double = {
    val predictions: RDD[Rating] = model.predict(data.map(x => (x.user, x.product)))
    val predictionsAndRatings = predictions.map(x => ((x.user, x.product), x.rating))
      .join(data.map(x => ((x.user, x.product), x.rating)))
      .values
    math.sqrt(predictionsAndRatings.map(x => (x._1 - x._2) * (x._1 - x._2)).reduce(_ + _) / n)
  }
  
  def predictForUser(model: MatrixFactorizationModel, userId: Int, sparkContext: SparkContext): RDD[Rating] = {
    val notRatedMovies = getNotRatedMoviesByUser(userId, sparkContext);
    val prediction = model.predict(notRatedMovies)
    
    prediction
  }
  
  def getNotRatedMoviesByUser(userId: Int, sparkContext: SparkContext): RDD[(Int, Int)] = {
    val allFilms = loadProducts(sparkContext)
    val ratedFilms = loadUserRatings(userId, sparkContext).map(_.product).collect().toSet
    
    val candidates = sparkContext.parallelize(allFilms.keys.filter(!ratedFilms.contains(_)).toSeq)
    
    val notRatedMovies = candidates.map((userId, _))
    notRatedMovies
  }
}