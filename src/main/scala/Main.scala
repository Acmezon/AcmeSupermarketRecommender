

import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import org.apache.log4j.Level
import org.apache.spark.mllib.recommendation.Rating
import org.apache.log4j.Logger
import java.io.File
import org.apache.spark.mllib.recommendation.ALS
import com.mongodb.casbah.commons.MongoDBObject
import com.mongodb.casbah.MongoClient
import Functions._

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
    
    if(args(0).toInt > -1) {
      makeRecommendation(sc, args(0).toInt);
    } else {
      configureParameters(sc, args(0).toInt);
    }
    sc.stop()
  }
}