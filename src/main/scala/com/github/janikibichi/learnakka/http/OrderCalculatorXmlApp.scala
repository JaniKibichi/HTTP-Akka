package com.github.janikibichi.learnakka.http

import akka.http.scaladsl.server.HttpApp
import akka.http.scaladsl.settings.ServerSettings
import com.typesafe.config.ConfigFactory

import scala.xml.NodeSeq
import scala.util.Random._

object OrderCalculatorXMLServer extends HttpApp with OrderXMLSupport {
  val route =
    path("calculateGrandTotal" ~Slash.?){
      post{
        entity(as[NodeSeq]){ xmlOrder =>
          complete {
            calculateGrandTotal(xmlOrder)
          }
        }
      }
    }~path("randomOrder"){
      get{
        complete{
          generateRandomOrder()
        }
      }
    }

  private def calculateGrandTotal(o: Order): NodeSeq = {
    val amount = o.items.map(i => i.percentageDiscount.getOrElse(1.0d) * i.unitPrice * i.quantity).sum + o.deliveryPrice
    GrandTotal(o.id, amount)
  }

  private def generateRandomOrder(): NodeSeq = {
    val items = (0 to nextInt(5)).map( i=> {
      Item(i, nextInt(100), 1000*nextDouble(), if(nextBoolean()) Some(nextDouble()) else None)
    }).toList

    Order(nextString(4), System.currentTimeMillis(), items, 100 * nextDouble(), Map("notes"->"random"))
  }
}

object OrderCalculatorXMLServerApp extends App {
  OrderCalculatorXMLServer.startServer("0.0.0.0", 8088,ServerSettings(ConfigFactory.load))
}
