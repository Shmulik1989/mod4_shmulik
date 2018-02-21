package salvo.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.*;
import static java.util.stream.Collectors.toList;

//Tells SPRING to store the class objects in the database and retrieve the objects from the database.
@Entity
public class Game {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)

     private long id;
     private Date date;

    //Decalre a one to many relationship with GamePlayer.
    @OneToMany(mappedBy="game", fetch=FetchType.EAGER)
    private Set<GamePlayer> gamePlayers = new HashSet<>();

    public Game () { }

        public Game(Date date){
            this.date = date;
        }

        public Date getDate(){
         return date;

        }

        public Set<GamePlayer> getGamePlayers() {
        return gamePlayers;
        }
    @JsonIgnore
    public List<Game> getGames() {

        return gamePlayers.stream().map(gamePlayer -> gamePlayer.getGame()).collect(toList());
    }

    public long getId() {
        return id;
    }
}