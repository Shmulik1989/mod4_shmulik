package salvo.salvo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.stream.Collectors.toList;

//Define the URL address for api and defining a REST controller.
@RequestMapping("/api")
@RestController
public class SalvoController {

    //
    @Autowired
    private GameRepository gameRepo;

    @Autowired
    private GamePlayerRepository gamePlayerRepo;

    //Define the URL address for games(REST).
    @RequestMapping("/games")
    public List<Map> getAll() {

        return gameRepo.findAll().stream().map(game -> getGameMap(game)).collect(toList());
    }

    @RequestMapping("/api/game_view/{gamePlayer_id}")
    private Map<String, Object> findGamePlayer(@PathVariable Long gamePlayer_id){

    GamePlayer gamePlayer = gamePlayerRepo.findOne(gamePlayer_id);
    Game game = gamePlayer.getGame();
    Map<String, Object> gamePlayerDTO = new LinkedHashMap<>();
    Set<GamePlayer> gamePlayers = game.getGamePlayers();
    List<Map> gamePlayersList = new ArrayList<>();

    gamePlayerDTO.put("id",game.getId());
    gamePlayerDTO.put("created",game.getDate());

    gamePlayers.forEach(gamePlayer1 ->{

        Map gamePlayerMap = new LinkedHashMap();
        gamePlayerMap.put("id", gamePlayer1.getId());
        gamePlayerMap.put("player", playerMap(gamePlayer1.getPlayer()));
        gamePlayersList.add(gamePlayerMap);

    });

    gamePlayerDTO.put("gamePlayers", gamePlayersList);
    gamePlayerDTO.put("ships", gamePlayer.getShips());
    return gamePlayerDTO;

    }
    //Create a Map for each game
    private Map getGameMap(Game game){

        Map gameMap = new LinkedHashMap();

        //Create "id" and "created" values for each game.
        gameMap.put("id",game.getId());
        gameMap.put("created",game.getDate());

        //Create a gamePlayers Map for each game.
        List<Map> gamePlayers = game.getGamePlayers().stream().map(gamePlayer -> getGamePlayerMap(gamePlayer)).collect(toList());
        gameMap.put("gamePlayers", gamePlayers);

        return gameMap;

    }

    //Create the Map for each game players using id and email address.
    private Map getPlayerMap(Player player){

        Map gameMap = new LinkedHashMap();

        gameMap.put("id", player.getId());
        gameMap.put("email", player.getUserName());

        return gameMap;

    }

    //Create gamePlayer Map which would be nested with the players from getPlayerMap.
    private Map getGamePlayerMap(GamePlayer gamePlayer){

        Map gameMap = new LinkedHashMap();

        gameMap.put("id", gamePlayer.getId());
        Map player = getPlayerMap(gamePlayer.getPlayer());
        gameMap.put("player", player);

        return gameMap;
    }

    //Create a Map for playerDTO(Data transfer object)
    private Map playerMap(Player player){

        Map playerDTO = new LinkedHashMap();
        playerDTO.put("id", player.getId());
        playerDTO.put("username", player.getUserName());

        return playerDTO;

    }

}

