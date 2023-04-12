ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "Heat23Scala",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.15",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15",
    libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.32",
    //libraryDependencies += "org.mariadb.jdbc" % "mariadb-java-client" % "3.1.2",
    libraryDependencies += "mysql" % "mysql-connector-java" % "8.0.32",
    libraryDependencies += "com.github.losizm" %% "little-json" % "9.0.0"
  )
