package com.github.janikibichi.learnakka.http

import akka.actor.ActorSystem
import scala.util.{Success,Failure}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Sink, Source}
import scala.concurrent.duration._

object ConnectionLevelClientAPIApp extends App {
  implicit val actorSystem = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = actorSystem.dispatcher

  val connectionFlow = Http().outgoingConnectionHttps("api.github.com")
  val akkaToolkitRequest = HttpRequest(uri = "/repos/akka/akka-http")

  val responseFuture = Source
    .single(akkaToolkitRequest)
    .via(connectionFlow)
    .runWith(Sink.head)

  responseFuture.andThen{
    case Success(response) =>
        response.entity.toStrict(5 seconds).map(_.data.decodeString("UTF-8")).andThen{

          case Success(json) =>
            val pattern = """.*"open_issues":(.*?),.*""".r
            pattern.findAllIn(json).matchData foreach { m =>
              println(s"There are ${m.group(1)} open issues in Akka Http.")
              materializer.shutdown()
              actorSystem.terminate()
            }

          case _ =>
        }

    case _ => println("request failed")
  }
}
