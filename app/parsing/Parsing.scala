package parsing

import scala.util.matching.Regex

import models.Program

case class ParsedTitle(channel: String, hour: String, title: String)

object ParsedTitle {
	val TitleRegex = """(\w*)\s\|\s(\d{1,2}\:\d{1,2})\s\:\s(.*)\s\(.*""".r
  def parsing(s: String): Option[ParsedTitle] = s match {
	  case TitleRegex(c, h, t) => Some(ParsedTitle(c, h, t))
	  case _ => None
  }
}
