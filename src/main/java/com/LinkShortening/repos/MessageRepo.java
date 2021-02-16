package com.LinkShortening.repos;

import com.LinkShortening.domain.Message;
import com.LinkShortening.domain.User;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepo extends CrudRepository<Message, Long> {

  List<Message> findByAuthor(User author);

}
