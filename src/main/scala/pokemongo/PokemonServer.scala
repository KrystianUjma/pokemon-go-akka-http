package pokemongo

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer

/**
  * Created by Veezq
  */
class PokemonServer(implicit val system: ActorSystem, implicit val materializer: ActorMaterializer) extends PokemonService {

  def startServer(address: String, port: Int) = {

    Http().bindAndHandle(route, address, port)

  }

}

object PokemonServer {

  def main(args: Array[String]) {

    implicit val actorSystem = ActorSystem("pokemon-server")

    implicit val materializer = ActorMaterializer()

    val server = new PokemonServer()

    server.startServer("localhost", 8080)

  }

}
