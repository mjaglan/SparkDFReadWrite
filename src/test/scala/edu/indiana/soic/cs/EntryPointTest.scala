package edu.indiana.soic.cs

import org.junit.runner._
import org.scalatest._
import org.scalatest.junit._


@RunWith(classOf[JUnitRunner])
class EntryPointTest extends FunSuite {
  
  // May be this is right way to check entry point
  test("EntryPoint method - write") {
    var isOk = false
    try {
      EntryPoint.main(Array("./testInput1.json"))
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
      EntryPoint.main(Array("./testInput2.json"))
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
      EntryPoint.main(Array("./testInput1.fail.json"))
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
      EntryPoint.main(Array("./testInput2.fail.json"))
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
