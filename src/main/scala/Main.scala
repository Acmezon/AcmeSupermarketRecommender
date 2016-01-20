

import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.log4j.Level
import org.apache.spark.mllib.recommendation.Rating
import org.apache.log4j.Logger
import java.io.File
import org.apache.spark.mllib.recommendation.ALS

object Main {
  def main(args: Array[String]) {

    Logger.getLogger("org.apache.spark").setLevel(Level.ERROR)
    Logger.getLogger("org.eclipse.jetty.server").setLevel(Level.OFF)

    if (args.length != 1) {
      println("Usage: spark-submit --driver-memory 2g --class Main " +
        "archive-*.jar userToRecommendId")
      sys.exit(1)
    }

    // set up environment
    val conf = new SparkConf()
      .setAppName("AcmeSupermarketRecommender")
    val sc = new SparkContext(conf)
    
    // load personal ratings

    val myRatingsRDD = Functions.loadUserRatings(args(0).toInt, sc)
    
    // load ratings and products

    val ratings = Functions.loadRatings(sc)

    val products = Functions.loadProducts(sc)

    val numPartitions = 4
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
    
    val numTraining = training.count()
    val numValidation = validation.count()
    val numTest = test.count()

    val ranks = List(8, 12)
    val lambdas = List(1.0, 10.0)
    val numIters = List(10, 20)
    var bestModel: Option[MatrixFactorizationModel] = None
    var bestValidationRmse = Double.MaxValue
    var bestRank = 0
    var bestLambda = -1.0
    var bestNumIter = -1
    for (rank <- ranks; lambda <- lambdas; numIter <- numIters) {
      val model = ALS.train(training, rank, numIter, lambda)
      val validationRmse = Functions.computeRmse(model, validation, numValidation)
      println("RMSE (validation) = " + validationRmse + " for the model trained with rank = "
        + rank + ", lambda = " + lambda + ", and numIter = " + numIter + ".")
      if (validationRmse < bestValidationRmse) {
        bestModel = Some(model)
        bestValidationRmse = validationRmse
        bestRank = rank
        bestLambda = lambda
        bestNumIter = numIter
      }
    }

    val testRmse = Functions.computeRmse(bestModel.get, test, numTest)

    println("The best model was trained with rank = " + bestRank + " and lambda = " + bestLambda
      + ", and numIter = " + bestNumIter + ", and its RMSE on the test set is " + testRmse + ".")
      
    val prediction = Functions.predictForUser(bestModel.get, args(0).toInt, sc).collect().sortBy(_.rating).take(10)
    
    var index = 1;
    prediction.foreach { rating =>
      println("%2d".format(index) + ". " + products.get(rating.product).get)
      index += 1
    }
    // clean up
    sc.stop()
  }
}