package edu.indiana.soic.cs.services

import org.apache.spark._
import org.apache.spark.sql._
import edu.indiana.soic.cs.model._

class SparkEngineServiceImpl extends SparkEngineService {
  def getSparkConf(confModel: SparkConfProps): SparkConf = {
    val sConf = new SparkConf()
    .setAppName(confModel.appName)
    .setMaster(confModel.masterName)
    .set(confModel.multipleContexts(0), confModel.multipleContexts(1))
    
    sConf
  }
    
  def getApacheSparkContext(myConf: SparkConf): SparkContext = {
    val sc = SparkContext.getOrCreate(myConf)
    
    sc
  }
  
  def getApacheSparkSQLContext(sc: SparkContext): SQLContext = {
    val ssc = SQLContext.getOrCreate(sc)
    
    ssc
  }
  
  def makeDataframeFromJSON(ssc: SQLContext, filePath: String): DataFrame = {
    val df = ssc.read.json(filePath)
    
    df
  }
  
}