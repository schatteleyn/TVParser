import play.api._
import play.api.libs.concurrent.Akka

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    Akka.system.scheduler.schedule(0 seconds, 24 hours, programParser, "tick")
  } 
}
