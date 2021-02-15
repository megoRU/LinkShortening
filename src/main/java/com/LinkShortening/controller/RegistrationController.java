package com.LinkShortening.controller;

import com.LinkShortening.domain.Role;
import com.LinkShortening.domain.User;
import com.LinkShortening.repos.UserRepo;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

  @Autowired
  private UserRepo userRepo;

  @GetMapping("/registration")
  public String registration() {
    return "registration.mustache";
  }

  @PostMapping("/registration")
  public String addUser(User user, Map<String, Object> model) {
    User userRromDB = userRepo.findByUsername(user.getUsername());

    if (userRromDB != null) {
      model.put("message", "User exists!");
      return "registration";
    }

    user.setActive(true);
    user.setRoles(Collections.singleton(Role.USER));
    userRepo.save(user);

    return "redirect:/login";
  }

}
