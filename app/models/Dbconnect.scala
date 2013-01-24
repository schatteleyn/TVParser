package models

import com.mongodb.casbah._

import play.api.Play
import play.api.Play.current

object Dbconnect {
  val dbHost = Play.current.configuration.getString("mongohost") getOrElse "127.0.0.1"
  val dbPort = Play.current.configuration.getString("mongoport") getOrElse "27017" toInt
  val dbName = Play.current.configuration.getString("mongoname") getOrElse "gamific"
  val db = MongoConnection(dbHost, dbPort)(dbName)
}
