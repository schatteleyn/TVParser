package actors

import play.api.libs.concurrent._
import play.api.libs.ws.WS
import parsing.ParsedTitle
import scala.xml._
import models.Program
import akka.actor.Actor

class Cron extends Actor {
  def parseRequest(xml: Elem) = {
      ( xml \\ "item") map { item =>
        ParsedTitle.parsing((item \ "title").text) map { pt =>
          Program.create(
            pt.channel,
            pt.title,
            new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()),
            pt.hour,
            (xml \\ "item" \ "link").headOption.map(_.text) getOrElse ""
          )  
        }
     } 
  }.flatten 
  
  def save() = { 
    WS.url("http://feeds.feedburner.com/programme-television?format=xml").get()
    .map(_.xml)
    .map(parseRequest(_))
    .map( ps => ps.map(Program.save(_)))
  }

  def receive = {
    case "tick" => save() 
  }
}
