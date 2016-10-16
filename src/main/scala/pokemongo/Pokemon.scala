package pokemongo

import spray.json.DefaultJsonProtocol

/**
  * Created by Veezq
  */

object Model {

  case class Pokemon(name: String)

  object ServiceJsonProtocol extends DefaultJsonProtocol {
    implicit val pokemonJsonProtocol = jsonFormat1(Pokemon)
  }

}
