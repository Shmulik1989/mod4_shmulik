package salvo.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//Create the repository as RESTrepository(Readable as a JSON on the browser).
@RepositoryRestResource
public interface PlayerRepository extends JpaRepository<Player, Long> {

}