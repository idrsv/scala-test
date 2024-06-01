package wt

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder

import scala.util.Random

object Actions {

  val main: HttpRequestBuilder = http("GET main")
    .get("/webtours/")
    .check(status.is(200))

  val login: HttpRequestBuilder = http("POST login")
    .post("/cgi-bin/login.pl")
    .formParam("username", "w1mt23")
    .formParam("password", "w1mt23")
    .check(status.is(200))

  val cities: HttpRequestBuilder = http("GET cities")
    .get("/cgi-bin/reservations.pl")
    .queryParam("page","welcome")
    .check(css("[name='depart'] > option").findRandom.saveAs("departureCity"))
    .check(css("[name='depart'] > option").findRandom.saveAs("arrivalCity"))
    .check(status.is(200))

//  val departure = exec { session =>
//    val listCity = session("departureCity").as[Seq[String]]
//    val randomCityDep = listCity(Random.nextInt(listCity.length))
//    println(s"Random city daperture: $randomCityDep")
//    session.set("randomCityDep", randomCityDep)
//  }
//
//  val arrival = exec { session =>
//    val listCity = session("arrivalCity").as[Seq[String]]
//    val randomCityArr = listCity(Random.nextInt(listCity.length))
//    println(s"Random city arrival: $randomCityArr")
//    session.set("randomCityArr", randomCityArr)
//  }

  val depart: HttpRequestBuilder = http("POST select city")
    .post("/cgi-bin/reservations.pl")
    .formParam("depart", "#{departureCity}")
    .formParam("arrive", "#{arrivalCity}")
    .formParam("departDate", "06/02/2024")
    .formParam("returnDate", "06/03/2024")
    .formParam("numPassengers", "1")
    .formParam("seatPref", "Window")
    .formParam("seatType", "Business")
    .formParam("findFlights.x:", "#{departureCity}")
    .formParam("findFlights.y:", "#{arrivalCity}")
    .formParam("advanceDiscount", "0")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")
    .check(status.is(200))


  val flightsList: HttpRequestBuilder = http("get flights")
    .get("/cgi-bin/reservations.pl")
    .check(regex("name=\"outboundFlight\" value=\"(.*?)\"").findRandom.saveAs("outboundFlight"))
//    .check(status.is(200))

  val flightsList3: HttpRequestBuilder = http("get flights 2 #{outboundFlight}")
    .get("/cgi-bin/reservations.pl")

//  val flight = exec { session =>
//    val listFlights = session("flightsList").as[Seq[String]]
//    val randomFlights = listFlights(Random.nextInt(listFlights.length))
//    println(s"Random flight: $randomFlights")
//    session.set("randomFlights", randomFlights)
//  }
}