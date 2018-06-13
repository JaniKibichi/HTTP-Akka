package com.github.janikibichi.learnakka.http

import akka.http.scaladsl.server.HttpApp
import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.ConfigFactory

class TemperatureInMemoryStorageRestApi(cache: scala.collection.mutable.Map[String, TemperatureMeasurement])
  extends HttpApp
  with InMemoryStorageRESTAPI[String, TemperatureMeasurement]
  with GetRequestsHandler
  with PostRequestsHandler
  with PutRequestsHandler
  with DeleteRequestHandler {

  implicit val fixedPath = "temperature"
  val route = composedRoute(cache)
}

object TemperatureInMemoryStorageApiApplication extends App {
  val cache = scala.collection.mutable.Map.empty[String, TemperatureMeasurement]
  new TemperatureInMemoryStorageRestApi(cache).startServer("0.0.0.0",8088, ServerSettings(ConfigFactory.load))
}