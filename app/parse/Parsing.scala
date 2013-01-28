package parse

import scala.xml._

import scala.util.matching.Regex

import models.Programs

class Parsing(val url: String) {
 
  def downloadItems(): List[Item] = {
 
    // load the xml object into memory
    val root = XML.load(url)
 
    // parse feed into Item data structure
    (root \\ "item").map(buildProgram(_)).toList
  }
 
  def buildProgram(node: Node): Program = {
    val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
    val date = format.format(new java.util.Date())

    val program = new Program(
	    new Regex("(\\w*\\d*) \|") findAll (node \\ "title").text, //channel
      new Regex("\: (.*) \(") findAll (node \\ "title").text, //title
      date,
      new Regex("\| (\\d{2}\:\\d{2}) \:") findAll (node \\ "title").text, //hour
      (node \\ "link").text //link
    )

    Programs.save(program)
  }
}
