package edu.indiana.soic.cs.utils
import scala.collection.mutable._
import edu.indiana.soic.cs.model._

trait MyUtils {
  def getSparkArgs(fileName: String): SparkConfProps

  def getMySQLArgs(fileName: String): MySQLProps
  
  def getInputArgs(fileName: String): Map[String, String]
}
