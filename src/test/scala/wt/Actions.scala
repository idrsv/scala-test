package wt

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import scala.util.Random

object Actions {

  val main: HttpRequestBuilder = http("main")
    .get("/webtours")

  val login: HttpRequestBuilder = http("login")
    .post("/cgi-bin/login.pl")
    .formParam("username", "w1mt23")
    .formParam("password", "w1mt23")
    .check(status.is(200))

  val cities: HttpRequestBuilder = http("get cities")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page","welcome")
    .check(status.is(200))
    .check(css("select[name=depart] option","value").findAll.saveAs("departureCity"))
    .check(css("select[name=depart] option","value").findAll.saveAs("arrivalCity"))

  val departure = exec { session =>
    val listCity = session("departureCity").as[Seq[String]]
    val randomCityDep = listCity(Random.nextInt(listCity.length))
    println(s"Random city daperture: $randomCityDep")
    session.set("randomCityDep", randomCityDep)
  }

  val arrival = exec { session =>
    val listCity = session("arrivalCity").as[Seq[String]]
    val randomCityArr = listCity(Random.nextInt(listCity.length))
    println(s"Random city arrival: $randomCityArr")
    session.set("randomCityArr", randomCityArr)
  }

  val depart: HttpRequestBuilder = http("select city")
    .post("/cgi-bin/reservations.pl")
    .formParam("depart", "#{randomCityDep}")
    .formParam("arrive", "#{randomCityArr}")
    .check(status.is(200))



}
