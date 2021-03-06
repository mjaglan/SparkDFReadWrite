package edu.indiana.soic.cs.services

import org.junit.runner._
import org.scalatest._
import org.scalatest.junit._
import org.scalamock._
import org.scalamock.scalatest._
import org.apache.spark._
import org.apache.spark.sql._
import edu.indiana.soic.cs._
import edu.indiana.soic.cs.model._
import edu.indiana.soic.cs.services._
import edu.indiana.soic.cs.utils._
import org.apache.spark.sql.types._
import java.util._

/*
 * NOTE: ScalaTest's normal approach for running suites of tests in parallel is to run 
 * different suites in parallel, but the tests of any one suite sequentially.
 * 
 * Source: http://doc.scalatest.org/2.2.4/index.html#org.scalatest.ParallelTestExecution
 * */

// One test suite
@RunWith(classOf[JUnitRunner])
class MySQLConnectionServiceTest extends FunSuite {
  val dbc = new MySQLConnectionServiceImpl
  val sc = SparkContext.getOrCreate(new SparkConf().setAppName("unit-test").setMaster("local[*]"))
  val ssc = SQLContext.getOrCreate(sc)
  val myUtilsObj = new MyUtilsImpl
  val mysqlProp = myUtilsObj.getMySQLArgs()

    
  val prop = new Properties()
      prop.setProperty("user", mysqlProp.userName)
      prop.setProperty("password", mysqlProp.password)
      prop.setProperty("driver", "com.mysql.jdbc.Driver")

  test("readDF method success") {
    var query = "Select * from Persons"
    var res = dbc.readDF(ssc, mysqlProp, query)
    assert(res.getClass.getName.toString() == "org.apache.spark.sql.DataFrame")
  }
  
  test("readDF method fail") {
    var query = ""
    var res = dbc.readDF(ssc, mysqlProp, query)
    assert(res == null)    
  }
  
  test("writeDF method success") {
    val schemaList = Array("h1","h2")
    val structField = schemaList.map(fieldName => StructField(fieldName, StringType, nullable = true))
    val nullDf = ssc.createDataFrame(sc.emptyRDD[Row], StructType(structField))
    var res = dbc.writeDF(ssc, mysqlProp, nullDf, "Persons")              
    assert(res==true)    
  }
  
  test("writeDF method fail") {
    val schemaList = Array("h1","h2")
    val structField = schemaList.map(fieldName => StructField(fieldName, StringType, nullable = true))
    val nullDf = ssc.createDataFrame(sc.emptyRDD[Row], StructType(structField))
    var res = dbc.writeDF(ssc, mysqlProp, null, "Persons")              
    assert(res==false)    
  }
  
}
