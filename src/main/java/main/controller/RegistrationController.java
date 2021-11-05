package main.controller;

import lombok.AllArgsConstructor;
import main.api.request.RegistrationRequest;
import main.domain.User;
import main.domain.enums.Role;
import main.repos.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserRepository userRepository;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String regUser(RegistrationRequest registrationRequest) {

        System.out.println(registrationRequest.toString());

        if (registrationRequest.getUsername().equals("")
                || registrationRequest.getEmail().equals("")
                || registrationRequest.getPassword().equals("")) {
            return "/registration";
        }

        Optional<User> user = userRepository.findByEmail(registrationRequest.getEmail());

        if (user.isPresent()) {
            return "/registration";
        }

        User newUser = new User();
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setName(registrationRequest.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(registrationRequest.getPassword()));
        newUser.setType(Role.USER);
        newUser.setActive(true);
        userRepository.save(newUser);
        return "/login";
    }

}