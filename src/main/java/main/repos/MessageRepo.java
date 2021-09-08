package main.repos;

import main.domain.Message;
import main.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {

  List<Message> findByAuthor(User author);

}
