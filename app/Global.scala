import play.api._
import play.api.Play.current
import play.api.libs.concurrent.Akka
import akka.actor.Props
import akka.util.duration._
import actors.Cron

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    val cron = Akka.system.actorOf(Props[Cron], name = "cron")
    Akka.system.scheduler.schedule(0 seconds, 24 hours, cron, "tick")
  } 
}
