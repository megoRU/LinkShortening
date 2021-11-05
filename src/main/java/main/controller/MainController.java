package main.controller;

import lombok.AllArgsConstructor;
import main.domain.Message;
import main.domain.User;
import main.repos.MessageRepo;
import main.security.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@Controller
@AllArgsConstructor
public class MainController {

    private final MessageRepo messageRepo;
    private final UserService userService;

    @GetMapping("/main")
    public String main(
            Principal principal,
            Map<String, Object> model) {

        if (principal == null) {
            System.out.println("redirect:/login");
            return "redirect:/login";
        }

        Iterable<Message> messages = messageRepo.findByAuthor(userService.getCurrentUser());

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
