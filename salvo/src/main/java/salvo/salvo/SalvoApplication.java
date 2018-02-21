//Define the package we are currently on and which the class belongs to.
package salvo.salvo;

//Import the necessary services for this class from the Spring framework.
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;

@SpringBootApplication
public class SalvoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalvoApplication.class, args);

}

	@Bean
	public CommandLineRunner initData(PlayerRepository playerRepository, GameRepository gameRepository, GamePlayerRepository gamePlayerRepository, ShipRepository shipRepository) {
		return (String... args) -> {
			//Save new players to Player class.
			Player one = playerRepository.save(new Player("j.bauer@ctu.gov"));
			Player two = playerRepository.save(new Player("c.obrian@ctu.gov"));
			Player three = playerRepository.save(new Player("almeida@ctu.gov"));
			Player four = playerRepository.save(new Player("d.palmer@whitehouse.gov"));
			Player five = playerRepository.save(new Player("shmulik@gmail.com"));

			//Instantiate the games with date.
			Game game1 = gameRepository.save(new Game(new Date()));
			Game game2 = gameRepository.save(new Game(Date.from(new Date().toInstant().plusSeconds(3600))));
			Game game3 = gameRepository.save(new Game(Date.from(new Date().toInstant().plusSeconds(7200))));
			Game game4 = gameRepository.save(new Game(Date.from(new Date().toInstant().plusSeconds(10800))));
			Game game5 = gameRepository.save(new Game(Date.from(new Date().toInstant().plusSeconds(14400))));
			Game game6 = gameRepository.save(new Game(Date.from(new Date().toInstant().plusSeconds(18000))));

			//Instantiate the games and their players using gamePlayerRepository.
			GamePlayer gamePlayer1 = gamePlayerRepository.save(new GamePlayer(game1, one, new Date()));
			GamePlayer gamePlayer2 = gamePlayerRepository.save(new GamePlayer(game1, two, new Date()));
			GamePlayer gamePlayer3 = gamePlayerRepository.save(new GamePlayer(game2, one, (Date.from(new Date().toInstant().plusSeconds(3600)))));
			GamePlayer gamePlayer4 = gamePlayerRepository.save(new GamePlayer(game2, two,(Date.from(new Date().toInstant().plusSeconds(3600)))));
			GamePlayer gamePlayer5 = gamePlayerRepository.save(new GamePlayer(game3, two, (Date.from(new Date().toInstant().plusSeconds(7200)))));
			GamePlayer gamePlayer6 = gamePlayerRepository.save(new GamePlayer(game3, three, (Date.from(new Date().toInstant().plusSeconds(7200)))));
			GamePlayer gamePlayer7 = gamePlayerRepository.save(new GamePlayer(game4, one, (Date.from(new Date().toInstant().plusSeconds(10800)))));
			GamePlayer gamePlayer8 = gamePlayerRepository.save(new GamePlayer(game4, two, (Date.from(new Date().toInstant().plusSeconds(10800)))));
			GamePlayer gamePlayer9 = gamePlayerRepository.save(new GamePlayer(game5, three, (Date.from(new Date().toInstant().plusSeconds(14400)))));
			GamePlayer gamePlayer10 = gamePlayerRepository.save(new GamePlayer(game5, one, (Date.from(new Date().toInstant().plusSeconds(14400)))));
			GamePlayer gamePlayer11 = gamePlayerRepository.save(new GamePlayer(game6, four, (Date.from(new Date().toInstant().plusSeconds(18000)))));
			GamePlayer gamePlayer12 = gamePlayerRepository.save(new GamePlayer(game6, five, (Date.from(new Date().toInstant().plusSeconds(18000)))));

			//Instantiate ships and create ships locations for gamePlayer.
			Ship ship1 = new Ship(Ship.Type.Battleship, Arrays.asList("A1", "B1", "C1", "D1", "E1"));
			Ship ship2 = new Ship(Ship.Type.Cruiser, Arrays.asList("B1", "B2", "B3"));
			Ship ship3 = new Ship(Ship.Type.Submarine, Arrays.asList("A2", "B2", "C2", "D2"));
			Ship ship4 = new Ship(Ship.Type.Patrolboat, Arrays.asList("F2", "F3"));
			Ship ship5 = new Ship(Ship.Type.Destroyer, Arrays.asList("J1", "J2", "J3", "J4", "J5"));
			Ship ship6 = new Ship(Ship.Type.Battleship, Arrays.asList("C1", "C2", "C3", "C4", "C5"));
			Ship ship7 = new Ship(Ship.Type.Cruiser, Arrays.asList("D1", "D2", "D3"));
			Ship ship8 = new Ship(Ship.Type.Submarine, Arrays.asList("G1", "G2", "G3", "G4", "G5"));
			Ship ship9 = new Ship(Ship.Type.Patrolboat, Arrays.asList("H3", "H4"));
			Ship ship10 = new Ship(Ship.Type.Destroyer, Arrays.asList("J1", "J2", "J3", "J4", "J5"));

			//Add ships to players using the method in GamePlayer.
			gamePlayer1.addShip(ship1);
			gamePlayer1.addShip(ship2);
			gamePlayer1.addShip(ship3);
			gamePlayer2.addShip(ship4);
			gamePlayer2.addShip(ship5);
			gamePlayer2.addShip(ship6);
			shipRepository.save(ship1);
			shipRepository.save(ship2);
			shipRepository.save(ship3);
			shipRepository.save(ship4);
			shipRepository.save(ship5);
			shipRepository.save(ship6);

		};
	}
}
