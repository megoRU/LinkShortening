package main.repos;

import main.domain.User;
import main.domain.UserUrls;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserUrlsRepository extends CrudRepository<UserUrls, Long> {

    List<UserUrls> findByAuthor(User author);

    @Query(value = "SELECT u FROM UserUrls u WHERE u.destinationUrl = :inputUrl")
    Optional<UserUrls> findSourceUrlByDestinationUrl(String inputUrl);


    @Query(value = "SELECT u FROM UserUrls u WHERE u.id = :id")
    Optional<UserUrls> findUrlById(Long id);

}