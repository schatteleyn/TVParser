import play.api.libs.concurrent._, Akka

import parse.Parsing

class Actors extends Actor {
	val programParser = Akka.system.actorOf(Props[programParser], name = "programParser")

  def index = Action {
    Async {
      (programParser ? "tick").mapTo[String].asPromise.map { response => 
        Parsing("http://feeds.feedburner.com/programme-television?format=xml")
      }	
    }	
  }
}
