package edu.indiana.soic.cs.model

case class SparkConfProps (var appName: String,
    var masterName: String,
    var multipleContexts: Array[String])