package wt

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

object Actions {

  val main: HttpRequestBuilder = http("main")
    .get("/webtours")

  val login: HttpRequestBuilder = http("login")
    .post("/cgi-bin/login.pl")
    .formParam("username", "w1mt23")
    .formParam("password", "w1mt23")
    .check(status.is(200))

  val cities: HttpRequestBuilder = http("cities")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page","welcome")
    .check(status.is(200))
//    .check(css("select[name=depart] option","value").findAll.saveAs("listCity"))

//  val depart: HttpRequestBuilder = http("depart #{listCity}")
//    .get("/cgi-bin/reservations.pl")
}
