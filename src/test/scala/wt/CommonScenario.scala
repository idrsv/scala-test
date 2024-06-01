package wt

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}
import io.gatling.http.Predef._

object CommonScenario{
  def apply(): ScenarioBuilder = new CommonScenario().scn
}

class CommonScenario {

  val scn: ScenarioBuilder = scenario("Common scenario")
    .exec(Actions.main)
    .exec(Actions.login)
    .exec(Actions.cities)
//    .exec(Actions.depart)
//    println("#{listCity}")

//    .exec(session => {
//      val departureCities = session("#{listCity}").as[Seq[(String, String)]]
//      val randomDepartureCity = departureCities(util.Random.nextInt(departureCities.length))._1
//      val arrivalCities = session("#{listCity}").as[Seq[(String, String)]]
//      val randomArrivalCity = arrivalCities(util.Random.nextInt(arrivalCities.length))._1
//      println(s"Random Departure City: $randomDepartureCity")
//      println(s"Random Arrival City: $randomArrivalCity")
//      session.set("RandomDepartureCity", randomDepartureCity).set("RandomArrivalCity", randomArrivalCity)
//    })

//    .exec(Actions.list)
//    .exec(Actions.depart)
}
