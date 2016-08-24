package edu.indiana.soic.cs.utils;

import org.junit.runner._
import org.scalatest._
import org.scalatest.junit._
import org.scalamock._
import org.scalamock.scalatest._

/*
 * NOTE: ScalaTest's normal approach for running suites of tests in parallel is to run 
 * different suites in parallel, but the tests of any one suite sequentially.
 * 
 * Source: http://doc.scalatest.org/2.2.4/index.html#org.scalatest.ParallelTestExecution
 * */

// One test suite
@RunWith(classOf[JUnitRunner])
class JsonUtilTest extends FunSuite {
  trait TestSets {
    val myjson = """{"key1":"value1"}"""
    val mymap = Map("key1" -> "value1")
  }  
    
  // ********************* METHOD #1 ************************************
  test("toMap method") {
    new TestSets {
      val result = JsonUtil.toMap[String](myjson)
      assert(result.get("key1").get.asInstanceOf[String] == mymap.get("key1").get.asInstanceOf[String])
    }
  }
}
