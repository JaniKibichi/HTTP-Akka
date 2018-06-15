package com.github.janikibichi.learnakka.http

import java.nio.file.Paths

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model.{ContentTypes, HttpRequest, Multipart}
import akka.stream.ActorMaterializer
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Success, Failure}

object UploadingFileClient extends App {
  implicit val actorSystem = ActorSystem()
  implicit val materializer = ActorMaterializer()
  import actorSystem.dispatcher

  val http = Http()
  val entity = Multipart.FormData.fromPath(
    "file", ContentTypes.`text/plain(UTF-8)`,Paths.get("./src/main/resources/testFile.txt")
  ).toEntity()

  val uris = Seq("http://127.0.0.1:8088/regularupload","http://127.0.0.1:8088/streamupload")
  val requests = uris.map(uri => HttpRequest(POST, uri, Nil, entity))

  Future.traverse(requests)(http.singleRequest(_))andThen {
    case Success(responses) => responses.foreach(response =>
      response.entity.toStrict(5 seconds).map(_.data.utf8String).andThen {
        case Success(content) => println(s"Response: $content")
        case _ =>
      })
    case Failure(e) => println(s"request failed $e")
  }
}
