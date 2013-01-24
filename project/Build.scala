import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "TVParser"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "org.mongodb" %% "casbah" % "2.5.0",
      "joda-time" % "joda-time" % "2.0",
      "org.joda" % "joda-convert" % "1.1"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = SCALA).settings(
      // Add your own project settings here      
    )

}
