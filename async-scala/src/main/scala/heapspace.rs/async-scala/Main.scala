package asyncscala

import akka.actor._

import examples._

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object AsyncScala extends App {
	override def main(args: Array[String]) {
		lazy val system = ActorSystem("async-scala")

		val tstatt = system.actorOf(Props[Thermostatt], "thermostatt")

		tstatt ! PowerOn

		system.scheduler.scheduleOnce(300 seconds, tstatt, PowerOff)
	}
}