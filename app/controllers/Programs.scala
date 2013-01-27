package controllers

import play.api._
import play.api.mvc._

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
      "date" -> date
    )) map { p => Ok(Json.toJson(p map {_.asJson})) } getOrElse NotFound
  }
  
}
