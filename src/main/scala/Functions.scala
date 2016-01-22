

import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.SparkContext
import com.mongodb.casbah.MongoClient
import com.mongodb.casbah.commons.MongoDBObject
import scala.util.Random
import breeze.macros.expand.args
import org.apache.spark.mllib.recommendation.ALS
import breeze.macros.expand.args

object Functions {
  def loadUserRatings(userId: Int, sc: SparkContext): RDD[Rating] = {
    val mongoRatesConnection = MongoClient();
    var mongoRatesColl = mongoRatesConnection("Acme-Supermarket")("rates");
    //Load my ratings
    val query = MongoDBObject("customer_id" -> userId);
    val fields = MongoDBObject("value" -> 1, "product_id" -> 1, "customer_id" -> 1);
    val rows = mongoRatesColl.find(query, fields).toSeq;
    
    val rows_rdd = sc.parallelize(rows);
    
    val ratings = rows_rdd.map { row =>
      Rating(Integer.parseInt(row.get("customer_id").toString()), 
              Integer.parseInt(row.get("product_id").toString()), 
              Integer.parseInt(row.get("value").toString()));
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
    val mongoRatesConnection = MongoClient();
    var mongoRatesColl = mongoRatesConnection("Acme-Supermarket")("rates");
    //Load every other ratings
    val query = MongoDBObject.empty;
    val fields = MongoDBObject("value" -> 1, "product_id" -> 1, "customer_id" -> 1);
    val rows = mongoRatesColl.find(query, fields).toSeq;
    
    val rows_rdd = sc.parallelize(rows);
    
    val ratings = rows_rdd.map { row =>
      var r = Random;
      (r.nextLong() % 10, Rating(Integer.parseInt(row.get("customer_id").toString()), 
                                  Integer.parseInt(row.get("product_id").toString()), 
                                  Integer.parseInt(row.get("value").toString())));
    }
    
    //mongoRatesConnection.underlying.close();
    //mongoRatesColl = null;
    
    return ratings;
  }
  
  def loadProducts(sc: SparkContext): Array[Int] = {
    val mongoProductConnection = MongoClient();
    var mongoProductColl = mongoProductConnection("Acme-Supermarket")("products");
    
    val query = MongoDBObject.empty;
    val fields = MongoDBObject("_id" -> 1);
    
    val rows = mongoProductColl.find(query, fields).toSeq;
    
    val products = rows.map { row =>
      (Integer.parseInt(row.get("_id").toString()))
    }.toArray;
    
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
    val notRatedProducts = getNotRatedProductsByUser(userId, sparkContext);
        
    val prediction = model.predict(notRatedProducts)
        
    prediction;
  }
  
  def getNotRatedProductsByUser(userId: Int, sparkContext: SparkContext): RDD[(Int, Int)] = {
    val allProducts = loadProducts(sparkContext)
    val ratedProducts = loadUserRatings(userId, sparkContext).map(_.product).collect().toSet
    
    val candidates = sparkContext.parallelize(allProducts.filter(!ratedProducts.contains(_)).toSeq)
    
    val notRatedProducts = candidates.map((userId, _))
    
    notRatedProducts;
  }
  
  def makeRecommendation(sc: SparkContext, user_id: Int) = {
    val configConnection = MongoClient();
    var configColl = configConnection("Acme-Supermarket-Recommendations")("config");
   
    val config = configColl.find().toSeq;
    
    if(config.length == 0) {
      println("No hay configuraciones en la BD. Ejecuta el sistema con la id de usuario -1 para poner configucaciones.");
      sys.exit(1);
    }
    
    val numPartitions = 6;
    val rank = config(0).get("rank").toString().toInt;
    val numIter = config(0).get("numIter").toString().toInt;
    
    val lambda = config(0).get("numPartitions").toString().toDouble;
    
    // load personal ratings
    val myRatingsRDD = Functions.loadUserRatings(user_id, sc)
    
    // load ratings and products
    val ratings = Functions.loadRatings(sc)
    
    val training = ratings.filter(x => x._1 <= 3)
      .values
      .union(myRatingsRDD)
      .repartition(numPartitions)
      .cache()
      
    val model = ALS.train(training, rank, numIter, lambda);
    
    val prediction = Functions.predictForUser(model, user_id, sc).collect().sortBy(_.rating).take(10)
    
    val predictionsConnection = MongoClient();
    var predictionsColl = predictionsConnection("Acme-Supermarket-Recommendations")("recommendations");
    
    prediction.foreach { rating =>
      val recommendation = MongoDBObject(
          "customer_id" -> rating.user,
          "product_id" -> rating.product,
          "rating" -> rating.rating
      );
      
      predictionsColl += recommendation;
    }
    
    predictionsConnection.underlying.close();
    predictionsColl = null;
    // clean up
  }
  
  def configureParameters(sc: SparkContext, user_id : Int) = {
    val configConnection = MongoClient();
    var configColl = configConnection("Acme-Supermarket-Recommendations")("config");
    
    // load personal ratings
    val myRatingsRDD = Functions.loadUserRatings(user_id, sc)
    
    // load ratings and products
    val ratings = Functions.loadRatings(sc)

    val numPartitions = 6;
    val training = ratings.filter(x => x._1 <= 3)
      .values
      .union(myRatingsRDD)
      .repartition(numPartitions)
      .cache()
    val validation = ratings.filter(x => x._1 > 3 && x._1 <= 4)
      .values
      .repartition(numPartitions)
      .cache()
    val test = ratings.filter(x => x._1 > 4).values.cache()
    
    val numValidation = validation.count()

    val ranks = List(8, 12)
    val lambdas = List(1.0, 10.0)
    val numIters = List(10, 20)
    
    var bestValidationRmse = Double.MaxValue
    var bestRank = 0
    var bestLambda = -1.0
    var bestNumIter = -1
    for (rank <- ranks; lambda <- lambdas; numIter <- numIters) {
      val model = ALS.train(training, rank, numIter, lambda);
      
      val validationRmse = Functions.computeRmse(model, validation, numValidation);
      
      if (validationRmse < bestValidationRmse) {
        bestValidationRmse = validationRmse
        bestRank = rank
        bestLambda = lambda
        bestNumIter = numIter
      }
    }
        
    val query = MongoDBObject.empty;
    val fields = MongoDBObject("_id" -> 1);
    val rows = configColl.find(query, fields).toSeq;
    
    val bestValues = MongoDBObject(
      "rank" -> bestRank,
      "numIter" -> bestNumIter,
      "lambda" -> bestLambda
    );
    if ( rows.length == 0 ){
      configColl += bestValues;
    } else {
      var conf_id = rows(0).get("_id").toString();
    
      val update_query = MongoDBObject("_id" -> conf_id);
      val update_fields = MongoDBObject(
        "$set" -> bestValues 
      );
      
      configColl.findAndModify(update_query, update_fields);
    }
    configConnection.underlying.close();
    configColl = null;
  }
}