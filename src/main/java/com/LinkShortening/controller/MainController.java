package com.LinkShortening.controller;

import com.LinkShortening.domain.Message;
import com.LinkShortening.domain.User;
import com.LinkShortening.repos.MessageRepo;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @Autowired
  private MessageRepo messageRepo;

  @GetMapping("/")
  public String site(Map<String, Object> model) {
    return "site";
  }

  @GetMapping("/main")
  public String main(
      @AuthenticationPrincipal User user,
      Map<String, Object> model) {

    Iterable<Message> messages = messageRepo.findByAuthor(user);

    model.put("messages", messages);

    return "main";
  }

  @PostMapping("/main")
  public String add(
      @AuthenticationPrincipal User user,
      @RequestParam String text,
      Map<String, Object> model) {
    Message message = new Message(text, user);
    messageRepo.save(message);
    Iterable<Message> messages = messageRepo.findByAuthor(user);
    model.put("messages", messages);

    return "main";
  }

}
