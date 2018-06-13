package com.github.janikibichi.learnakka.http

import akka.http.scaladsl.marshalling.{Marshaller, ToEntityMarshaller}
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.{FromEntityUnmarshaller, Unmarshaller}

case class SpeedMeasurement(timestamp: Long, latitude: Double, longitude: Double, value: Double){
  val marshall = s"$timestamp $latitude $longitude $value"
}

object SpeedMeasurement {
  def unmarshall(str: String) = {
    str.split("\\s") match {
      case Array(timestamp, latitude, longitude, value) =>
        SpeedMeasurement(timestamp.toLong, latitude.toDouble, longitude.toDouble, value.toDouble)
    }
  }
}

trait SpeedMeasurementMarshallingHelper{
  val contentType = ContentType(MediaTypes.`text/tab-separated-values`,HttpCharsets.`UTF-8`)

  implicit val utf8TextSpaceMarshaller:
    ToEntityMarshaller[SpeedMeasurement] = Marshaller.withFixedContentType(contentType){
    speedMeasurement => HttpEntity(contentType, speedMeasurement.marshall)
  }

  implicit val utf8TextSpaceUnmarshaller:
    FromEntityUnmarshaller[SpeedMeasurement] = Unmarshaller.stringUnmarshaller.map(SpeedMeasurement.unmarshall)
}
