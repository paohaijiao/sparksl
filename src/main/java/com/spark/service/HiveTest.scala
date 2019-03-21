package com.spark.service


import java.io.File

import org.apache.spark.sql.{Row, SaveMode, SparkSession}

case class Record(key: Int, value: String)

object HiveTest {
  def main(args: Array[String]): Unit = {
    val warehouseLocation = new File("hdfs://CDH:9000/user/hive/warehouse").getAbsolutePath

    val spark = SparkSession
      .builder()
      .appName("Spark Hive Example")
      .config("spark.sql.warehouse.dir", warehouseLocation)
      .config("spark.sql.hive.metastore.version","1.2.1")
      .enableHiveSupport().master("local")
      .getOrCreate()
    import spark.implicits._
    import spark.sql

    sql("use test")
    // Queries are expressed in HiveQL
    sql("SELECT * FROM t_order").show()

  }
}
