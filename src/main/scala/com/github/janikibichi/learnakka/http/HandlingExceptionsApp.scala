package com.github.janikibichi.learnakka.http

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.http.scaladsl.server.HttpApp
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.settings.ServerSettings
import akka.util.Timeout
import akka.pattern.ask
import com.typesafe.config.ConfigFactory
import scala.concurrent.duration._


class HandlingExceptionsServer(someActor: ActorRef) extends HttpApp with RouteExceptionHandler{
  implicit val timeout = Timeout(500 millis)

  val route =
    handleExceptions(routeExceptionHandler){
      path("divide"){
        parameters('a.as[Int], 'b.as[Int]) { (a,b) =>
          complete {
            val result = a / b
            s"Result is: $result"
          }
        }
      }~path("futureTimingOut"){
        onSuccess(someActor ? "Something"){
          case _ => complete("Actor finished processing.")
        }
      }
    }
}

object HandlingExceptionsApp extends App {
  val unresponsiveActor = ActorSystem().actorOf(Props[UnresponsiveActor])
  new HandlingExceptionsServer(unresponsiveActor).startServer("0.0.0.0",8088,ServerSettings(ConfigFactory.load))
}

