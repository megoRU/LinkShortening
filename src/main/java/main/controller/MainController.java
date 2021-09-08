package main.controller;

import lombok.AllArgsConstructor;
import main.domain.Message;
import main.domain.User;
import main.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {

    private final MessageRepo messageRepo;

    @RequestMapping("/")
    public String site() {
        return "site";
    }

    @RequestMapping("/main")
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
