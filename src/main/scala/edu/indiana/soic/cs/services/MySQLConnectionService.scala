package edu.indiana.soic.cs.services

import org.apache.spark.sql._ 
import edu.indiana.soic.cs.model.MySQLProps

trait MySQLConnectionService {
  def writeDF(ssc: SQLContext, mysqlProp:MySQLProps, df: DataFrame, tableName: String): Boolean
  def readDF (ssc: SQLContext, mysqlProp:MySQLProps, query:String): DataFrame
}
