

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
import com.mongodb.DBObject
import scala.collection.mutable.Seq

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
    
    //mongoRatesConnection.underlying.close();
    //mongoRatesColl = null;
    
    if (ratings.isEmpty) {
      sys.error("No ratings provided.");
    } else {
      return ratings;
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
      (r.nextInt(10).toLong, Rating(Integer.parseInt(row.get("customer_id").toString()), 
                                  Integer.parseInt(row.get("product_id").toString()), 
                                  Integer.parseInt(row.get("value").toString())));
    }
    
    return ratings;
  }
  
  def loadUserPurchases(userId: Int, sc: SparkContext): RDD[Rating] = {
    val mongoPurchasesConnection = MongoClient();
    var acmeSupermarketDB = mongoPurchasesConnection("Acme-Supermarket");
    
    //Load purchases
    val purchasesColl = acmeSupermarketDB("purchases");
    val purchasesQuery = MongoDBObject("customer_id" -> userId);
    val purchaseFields = MongoDBObject("_id" -> 1);
    val purchases = purchasesColl.find(purchasesQuery, purchaseFields).map(_.get("_id").toString().toInt).toArray;
    
    //Load provides from purchase lines
    val purchaseLineColl = acmeSupermarketDB("purchase_lines");
    val purchaseLineQuery = MongoDBObject("purchase_id" -> MongoDBObject("$in" -> purchases));
    val pruchaseLineFields = MongoDBObject("provide_id" -> 1);
    val provides = purchaseLineColl.find(purchaseLineQuery, pruchaseLineFields).map(_.get("provide_id").toString().toInt).toArray;
    
    //Load products from provides
    val providesColl = acmeSupermarketDB("provide");
    val providesQuery = MongoDBObject("_id" -> MongoDBObject("$in" -> provides));
    val providesFields = MongoDBObject("product_id" -> 1);
    val products = providesColl.find(providesQuery, providesFields).map(_.get("product_id").toString().toInt).toArray;
    
    val products_rdd = sc.parallelize(products);
    
    val purchased_products = products_rdd.map { purchase =>
      Rating(userId, 
             purchase, 
             5);
    }
    mongoPurchasesConnection.underlying.close();
    
    if (purchased_products.isEmpty) {
      sys.error("No purchases provided.")
    } else {
      return purchased_products;
    }
  }
  
  def loadPurchases(sc: SparkContext): RDD[(Long, Rating)] = {
    val mongoPurchasesConnection = MongoClient();
    var acmeSupermarketDB = mongoPurchasesConnection("Acme-Supermarket");
    
    //Load purchases
    val purchasesColl = acmeSupermarketDB("purchases");
    val purchasesQuery = MongoDBObject.empty;
    val purchaseFields = MongoDBObject("customer_id" -> 1);
    var purchases = scala.collection.mutable.Map[Int, Array[Int]]();
    
    purchasesColl.find(purchasesQuery, purchaseFields).foreach{ purchase =>
        val customer_id = purchase.get("customer_id").toString().toInt;
        val purchase_id = purchase.get("_id").toString().toInt;
        
        //Load provides from purchase lines
        val purchaseLineColl = acmeSupermarketDB("purchase_lines");
        val purchaseLineQuery = MongoDBObject("purchase_id" -> purchase_id);
        val pruchaseLineFields = MongoDBObject("provide_id" -> 1);
        
        purchaseLineColl.find(purchaseLineQuery, pruchaseLineFields).foreach{ purchaseLine => 
          val provide_id = purchaseLine.get("provide_id").toString().toInt;
          
          val providesColl = acmeSupermarketDB("provide");
          val providesQuery = MongoDBObject("_id" -> provide_id);
          val providesFields = MongoDBObject("product_id" -> 1);
              
          val provide = providesColl.findOne(providesQuery, providesFields).get;
          val product_id = provide.get("product_id").toString().toInt;
          
          if(purchases.contains(customer_id) && !purchases.get(customer_id).get.contains(product_id)) {
            val new_array = purchases.get(customer_id).get :+ product_id;
            
            purchases.put(customer_id, new_array);
          } else {
            purchases.put(customer_id, Array(product_id));
          }
        };
    };
    
    val customers = sc.parallelize(purchases.keys.toSeq);
        
    val final_purchases = customers.map { customer =>
      var r = Random;
      val purchase = purchases.get(customer).get.map{ product => 
        (r.nextInt(10).toLong, Rating(customer, product, 5));  
      }
      
      purchase;
    }.flatMap(array => array)
    
    println("Purchases: " + final_purchases.count());
    
    return final_purchases;
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
  
  def predictRatesForUser(model: MatrixFactorizationModel, userId: Int, sparkContext: SparkContext): RDD[Rating] = {
    val notRatedProducts = getNotRatedProductsByUser(userId, sparkContext);
        
    val prediction = model.predict(notRatedProducts)
        
    prediction;
  }
  
  def predictPurchasesForUser(model: MatrixFactorizationModel, userId: Int, sparkContext: SparkContext): RDD[Rating] = {
    val notRatedProducts = getNotPurchasedProductsByUser(userId, sparkContext);
        
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
  
  def getNotPurchasedProductsByUser(userId: Int, sparkContext: SparkContext): RDD[(Int, Int)] = {
    val allProducts = loadProducts(sparkContext)
    val purchasedProducts = loadUserPurchases(userId, sparkContext).map(_.product).collect().toSet
    
    val candidates = sparkContext.parallelize(allProducts.filter(!purchasedProducts.contains(_)).toSeq)
    
    val notPurchasedProducts = candidates.map((userId, _))
    
    return notPurchasedProducts;
  }
  
  def makePurchasesRecommendation(sc: SparkContext, user_id: Int) = {
    val configConnection = MongoClient();
    var configColl = configConnection("Acme-Supermarket-Recommendations")("config_purchases");
   
    var time = System.currentTimeMillis();
    val config = configColl.findOne(MongoDBObject("customer_id" -> user_id)).getOrElse(configurePurchasesParameters(sc, user_id));
    println("Get config: " + (System.currentTimeMillis() - time) / 1000.0);
    
    
    val numPartitions = 6;
    val rank = config.get("rank").toString().toInt;
    val numIter = config.get("numIter").toString().toInt;
    val lambda = config.get("lambda").toString().toDouble;
    
    configConnection.underlying.close();
    configColl = null;
    
    time = System.currentTimeMillis();
    // load personal purchases
    val myPurchasesRDD = Functions.loadUserPurchases(user_id, sc);
    println("Get user purchases: " + (System.currentTimeMillis() - time) / 1000.0);
    
    time = System.currentTimeMillis();
    // load purchases and products
    val purchases = Functions.loadPurchases(sc)
    println("Get purchases: " + (System.currentTimeMillis() - time) / 1000.0);
    
    val training = purchases.filter(x => x._1 <= 3)
      .values
      .union(myPurchasesRDD)
      .repartition(numPartitions)
      .cache()
    
    time = System.currentTimeMillis();
    val model = ALS.train(training, rank, numIter, lambda);
    println("Training: " + (System.currentTimeMillis() - time) / 1000.0);
    
    time = System.currentTimeMillis();
    val prediction = Functions.predictPurchasesForUser(model, user_id, sc).collect().sortBy(-_.rating).take(20)
    println("Prediction: " + (System.currentTimeMillis() - time) / 1000.0);
    
    val predictionsConnection = MongoClient();
    var predictionsColl = predictionsConnection("Acme-Supermarket-Recommendations")("recommendations_purchase");
    
    val removeQuery = MongoDBObject("customer_id" -> user_id);
    predictionsColl.remove(removeQuery);
    
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
  }
  
  def makeRatesRecommendation(sc: SparkContext, user_id: Int) = {
    val configConnection = MongoClient();
    var configColl = configConnection("Acme-Supermarket-Recommendations")("config_rates");
   
    val config = configColl.findOne(MongoDBObject("customer_id" -> user_id)).getOrElse(configureRatesParameters(sc, user_id));
        
    val numPartitions = 6;
    val rank = config.get("rank").toString().toInt;
    val numIter = config.get("numIter").toString().toInt;
    val lambda = config.get("lambda").toString().toDouble;
    
    configConnection.underlying.close();
    configColl = null;
    
    
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
    
    val prediction = Functions.predictRatesForUser(model, user_id, sc).collect().sortBy(-_.rating).take(20)
    
    val predictionsConnection = MongoClient();
    var predictionsColl = predictionsConnection("Acme-Supermarket-Recommendations")("recommendations_rates");
    
    val removeQuery = MongoDBObject("customer_id" -> user_id);
    predictionsColl.remove(removeQuery);
    
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
  
  def configureRatesParameters(sc: SparkContext, user_id: Int) : DBObject = {
    val configConnection = MongoClient();
    var configColl = configConnection("Acme-Supermarket-Recommendations")("config_rates");
    
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
    
    val bestValues = MongoDBObject(
      "customer_id" -> user_id,
      "rank" -> bestRank,
      "numIter" -> bestNumIter,
      "lambda" -> bestLambda
    );

    val query = MongoDBObject("customer_id" -> user_id);
    val rows = configColl.find(query).toSeq;
    
    if ( rows.isEmpty ){
      configColl += bestValues;
    } else {    
      val update_query = MongoDBObject("customer_id" -> user_id);
      val update_fields = MongoDBObject(
        "$set" -> bestValues 
      );
      
      configColl.findAndModify(update_query, update_fields);
    }
    
    configConnection.underlying.close();
    configColl = null;
    
    return bestValues;
  }
  
  def configurePurchasesParameters(sc: SparkContext, user_id: Int) : DBObject = {
    val configConnection = MongoClient();
    var configColl = configConnection("Acme-Supermarket-Recommendations")("config_purchases");
    
    // load personal ratings
    val myPurchasesRDD = Functions.loadUserPurchases(user_id, sc)
    
    // load ratings and products
    val purchases = Functions.loadPurchases(sc)

    val numPartitions = 6;
    val training = purchases.filter(x => x._1 <= 3)
      .values
      .union(myPurchasesRDD)
      .repartition(numPartitions)
      .cache()
    val validation = purchases.filter(x => x._1 > 3 && x._1 <= 4)
      .values
      .repartition(numPartitions)
      .cache()
    val test = purchases.filter(x => x._1 > 4).values.cache()
    
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
    
    val bestValues = MongoDBObject(
      "customer_id" -> user_id,
      "rank" -> bestRank,
      "numIter" -> bestNumIter,
      "lambda" -> bestLambda
    );

    val query = MongoDBObject("customer_id" -> user_id);
    val rows = configColl.find(query).toSeq;
    
    if ( rows.isEmpty ){
      configColl += bestValues;
    } else {    
      val update_query = MongoDBObject("customer_id" -> user_id);
      val update_fields = MongoDBObject(
        "$set" -> bestValues 
      );
      
      configColl.findAndModify(update_query, update_fields);
    }
    
    configConnection.underlying.close();
    configColl = null;
    
    return bestValues;
  }
}