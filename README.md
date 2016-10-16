# pokemon-go-akka-http

Simple application with Akka HTTP based on: http://www.yoppworks.com/2016/08/24/blog/pokemon-go-with-akka-http/

1. Run PokemonServer

2. To add a pokemon use terminal command:
```
curl -H "Content-Type: application/json" -X POST -d "{\"name\":\"Pikachu\"}" http://localhost:8080/pokemons
```

3. To get all pokemons:
```
curl -X GET http://localhost:8080/pokemons 
```
