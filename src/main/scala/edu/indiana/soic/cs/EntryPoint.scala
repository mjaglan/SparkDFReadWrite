package edu.indiana.soic.cs

import edu.indiana.soic.cs.model._
import edu.indiana.soic.cs.services._
import edu.indiana.soic.cs.utils._
import org.apache.log4j.Logger
import org.apache.log4j.Level

object EntryPoint extends App {
  println("*** Entry Point ***")
  
  // Let's have cleaner console :-)
  Logger.getLogger("org").setLevel(Level.ERROR)
  Logger.getLogger("akka").setLevel(Level.ERROR)

  // override input args
  var inputFileName = "./resources/main/input.json"
  if (args.length == 1) {
    inputFileName = args(0)
  }
  
  // Print runtime arguments
  val inputController = new MyUtilsImpl
  val inputMap = inputController.getInputArgs(inputFileName)
  inputMap.keySet.foreach { x => println(x + " : " + inputMap.get(x).get) }
  
  // get mysql props model
  val mysqlProp = inputController.getMySQLArgs(inputMap.get("dbArgsFileName").get)
  
  // get spark conf props model
  val sparkConfProps = inputController.getSparkArgs(inputMap.get("sparkArgsFileName").get)
                             
  // get Spark Engine
  var ses = new SparkEngineServiceImpl
  
  // Get SparkConf
  val sConf = ses.getSparkConf(sparkConfProps)
  
  // Get SparkContext
  val sc = ses.getApacheSparkContext(sConf)
  
  // Get SparkSQLContext
  val ssc = ses.getApacheSparkSQLContext(sc)

  // Get MySQL Connection
  val sqlC = new MySQLConnectionServiceImpl
  
  // if (write): writeDF
  if (inputMap.get("write").get == "true") {
    // Make DataFrame
    val df = ses.makeDataframeFromJSON(ssc, "./resources/main/record.json")
    
    val feedBack = sqlC.writeDF(ssc, mysqlProp, df, inputMap.get("tableName").get)
    
    if (feedBack) {
      println("Successfully written all data which looks like =>")
      df.show(5, true)
      
    } else {
      println("Failed to write to DB!")
    }
    
  } else {
    
    // else(read): readDF
    val newDf = sqlC.readDF(ssc, mysqlProp, inputMap.get("query").get)
    if (newDf != null) {
      println("Total "+ newDf.count() + " records fetched which looks like =>")
      newDf.show(5, true) 
      
    } else {
      println("Failed to read from DB!")
    }
    
  }
}

