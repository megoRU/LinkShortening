package com.LinkShortening.repos;

import com.LinkShortening.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
