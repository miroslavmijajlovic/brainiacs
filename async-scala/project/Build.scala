import sbt._
import Keys._

object AsyncScala extends Build {

  val akkaVersion = "2.3.9"

  lazy val buildSettings = Seq(
    organization := "rs.heapspace",
    version := "1.0",
    scalaVersion := "2.11.5"
  )

  lazy val root = Project(
    id = "async-scala",
    base = file("."),
    settings = defaultSettings)

  
  lazy val defaultSettings = Defaults.defaultSettings ++ Seq(
    libraryDependencies ++= {
      Seq(
        "com.typesafe.akka" %% "akka-actor" % akkaVersion % "compile",
        "com.typesafe.akka" %% "akka-kernel" % akkaVersion % "compile",
        "com.typesafe.akka" %% "akka-slf4j" % akkaVersion % "compile"
        )
    },

    resolvers ++= {
      Seq("Akka Releases" at "http://akka.io/repository",
        "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases",
        "Sonatype Nexus Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
        "Sonatype Nexus Releases" at "https://oss.sonatype.org/content/repositories/releases",
        "Local Maven" at "file://" + Path.userHome.absolutePath + "/.m2/repo",
        "Local Ivy" at "file://" + Path.userHome.absolutePath + "/.ivy2/local")
    },

    logLevel := Level.Info,

    maxErrors := 50)
}
