package com.spark.service

import java.util.{Date, Properties}
import java.sql.Timestamp

import com.spark.bean.TransferInfo
import org.apache.spark.sql.types._
import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}
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
    var jdbcDF = sqlContext.read.jdbc(tansfer.getSourceJdbc,tansfer.getSourceSchema+"."+tansfer.getSourceTable,prop)
    import sqlContext.implicits._
    var rows=jdbcDF.collect().map(e=>itf_grey_list(e.getInt(0)+50,e.getString(1),e.getString(2),e.getString(3),e.getString(4),e.getTimestamp(5),e.getString(6)))
    val jdbcDFTo=sc.parallelize(rows,2).toDF()
    import sqlContext.implicits._
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

    jdbcDFTo.write.mode(SaveMode.Overwrite).jdbc(tansfer.getToJdbc,tansfer.getToSchema+"."+tansfer.getToTable,prop1);
    sc.stop();

  }

}
