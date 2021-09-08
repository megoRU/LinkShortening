package main.controller;

import lombok.AllArgsConstructor;
import main.domain.User;
import main.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserRepository userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userRromDB = userRepo.findByName(user.getName());

        if (userRromDB != null) {
            model.put("message", "User exists!");
            return "registration";
        }

        user.setActive(true);
        userRepo.save(user);

        return "redirect:/login";
    }

}
