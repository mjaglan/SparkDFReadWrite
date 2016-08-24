package edu.indiana.soic.cs

import org.junit.runner._
import org.scalatest._
import org.scalatest.junit._

/*
 * NOTE: ScalaTest's normal approach for running suites of tests in parallel is to run 
 * different suites in parallel, but the tests of any one suite sequentially.
 * 
 * Source: http://doc.scalatest.org/2.2.4/index.html#org.scalatest.ParallelTestExecution
 * */

// One test suite
@RunWith(classOf[JUnitRunner])
class EntryPointTest extends FunSuite {
  
  // May be this is right way to check entry point
  test("EntryPoint method - write") {
    var isOk = false
    try {
      EntryPoint.main(Array("./resources/test/testInput1.json"))
      isOk = true
    } catch {
        case e: Exception => {
              e.printStackTrace()
        }
    }
    assert(isOk == true)
  }
  
  test("EntryPoint method - read") {
    var isOk = false
    try {
      EntryPoint.main(Array("./resources/test/testInput2.json"))
      isOk = true
    } catch {
        case e: Exception => {
              e.printStackTrace()
        }
    }
    assert(isOk == true)
  }
  
    // May be this is right way to check entry point
  test("EntryPoint method - write fail") {
    var isOk = false
    try {
      EntryPoint.main(Array("./resources/test/testInput1.fail.json"))
      isOk = true
    } catch {
        case e: Exception => {
              e.printStackTrace()
        }
    }
    assert(isOk == true)
  }
  
  test("EntryPoint method - read fail") {
    var isOk = false
    try {
      EntryPoint.main(Array("./resources/test/testInput2.fail.json"))
      isOk = true
    } catch {
        case e: Exception => {
              e.printStackTrace()
        }
    }
    assert(isOk == true)
  }
  
  test("EntryPoint method - no args") {
    var isOk = false
    try {
      EntryPoint.main(Array())
      isOk = true
    } catch {
        case e: Exception => {
              e.printStackTrace()
        }
    }
    assert(isOk == true)
  }
}
