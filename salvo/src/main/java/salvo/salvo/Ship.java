package salvo.salvo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

////Tells SPRING to store the class objects in the database and retrieve the objects from the database.
@Entity
public class Ship {

    //Orders Spring to generate an ID value to the class instances in the database.
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private Type type;

    @ElementCollection
    @Column(name="locations")
    private List<String> locations = new ArrayList<>();

    //Declare a many to one relationship with GamePlayer and Joincolumn with gamePlayer.
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="gamePlayer_id")
    GamePlayer gamePlayer;

    //Create a enum class with specific ship types.
    public enum Type{

        Destroyer,
        Patrolboat,
        Cruiser,
        Submarine,
        Battleship

    }

    //Create an empty constructor for Ship class.
    public Ship() { }

    //Declare a constructor with two arguments,Ship types and locations.
    public Ship(Type type, List locations ){

            this.type = type;
            this.locations = locations;

    }

    //getters and setters

    @JsonIgnore
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<String> getLocations() {
        return locations;
    }

    public void setLocations(List<String> locations) {
        this.locations = locations;
    }

    @JsonIgnore
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

}
