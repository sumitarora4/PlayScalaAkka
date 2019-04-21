package com.akka.investigation

import akka.actor.{ Actor, Props, ActorSystem }

class BadShakespeareanActor extends Actor {
  
  def receive = {
    
    case "Good Morning" =>
      println("him: Hey Good morning buddy")
      
    case "Hi" =>
    println("him: Hello")
  }

}

object BadShakespeareanMain {

  val system = ActorSystem("BadShakespearean")
  val actor = system.actorOf(Props[BadShakespeareanActor], "Shake")

  def send(msg: String) {

    println(s"Me: $msg")
    actor ! msg
    Thread.sleep(1000)
  }
  def main(args: Array[String]) {
    
    send("Good Morning")
    send("Hi")
    
    system.terminate()

  }

}