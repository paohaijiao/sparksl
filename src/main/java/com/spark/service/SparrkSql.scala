package com.spark.service

import java.util.Properties

import com.spark.bean.TransferInfo
import org.apache.spark.sql.{SQLContext, SaveMode}
import org.apache.spark.{SparkConf, SparkContext}

object SparrkSql {

  def invoke(tansfer:TransferInfo): Unit ={
    val conf = new SparkConf().setAppName(tansfer.getSourceTable).setMaster("local")
     // .setJars(List("D:\\workspace\\hello\\target\\hello-1.0-SNAPSHOT.jar"))
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc);
    print("########################"+tansfer.getSourceClass);
    Class.forName("com.mysql.jdbc.Driver");
    val prop = new Properties()
    prop.setProperty("user",tansfer.getSourceUser)
    prop.setProperty("password",tansfer.getSourccePassw)
    val jdbcDF = sqlContext.read.jdbc(tansfer.getSourceJdbc,tansfer.getSourceTable,prop);
   // Class.forName(tansfer.getToJdbc);
    val prop1 = new Properties()
    prop1.setProperty("user",tansfer.getToUser)
    prop1.setProperty("password",tansfer.getToPassw)
    jdbcDF.write.mode(SaveMode.Overwrite).jdbc(tansfer.getToJdbc,tansfer.getToTable,prop1);
  }

}
