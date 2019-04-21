package zzz.akka.avionics

import akka.actor.{Props, Actor, ActorSystem, ActorLogging}
import scala.concurrent.duration

object Altimeter {
  
  case class RateChange(amount: Float)
}

class Altimeter extends Actor with ActorLogging{
  
  import Altimeter._
  
  implicit val ec = context.dispatcher
  val celling = 43000
  val maxRateofclimb = 5000
  
  var rateOfClimb = 0f
  var altitude = 0d
  
  var lastTick = System.currentTimeMillis()
  val ticker = context.system.scheduler.schedule(100, 100, self, Tick)
  
  case object Tick
  
  def receive = {
    case RateChange(amount) =>
      
      rateOfClimb = amount.min(1.0f).max(-1.0f) * maxRateofclimb 
      log info (s"Altimeter changed rate of climb to $rateOfClimb")
      
    case Tick => 
      val tick = System.currentTimeMillis()
      altitude = altitude + (tick-lastTick) / 60000.0 * rateOfClimb
      lastTick = tick
      
  }
  
  // Kill the ticker when we stop
  override def postStop(): Unit = ticker.cancel
    
}