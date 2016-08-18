package edu.indiana.soic.cs.utils

import scala.io.Source
import scala.collection.mutable._
import edu.indiana.soic.cs.model._


class MyUtilsImpl extends MyUtils {
  def getSparkArgs(fileName:String = "./resources/main/sparkArgs.json"): SparkConfProps = {
      val dbArgsJsonString = Source.fromFile(fileName).getLines().mkString
      val imap = JsonUtil.fromJson[scala.collection.mutable.Map[String,Any]](dbArgsJsonString)
      
      imap.keySet.foreach { x => println(x + " : " + imap.get(x).get) }

      val setPropsList = Array(imap.get("setprops-key-1").get.asInstanceOf[String],
                         imap.get("setprops-val-1").get.asInstanceOf[String])
      
      val sparkConfProps = SparkConfProps(imap.get("appname").get.asInstanceOf[String],
                                          imap.get("masternode").get.asInstanceOf[String],
                                          setPropsList)

      sparkConfProps
  }
  
  def getMySQLArgs(fileName:String = "./resources/main/dbArgs.json"): MySQLProps = {
      val dbArgsJsonString = Source.fromFile(fileName).getLines().mkString
      val imap = JsonUtil.fromJson[scala.collection.mutable.Map[String,String]](dbArgsJsonString)
      
      imap.keySet.foreach { x => println(x + " : " + imap.get(x).get) }
      
      val mysqlProps = MySQLProps(
                                   imap.get("username").get,  
                                   imap.get("password").get,
                                   imap.get("hostname").get,
                                   imap.get("hostport").get,
                                   imap.get("dbname").get)
                                   
      mysqlProps
  }
  
  def getInputArgs(fileName:String = "./resources/main/input.json"):  Map[String, String] = {
      val dbArgsJsonString = Source.fromFile(fileName).getLines().mkString
      val imap = JsonUtil.fromJson[scala.collection.mutable.Map[String,String]](dbArgsJsonString)
      imap
  }
}