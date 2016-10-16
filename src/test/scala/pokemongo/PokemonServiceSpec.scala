package pokemongo

/**
  * Created by Veezq
  */
import akka.http.scaladsl.model.ContentTypes._
import akka.http.scaladsl.model.StatusCodes._
import akka.http.scaladsl.model.{HttpEntity, HttpMethods, HttpRequest}
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.{FreeSpec, MustMatchers}


class PokemonServiceSpec extends FreeSpec with MustMatchers with ScalatestRouteTest with PokemonService{

  "should return empty list of pokemons" in {
    WithTransformation2(Get(s"/pokemons")) ~> route ~> check {
      status mustBe OK
      contentType mustBe `application/json`
      responseAs[String] mustBe "[]"
    }
  }

  "should accept a pokemon with Http/POST and show it with Http/GET" in {
    val name = "pikachu"

    val jsonRequest =
      s"""
         |  {"name": "$name"}
      """.stripMargin

    val postRequest = HttpRequest(
      HttpMethods.POST,
      uri = "/pokemons",
      entity = HttpEntity(`application/json`, jsonRequest)
    )

    postRequest ~> route ~> check {
      status mustBe OK
      contentType mustBe `text/plain(UTF-8)`
      responseAs[String] mustBe s"Caught pokemon ${name}!!!"
    }

    Get(s"/pokemons") ~> route ~> check {
      status mustBe OK
      contentType mustBe `application/json`
      responseAs[String] contains name
      val response = responseAs[String]
    }
  }

}
