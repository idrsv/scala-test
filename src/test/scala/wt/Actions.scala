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
    .check(css("select[name=depart] option","value").findAll.saveAs("departureCity"))
    .check(css("select[name=depart] option","value").findAll.saveAs("arrivalCity"))
    .check(status.is(200))

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


  val flightsList: HttpRequestBuilder = http("get flights")
    .get("/cgi-bin/reservations.pl")
    .check(css("input[name=outboundFlight]", "value").findAll.saveAs("flightsList"))
    .check(status.is(200))

  val flightsList3: HttpRequestBuilder = http("get flights 2 #{flightsList}")
    .get("/cgi-bin/reservations.pl")

  val flight = exec { session =>
    val listFlights = session("flightsList").as[Seq[String]]
    val randomFlights = listFlights(Random.nextInt(listFlights.length))
    println(s"Random flight: $randomFlights")
    session.set("randomFlights", randomFlights)
  }
}