package controllers

import play.api._
import play.api.mvc._

import org.joda.time.DateTime

import com.mongodb.casbah._, Imports._

import play.api.libs.json._, Json._

import models.Program

object Programs extends Controller {
  
  def get(title: String) = Action {
    Program.find(MongoDBObject(
      "title" -> title
    )) map { p => Ok(Json.toJson(p map {_.asJson})) } getOrElse NotFound
  }

  def getSome(date: String) = Action {
    Program.find(MongoDBObject(
      "date" -> new DateTime(date)
    )) map { p => Ok(Json.toJson(p map {_.asJson})) } getOrElse NotFound
  }
  
}
