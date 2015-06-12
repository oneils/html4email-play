name := """html4email-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  "org.webjars" % "bootstrap" % "3.3.4",
  "io.spray" % "spray-json_2.11" % "1.3.2",
  "org.webjars" %% "webjars-play" % "2.4.0-1",

  specs2 % Test
)

LessKeys.compress in Assets := true

includeFilter in (Assets, LessKeys.less) := "*.less"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator