name := "GradelyScala221"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  
  //For finding plugins
  "org.clapper" % "classutil_2.10" % "1.0.3",
  
  //Used for XSS scrubing
  "org.apache.commons" % "commons-lang3" % "3.2.1",
  
  //DateTime wrapper around Joda Time
  "com.github.nscala-time" %% "nscala-time" % "0.8.0"
)     

play.Project.playScalaSettings
