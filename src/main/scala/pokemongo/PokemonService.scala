package pokemongo

import akka.actor.ActorSystem
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import pokemongo.Model.{Pokemon, ServiceJsonProtocol}
import spray.json.DefaultJsonProtocol._
/**
  * Created by Veezq
  */
trait PokemonService {
  implicit val system: ActorSystem
  implicit val materializer: ActorMaterializer

  var list = List[Pokemon]()


  import ServiceJsonProtocol.pokemonJsonProtocol

  val route =
    path("pokemons"){
      post {
        entity(as[Pokemon]) {
          pokemon =>
            complete {
              list = pokemon :: list
              s"Caught pokemon ${pokemon.name}!!!"
            }
        }
      }~get{
        complete{
          list
        }
      }
    }

}