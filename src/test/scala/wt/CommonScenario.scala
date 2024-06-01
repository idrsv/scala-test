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
    .exec(Actions.departure)
    .exec(Actions.arrival)
    .exec(Actions.depart)
}
