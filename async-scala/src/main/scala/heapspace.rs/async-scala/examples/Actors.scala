package asyncscala.examples

import akka.actor._
import akka.pattern._
import akka.util.Timeout

import scala.concurrent._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

class Heater extends Actor {

	private var currentTemperature = Temp(15)

	def receive = idle
	
	def idle: Receive = {
		case PowerOn =>
			println("[Heater] Is now running...")
			scheduleTempChange(1) 
			context.become(heating)
		case ChangeTemp(by) => updateTemp(by)
		case CheckTemp => sender() ! currentTemperature
		case _ =>

	}

	def heating: Receive = {		
		case PowerOff => 
			scheduleTempChange(-1)
			context.unbecome()
		case ChangeTemp(by) => updateTemp(by)
		case CheckTemp => sender() ! currentTemperature
		case _ =>
	}

	private def scheduleTempChange(by: Int) = 
		context.system.scheduler.scheduleOnce(15 seconds, self, ChangeTemp(by))

	private def updateTemp(by: Int) = {
		println(s"[Heater] Updating Temperature by $by")
		currentTemperature = currentTemperature.add(by)
		scheduleTempChange(by)
	}

}	

class Switch extends Actor {	

	def receive = idle 
	def idle: Receive = {
		case TurnOnPower(heater) => 
			println("[Switch] Turning On Heater...")
			heater ! PowerOn
			context.become(active)
		case _ =>
	}

	def active: Receive = {
		case TurnOffPower(heater) => 
			println("[Switch] Turning Heater Off...")
			heater ! PowerOff
		case _ =>
	}
}

class Thermostatt extends Actor {
	
	val switch = context.actorOf(Props[Switch])
	val heater = context.actorOf(Props[Heater])

	val maxTemp = 25
	val minTemp = 17

	implicit val timeout = Timeout(5 seconds)

	def receive = idle

	def idle: Receive = {
		case PowerOn => 
			println("[Thermostatt] Turning on...")
			context.become(active)
		case _ =>
	}

	def active: Receive = {
		case Temp(tempValue) => 
			println(s"Current Temperature $tempValue")
			tempValue match {
				case x if x >= maxTemp => switch ! TurnOffPower(heater)
				case x if x <= minTemp => switch ! TurnOnPower(heater)
				case _ =>
			}
		case CheckTemp =>
			println("[Thermostatt] Checking Temperature...")
			val selfRef = context.self
			
			(heater ? CheckTemp).mapTo[Temp] onComplete {
				case Success(temp) => selfRef ! temp
				case Failure(ex) => println(s"Temperature mesuring failure (!?) $ex")
			}
		case PowerOff => 
			println("[Thermostatt] Got PowerOff signal, switching everything off...")
			switch ! TurnOffPower(heater)
			context.unbecome()
		case _ =>
	}

	override def preStart = {
		context.system.scheduler.schedule(1 seconds, 15 seconds, self, CheckTemp)
	}
}