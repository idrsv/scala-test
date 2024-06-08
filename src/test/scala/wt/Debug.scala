package wt

import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class Debug extends Simulation{
  setUp(CommonScenario().inject(
    constantUsersPerSec(8).during(1.hour)
    ).protocols(httpProtocol)
  )
}

/**
 * setUp(CommonScenario().inject(
 * incrementUsersPerSec(10)
 * .times(10)
 * .eachLevelLasting(1.minutes)
 * .separatedByRampsLasting(20.minutes)
 * .startingFrom(0)
 * ).protocols(httpProtocol)
 * ).assertions(forAll.failedRequests.percent.lte(5))
 * .maxDuration(25.minutes)
 */