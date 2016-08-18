package edu.indiana.soic.cs.services

import java.util.Properties
import org.apache.spark.sql._ 
import edu.indiana.soic.cs.model.MySQLProps
import com.mysql.jdbc._

class MySQLConnectionServiceImpl extends MySQLConnectionService {
    def writeDF(ssc: SQLContext, mysqlProp:MySQLProps, df: DataFrame, tableName: String): Boolean = {

      val prop = new Properties()
      prop.setProperty("user", mysqlProp.userName)
      prop.setProperty("password", mysqlProp.password)
      prop.setProperty("driver", "com.mysql.jdbc.Driver")

      try {
        df.write
        .mode(SaveMode.Append)
        .jdbc("jdbc:mysql://"+mysqlProp.hostName+":"+mysqlProp.portNumber+"/"+mysqlProp.dbName, tableName, prop)
        
        // DEBUG: For testing only        
        val query = "Select * from " + tableName
        val newDf = this.readDF(ssc, mysqlProp, query)
        newDf.collect().foreach { x => println(x) }
        println("\n\n")
        
        true
      } catch {
        case e: Exception => {
              e.printStackTrace()
              false
        }
      }
    }
    
    def readDF (ssc: SQLContext, mysqlProp:MySQLProps, query:String): DataFrame = {
      try {
        val df = ssc.read.format("jdbc")
                          .option("url", "jdbc:mysql://"+mysqlProp.hostName+":"+mysqlProp.portNumber+"/"+mysqlProp.dbName)
                          .option("driver", "com.mysql.jdbc.Driver")
                          .option("dbtable", "("+query+") as tempTable")
                          .option("user", mysqlProp.userName)
                          .option("password", mysqlProp.password)
                          .load()
        df
      } catch {
        case e: Exception => {
              e.printStackTrace()
              null
        }
      }
    }
}