import AssemblyKeys._

assemblySettings

name := "stock_server"

version := "1.0"

scalaVersion := "2.12.1"

test in assembly := {}

mergeStrategy in assembly := {
  case PathList(ps @ _*) if ps.last endsWith "BUILD" => MergeStrategy.discard
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x =>
    val oldStrategy = (mergeStrategy in assembly).value
    oldStrategy(x)
}

libraryDependencies ++= Seq(
  "com.github.pathikrit" %% "better-files" % "2.17.1",
  "com.github.finagle" %% "finch-core" % "0.12.0",
  "com.github.finagle" %% "finch-circe" % "0.12.0",
  "io.circe" %% "circe-core" % "0.7.0",
  "io.circe" %% "circe-generic" % "0.7.0",
  "io.circe" %% "circe-parser" % "0.7.0"
)