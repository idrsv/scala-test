package wt

import io.gatling.core.Predef._
import io.gatling.core.structure.{ChainBuilder, ScenarioBuilder}

object CommonScenario{
  def apply(): ScenarioBuilder = new CommonScenario().scn
}

class CommonScenario {

  val scn: ScenarioBuilder = scenario("Common scenario")
    .exec(Actions.webtours)
    .exec(Actions.welcome1)
    .exec(Actions.nav2)
    .exec(Actions.login3)
    .exec(Actions.nav4)
    .exec(Actions.login5)
    .exec(Actions.welcome6)
    .exec(Actions.nav7)
    .exec(Actions.reservations8)
    .exec(Actions.reservations9)
    .exec(Actions.reservations10)
    .exec(Actions.reservations11)
    .exec(Actions.reservations12)
}
