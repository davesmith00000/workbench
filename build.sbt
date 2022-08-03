import sbt.Keys._

Global / onChangedBuildSource := ReloadOnSourceChanges

val defaultSettings = Seq(
  scalacOptions ++= Seq("-feature", "-deprecation"),
  Compile / unmanagedSourceDirectories += baseDirectory.value /  "shared" / "main" / "scala",
  Test / unmanagedSourceDirectories += baseDirectory.value / "shared" / "test" / "scala"
)

lazy val root = project.in(file(".")).settings(defaultSettings:_*).settings(
  name := "workbench",
  version := "0.4.1",
  organization := "com.lihaoyi",
  scalaVersion := "2.12.16",
  sbtPlugin := true,
  Test / publishArtifact := false,
  publishTo := Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2"),
  pomExtra :=
    <url>https://github.com/lihaoyi/workbench</url>
      <licenses>
        <license>
          <name>MIT license</name>
          <url>http://www.opensource.org/licenses/mit-license.php</url>
        </license>
      </licenses>
      <scm>
        <url>git://github.com/lihaoyi/workbench.git</url>
        <connection>scm:git://github.com/lihaoyi/workbench.git</connection>
      </scm>
      <developers>
        <developer>
          <id>lihaoyi</id>
          <name>Li Haoyi</name>
          <url>https://github.com/lihaoyi</url>
        </developer>
      </developers>
  ,
  Compile / resources += {
    (client / Compile / fullOptJS).value
    (client / Compile / fullOptJS / artifactPath).value
  },
  addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.21"),
  libraryDependencies ++= Seq(
    Dependencies.akkaHttp,
    Dependencies.akka,
    Dependencies.autowire.value,
    Dependencies.upickle.value
  )
)

lazy val client = project.in(file("client"))
  .enablePlugins(ScalaJSPlugin)
  .settings(defaultSettings: _*)
  .settings(
    Compile / unmanagedSourceDirectories += baseDirectory.value / ".." / "shared" / "main" / "scala",
    libraryDependencies ++= Seq(
      Dependencies.autowire.value,
      Dependencies.dom.value,
      Dependencies.upickle.value
    ),
    Compile / fullOptJS / scalaJSLinkerConfig ~= { _.withSourceMap(false) }
  )
