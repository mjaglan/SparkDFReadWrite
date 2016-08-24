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

/*
 * NOTE: ScalaTest's normal approach for running suites of tests in parallel is to run 
 * different suites in parallel, but the tests of any one suite sequentially.
 * 
 * Source: http://doc.scalatest.org/2.2.4/index.html#org.scalatest.ParallelTestExecution
 * */

// One test suite
@RunWith(classOf[JUnitRunner])
class SparkEngineServiceTest extends FunSuite {
  val a1 = new SparkEngineServiceImpl
  
  /* Testing with mock RDDs?
   * 
   * Did some reading, tied mocking RDDs and one way I come up with is below.
   * 
   * For example, a discussion like this: http://stackoverflow.com/a/30947492
   * 
   * */
  
  test("SparkEngineService: check in and outs of each method") {
  
      val a11 = new SparkConf()
      val sparkConfProps = SparkConfProps("Spark-Read-Write-DF-App",
                          "local[*]",
                          Array("spark.driver.allowMultipleContexts","false"))
      val scp = a1.getSparkConf(sparkConfProps)
      assert(scp.getClass.toString() == a11.getClass.toString())
      
      // depends on above values
      val a22 = SparkContext.getOrCreate(scp)
      val sc = a1.getApacheSparkContext(scp)
      assert(sc.getClass.toString() == a22.getClass.toString())
      
      // depends on above values
      val a33 = SQLContext.getOrCreate(a22)
      val scc = a1.getApacheSparkSQLContext(sc)
      assert(scc.getClass.toString() == a33.getClass.toString())
      
      // depends on above values
      val schemaList = Array("h1","h2")
      val structField = schemaList.map(fieldName => StructField(fieldName, StringType, nullable = true))
      val a44 = scc.createDataFrame(sc.emptyRDD[Row], StructType(structField)) // empty RDD, instead of mock RDD
      val df = a1.makeDataframeFromJSON(scc, "./resources/main/record.json")
      assert(df.getClass.toString() == a44.getClass.toString())
      
  }
  
}

