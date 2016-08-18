package edu.indiana.soic.cs.services

import org.apache.spark._
import org.apache.spark.sql._
import edu.indiana.soic.cs.model._

trait SparkEngineService {
  def getSparkConf(confModel: SparkConfProps): SparkConf
    
  def getApacheSparkContext(myConf: SparkConf): SparkContext
  
  def getApacheSparkSQLContext(sc: SparkContext): SQLContext
  
  def makeDataframeFromJSON(sqlc: SQLContext, filePath: String): DataFrame
  
}