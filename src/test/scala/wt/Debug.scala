package wt

import io.gatling.core.Predef._

import scala.concurrent.duration.DurationInt

class Debug extends Simulation{
  setUp(CommonScenario().inject(
      incrementUsersPerSec(10)
        .times(10)
        .eachLevelLasting(1.minutes)
        .separatedByRampsLasting(20.minutes)
        .startingFrom(0)
    ).protocols(httpProtocol)
  ).assertions(forAll.failedRequests.percent.lte(5))
    .maxDuration(25.minutes)
}

//setUp(CommonScenario().inject(atOnceUsers(1)))
//  .protocols(httpProtocol)
//  .assertions(global.responseTime.max.lt(1000))
//  .maxDuration(20.minutes)