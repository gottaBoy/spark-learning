package com.my.bigdata.spark.sql

import org.apache.spark.SparkConf
import org.apache.spark.sql._

object Spark04_SparkSQL_Mongo {

    def main(args: Array[String]): Unit = {

        // TODO 创建SparkSQL的运行环境
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("sparkSQL")
        val spark = SparkSession.builder().config(sparkConf).getOrCreate()

        // 读取MySQL数据
        val df = spark.read
                .format("mongo")
                .option("url", "mongodb://linux1:3306/spark-sql")
                .option("driver", "com.mysql.jdbc.Driver")
                .option("user", "root")
                .option("password", "123123")
                .option("dbtable", "user")
                .load()
        //df.show

        // 保存数据
        df.write
                .format("jdbc")
                .option("url", "jdbc:mysql://linux1:3306/spark-sql")
                .option("driver", "com.mysql.jdbc.Driver")
                .option("user", "root")
                .option("password", "123123")
                .option("dbtable", "user1")
                .mode(SaveMode.Append)
                .save()


        // TODO 关闭环境
        spark.close()
    }
}
