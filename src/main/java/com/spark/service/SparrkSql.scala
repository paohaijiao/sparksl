package com.spark.service

import java.util.{Date, Properties}
import java.sql.Timestamp

import com.spark.bean.TransferInfo
import org.apache.spark.sql.types._
import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

import scala.collection.JavaConverters
case class itf_grey_list(id:Int,ent_name:String,grey_type:String,describe:String,create_by:String,date_created:Timestamp,grey_flag:String)

object SparrkSql {

  def invoke(tansfer:TransferInfo): Unit ={
//    val structFields = Array(StructField("id",StringType,true),StructField("ent_name",StringType,true),StructField("grey_type",StringType,true),StructField("describe",StringType,true)
//    ,StructField("create_by",StringType,true),StructField("date_created",DateType,true),StructField("grey_flag",DateType,true))
//    val structType = StructType(structFields)
    import org.apache.spark.SparkConf
    val conf = new SparkConf
    conf.setAppName("Spark MultipleContest Test")
    conf.set("spark.driver.allowMultipleContexts", "true")
    conf.set("spark.executor.memory", "8g")
    conf.setMaster("local[5]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc);
    Class.forName(tansfer.getSourceClass);
    val prop = new Properties()
    prop.setProperty("user",tansfer.getSourceUser)
    prop.setProperty("password",tansfer.getSourcePassw)
    import sqlContext.implicits._
    var rows = sqlContext.read.jdbc(tansfer.getSourceJdbc, tansfer.getSourceSchema + "." + tansfer.getSourceTable, prop).collectAsList();
    val seq = JavaConverters.asScalaIteratorConverter(rows.iterator).asScala.toSeq
    val jdbcDFTo = sc.parallelize(seq, 2)
    val column = ""
    val structType = buildStructType(column)
    val jdbcDF = sqlContext.createDataFrame(jdbcDFTo, structType)
    //jdbcDF.
    Class.forName(tansfer.getToClass);
    val prop1 = new Properties()
    prop1.setProperty("user",tansfer.getToUser)
    prop1.setProperty("password",tansfer.getToPassw)
    if(null!=tansfer.getWhereClause){
      jdbcDF.where(tansfer.getWhereClause)
    }
    if(null!=tansfer.getSelectClause){
      var seq=Seq;
      jdbcDF.show()
     // jdbcDF.drop(jdbcDF(id"))
    }

    jdbcDF.write.mode(SaveMode.Overwrite).jdbc(tansfer.getToJdbc, tansfer.getToSchema + "." + tansfer.getToTable, prop1);
    sc.stop();

  }

  def buildStructType(fields: String): StructType = {
    StructType(fields.split(",").map(_.split(":"))
      .map(field => {
        val fieldType = field(1).toString.toLowerCase() match {
          case "string" => StringType
          case "int" => IntegerType
          case "integer" => IntegerType
          case "double" => DoubleType
          case "float" => FloatType
          case "long" => LongType
          case "boolean" => BooleanType
          case "Date" => DateType
          case _ => StringType
        }
        StructField(field(0), fieldType, true)
      }))
  }

}
