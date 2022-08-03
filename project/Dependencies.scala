import sbt._
import org.portablescala.sbtplatformdeps.PlatformDepsPlugin.autoImport._
import org.scalajs.sbtplugin.ScalaJSPlugin.autoImport._

object Dependencies {

  // jvm dependencies
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.0.10"
  val akka = "com.typesafe.akka" %% "akka-actor" % "2.4.19"

  // js and shared dependencies
  val autowire = Def.setting("com.lihaoyi" %%% "autowire" % "0.3.3")
  val dom = Def.setting("org.scala-js" %%% "scalajs-dom" % "2.2.0")
  val upickle = Def.setting("com.lihaoyi" %%% "upickle" % "2.0.0")

}
