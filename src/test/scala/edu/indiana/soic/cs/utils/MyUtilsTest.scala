package edu.indiana.soic.cs.utils

import org.junit.runner._
import org.scalatest._
import org.scalatest.junit._


@RunWith(classOf[JUnitRunner])
class MyUtilsTest extends FunSuite {
  trait TestSets {
    val myObj = new MyUtilsImpl
  }  
  
  // ********************* METHOD #1 ************************************
  test("getInputArgs method") {
    new TestSets {
      val a1 = myObj.getInputArgs()
      
      assert(a1.get("tableName").get.asInstanceOf[String] == "Persons")
    }
  }
  
  // ********************* METHOD #2 ************************************
  test("getMySQLArgs method") {
    new TestSets {
      val a1 = myObj.getMySQLArgs()
      
      assert(a1.hostName == "localhost")
    }
  }
  
  // ********************* METHOD #3 ************************************
  test("getSparkArgs method") {
    new TestSets {
      val a1 = myObj.getSparkArgs()
      
      assert(a1.appName == "app-name")
    }
  }
}
