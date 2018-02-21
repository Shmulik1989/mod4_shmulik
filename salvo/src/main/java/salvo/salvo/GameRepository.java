package salvo.salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//Define a RESTrepository.
@RepositoryRestResource
public interface GameRepository extends JpaRepository<Game, Long> {

}



