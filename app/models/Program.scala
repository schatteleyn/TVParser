package models

import play.api.libs.json._, Json._

import com.mongodb.casbah._, Imports._

case class Program (
  id: ObjectId,
  channel: String,
  title: String,
  date: String,
  hour: String,
  link: String
) {
	  def asJson = {
	    Json.toJson(
	      Map(
	        "_id" -> toJson(id.toString),
	        "channel" -> toJson(channel),
	        "title" -> toJson(title),
	        "date" -> toJson(date),
	        "hour" -> toJson(hour),
	        "link" -> toJson(link)
	      )
	    )	
	  }
	  def asMongoDBObject = MongoDBObject(
	    "_id" -> id,
	    "channel" -> channel,
	    "title" -> title,
	    "date" -> date,
	    "hour" -> hour,
	    "link" -> link
	  )
	}

object Program {
	def create(
    channel: String,
    title: String,
    date: String,
    hour: String,
    link: String
	): Program = {
	  Program(
	    id = new ObjectId(),
	    channel = channel,
	    title = title,
	    date = date,
	    hour = hour,
	    link = link
	  )	
	}
	def save(program: Program) = {
		  Dbconnect.db("programs").update(
		  MongoDBObject("_id" -> program.id.toString),
        program.asMongoDBObject,
        true,
        false
		  )
	}

	def find(query: MongoDBObject): Option[List[Program]] = {
	  Some(
      Dbconnect.db("programs").find(query).sort(MongoDBObject(
        "$orderby" -> "date"
      )).toList map { p => apply(p) }   	
    )
	}

	def apply(dbo: MongoDBObject): Program = {
	  apply(
	    dbo.as[ObjectId]("_id"),
	    dbo.as[String]("channel"),
	    dbo.as[String]("title"),
	    dbo.as[String]("date"),
	    dbo.as[String]("hour"),
	    dbo.as[String]("link")
	  )	
	}
}
