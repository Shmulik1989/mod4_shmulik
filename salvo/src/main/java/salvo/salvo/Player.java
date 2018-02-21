//Define the package we are currently on and which the class belongs to.
package salvo.salvo;
//Import the necessary services for this class from the JPA.
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import static java.util.stream.Collectors.toList;

//Tells SPRING to store the class objects in the database and retrieve the objects from the database.
@Entity

//Create the class with a public access modifier(meaning other classes and services can use it within and outside of the package including subclasses.
public class Player {

    //Orders Spring to automaticly generate an ID value to the class instances in the database.
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String userName;

    @OneToMany(mappedBy="player", fetch=FetchType.EAGER)
    private Set<GamePlayer> gamePlayers = new HashSet<>();

    //Create a no-argument default constructor for class Player.
    public Player() { }

        //Create a class constructor with String userName as an argument.
        public Player(String userName){

            this.userName = userName;
        }

        //A getter to "get" the username from the variable userName.
        public String getUserName() {

        return userName;

        }

        public long getId() {

            return id;
        }

    @JsonIgnore
    public List<Player> getPlayers() {
        return gamePlayers.stream().map(gamePlayer -> gamePlayer.getPlayer()).collect(toList());
    }

    }