import sbt._
import sbt.Keys.{javaOptions, name, organization, resolvers, scalacOptions, version}

object BuildSettings {
  val repositories: Seq[Resolver] = Seq(
    "Apache repo" at "https://repository.apache.org/content/repositories/releases",
    "JBoss repo" at "https://repository.jboss.org/nexus/content/groups/public-jboss",
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots"),
    "Mvnrepository" at "https://mvnrepository.com/artifact"
  )
  val org: String = "com.examples.graalvm"

  val compilerOptions: Seq[String] = Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-language:existentials",
    "-language:higherKinds",
    "-language:implicitConversions",
    "-unchecked",
    "-Xlint",
    "-Xfatal-warnings",
    "-Yno-adapted-args",
    "-Ywarn-dead-code",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture",
    "-Ywarn-unused-import",
    "-target:jvm-1.8",
    "-explaintypes",
    "-Ypartial-unification"
  )

  def baseSettings = {
    Seq(
      organization := org,
      resolvers ++= repositories,
      scalacOptions ++= compilerOptions
    )
  }

  def versionSettings: Seq[Def.Setting[_]] = Seq(
    javaOptions ++= Seq(
      s"-Dversion=${version.value}",
      s"-DappName=${name.value}"
    )
  )
}