name := "graalvm-examples"
version := "0.0.2"

val v = new {
  val logback = "1.2.3"
  val scalaLogging = "3.9.2"
  val graalJs = "20.3.0"
}


libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % v.logback,
  "com.typesafe.scala-logging" %% "scala-logging" % v.scalaLogging,
  "org.graalvm.js" % "js" % v.graalJs,
)


lazy val root = (project in file("."))

inThisBuild(
  List(
    organization := BuildSettings.org,
    scalaVersion := "2.12.12",
    resolvers ++= BuildSettings.repositories,
    scalacOptions ++= BuildSettings.compilerOptions
  )
)

