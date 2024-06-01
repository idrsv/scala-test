package wt

import io.gatling.core.Predef._
import scala.concurrent.duration.DurationInt

class Debug extends Simulation{
  setUp(CommonScenario().inject(incrementConcurrentUsers(1)
    .times(11)
    .eachLevelLasting(2.minutes)
    .startingFrom(0)
    ).protocols(httpProtocol)
  ).maxDuration(20.minutes)
}