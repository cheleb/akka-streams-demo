//enablePlugins(JavaServerAppPackaging) //<co id="example-enable-sbt-native-packager"/>

name := "Akka-stream-demo"

version := "1.0"

scalaVersion := "2.11.7"

javacOptions ++= Seq("-Xlog-implicits")

scalacOptions := Seq("-deprecation", "-feature", "-language:postfixOps")

organization := "com.goticks"

resolvers ++=
  Seq(
      "Spray Repository"    at "http://repo.spray.io",
      "Websudos releases"   at "https://dl.bintray.com/websudos/oss-releases/",
      "maven"                at "https://uk.maven.org/maven2")

      libraryDependencies ++= {
        val akkaVersion       = "2.4.1"
        val akkaHttpVersion      = "2.0.1"
        val kafkaVersion      = "0.8.2.2"
        val slf4jVersion      = "1.7.12"
        val PhantomVersion    = "1.17.5"
        val circeVersion      = "0.2.1"
        val slickVersion      = "3.1.1"

        Seq(
          "org.apache.kafka"  %%  "kafka"                   % kafkaVersion exclude("org.slf4j", "slf4j-log4j12") exclude ("log4j","log4j"),
          "com.typesafe.akka" %%  "akka-actor"              % akkaVersion,
          "com.typesafe.akka" %%  "akka-slf4j"              % akkaVersion,
          "com.typesafe.akka" %%  "akka-http-experimental"  % akkaHttpVersion,
          "com.typesafe.akka" %%  "akka-http-xml-experimental"  % akkaHttpVersion,
          "com.softwaremill.reactivekafka" %% "reactive-kafka-core" % "0.8.4",
          "com.softwaremill.reactivekafka" %% "zookeeper-committer" % "0.8.4",
          "com.typesafe.slick" %% "slick" % slickVersion,
          "com.github.tminglei" %% "slick-pg" % "0.11.0",
          "com.github.tminglei" %% "slick-pg_json4s" % "0.11.0",
          "com.typesafe.slick" %% "slick-hikaricp" % slickVersion,
          "org.postgresql" % "postgresql" % "9.4.1207.jre7",
          "com.github.etaty" %% "rediscala" % "1.6.0",
          "com.github.ben-manes.caffeine" % "caffeine" % "2.0.3",
          "io.circe" %% "circe-core" % circeVersion,
          "io.circe" %% "circe-generic" % circeVersion,
          "io.circe" %% "circe-parse" % circeVersion,
          "com.typesafe.akka" %%  "akka-remote"             % akkaVersion, //<co id="remote"/>
          "com.typesafe.akka" %%  "akka-multi-node-testkit" % akkaVersion % "test", //<co id="multinode"/>
          "com.typesafe.akka" %%  "akka-testkit"            % akkaVersion % "test",
          "org.scalatest"     %% "scalatest"                % "2.2.4"     % "test",
          "ch.qos.logback"    %  "logback-classic"                 % "1.1.2",
          "org.slf4j" % "log4j-over-slf4j" % slf4jVersion
        )
      }
