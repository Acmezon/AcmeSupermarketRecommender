

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.mllib.recommendation.Rating
import org.apache.spark.rdd.RDD
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel
import org.apache.spark.SparkContext

object Functions {
  def loadMyRatings(context: HiveContext, userId: Int): RDD[Rating] = {
    //Load my ratings
    val rows = context.sql("SELECT * FROM Ratings WHERE id_user = " + userId)
    val ratings = rows.map { row =>
      Rating(row.getInt(0), row.getInt(1).toInt, row.getInt(2))
    }.filter(_.rating > 0.0)
    if (ratings.isEmpty) {
      sys.error("No ratings provided.")
    } else {
      ratings
    }
  }
  
  def loadRatings(context: HiveContext): RDD[(Long, Rating)] = {
    //Load every other ratings
    val rows = context.sql("SELECT * FROM Ratings")
    val ratings = rows.map { row =>
      (row.getLong(3) % 10, Rating(row.getInt(0), row.getInt(1).toInt, row.getInt(2)))
    }
    ratings
  }
  
  def loadFilms(context: HiveContext): Map[Int, String] = {
    val rows = context.sql("SELECT * FROM Films")
    val films = rows.map { row =>
      (row.getInt(0), row.getString(1))
    }.collect().toMap
    
    films
  }
  
  /** Compute RMSE (Root Mean Squared Error). */
  def computeRmse(model: MatrixFactorizationModel, data: RDD[Rating], n: Long): Double = {
    val predictions: RDD[Rating] = model.predict(data.map(x => (x.user, x.product)))
    val predictionsAndRatings = predictions.map(x => ((x.user, x.product), x.rating))
      .join(data.map(x => ((x.user, x.product), x.rating)))
      .values
    math.sqrt(predictionsAndRatings.map(x => (x._1 - x._2) * (x._1 - x._2)).reduce(_ + _) / n)
  }
  
  def predictForUser(model: MatrixFactorizationModel, userId: Int, context: HiveContext, sparkContext: SparkContext): RDD[Rating] = {
    val notRatedMovies = getNotRatedMoviesByUser(userId, context, sparkContext);
    val prediction = model.predict(notRatedMovies)
    
    prediction
  }
  
  def getNotRatedMoviesByUser(userId: Int, context: HiveContext, sparkContext: SparkContext): RDD[(Int, Int)] = {
    val allFilms = loadFilms(context)
    val ratedFilms = loadMyRatings(context, userId).map(_.product).collect().toSet
    
    val candidates = sparkContext.parallelize(allFilms.keys.filter(!ratedFilms.contains(_)).toSeq)
    
    val notRatedMovies = candidates.map((userId, _))
    notRatedMovies
  }
  
  def printMovie(context: HiveContext, rating: Rating) = {
    val row = context.sql("SELECT movie_title FROM Films WHERE id_item = " + rating.product)
    row.map { film => println(film.getString(0)) }
  }
}