package main.repos;

import main.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String username);

    @Query("FROM User WHERE email = :email")
    Optional<User> findByEmail(@Param("email") String email);


}