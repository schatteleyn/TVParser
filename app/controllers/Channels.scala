package controllers

import play.api._
import play.api.mvc._

import com.mongodb.casbah._, Imports._

import play.api.libs.json._, Json._

import models.Program

object Channels extends Controller {
  
  def get(channel: String) = Action {
    Program.find(MongoDBObject(
      "channel" -> channel
    )) map { p => Ok(Json.toJson(p map {_.asJson})) } getOrElse NotFound
  }

}
