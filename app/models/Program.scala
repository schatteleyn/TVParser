package models

import org.joda.time.DateTime

import play.api.libs.json._, Json._

import com.mongodb.casbah._, Imports._

case class Program (
  channel: String,
  title: String,
  date: DateTime,
  link: String
) {
	  def asJson = {
	    Json.toJson(
	      Map(
	        "channel" -> toJson(channel),
	        "title" -> toJson(title),
	        "date" -> toJson(date.toString()),
	        "link" -> toJson(link)
	      )
	    )	
	  }
	}

object Program {
	def create(
    channel: String,
    title: String,
    date: DateTime,
    link: String
	): Program = {
	  Program(
	    channel = channel,
	    title = title,
	    date = date,
	    link = link
	  )	
	}
	//def save(program: Program): 
	def find(query: MongoDBObject): Option[List[Program]] = {
	  Some(
      Dbconnect.db("programs").find(query).sort(MongoDBObject(
        "$orderby" -> "date"
      )).toList map { p => apply(p) }   	
    )
	}

	def apply(dbo: MongoDBObject): Program = {
	  apply(
	    dbo.as[String]("channel"),
	    dbo.as[String]("title"),
	    //dbo.getAs[DateTime]("date") getOrElse 
	    new DateTime(),
	    dbo.as[String]("link")
	  )	
	}
}