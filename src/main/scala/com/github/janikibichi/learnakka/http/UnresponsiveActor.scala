package com.github.janikibichi.learnakka.http

import akka.actor.Actor

class UnresponsiveActor extends Actor {
  def receive = Actor.ignoringBehavior
}
