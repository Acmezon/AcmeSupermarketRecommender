

import org.apache.spark.sql.hive.HiveContext

object Configuration {
  def setVariables(context: HiveContext){
    context.setConf("hive.metastore.warehouse.dir", "hdfs://localhost:54310/user/hive/warehouse")
    context.setConf("javax.jdo.option.ConnectionURL", "jdbc:derby:/usr/lib/hive/warehouse;databaseName=metastore_db;create=true")
  }
  
  def useDatabase(context: HiveContext, dbName: String){
    context.sql("use " + dbName)
  }
}