package com.spark.service

import java.util.Properties

import com.spark.bean.TransferInfo
import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

object SparrkSql {

  def invoke(tansfer:TransferInfo): Unit ={
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
    val jdbcDF = sqlContext.read.jdbc(tansfer.getSourceJdbc,tansfer.getSourceSchema+"."+tansfer.getSourceTable,prop)
    Class.forName(tansfer.getToClass);
    val prop1 = new Properties()
    prop1.setProperty("user",tansfer.getToUser)
    prop1.setProperty("password",tansfer.getToPassw)
    if(null!=tansfer.getWhereClause){
      jdbcDF.where(tansfer.getWhereClause)
    }
    if(null!=tansfer.getSelectClause){
         val column=tansfer.getSelectClause.split(",").toSeq
    }
    jdbcDF.write.mode(SaveMode.Overwrite).jdbc(tansfer.getToJdbc,tansfer.getToSchema+"."+tansfer.getToTable,prop1);
    sc.stop();

  }

}
