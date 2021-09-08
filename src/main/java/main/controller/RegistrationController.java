package main.controller;

import lombok.AllArgsConstructor;
import main.api.request.DTOErrorDescription;
import main.api.request.RegistrationRequest;
import main.domain.User;
import main.domain.enums.Role;
import main.repos.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final UserRepository userRepo;

    @RequestMapping("/registration")
    public String registration() {
        return "registration";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(LoginRequest loginRequest) {
//
//        System.out.println(loginRequest.toString());
//
//
//        return new ResponseEntity<>(loginRequest, HttpStatus.OK);
//    }


    @PostMapping("/registration")
    public ResponseEntity<?> regUser(RegistrationRequest registrationRequest) {

        System.out.println(registrationRequest.toString());
        if (registrationRequest.getUsername().equals("")
                || registrationRequest.getEmail().equals("")
                || registrationRequest.getPassword().equals("")) {
            return ResponseEntity.status(400).body(
                    DTOErrorDescription.NULL.get());
        }

        Optional<User> user = userRepo.findByEmail(registrationRequest.getEmail());

        if (user.isPresent()) {
            return ResponseEntity.status(400).body(
                    DTOErrorDescription.EXIST.get());
        }

        User newUser = new User();
        newUser.setEmail(registrationRequest.getEmail());
        newUser.setName(registrationRequest.getUsername());
        newUser.setPassword(new BCryptPasswordEncoder().encode(registrationRequest.getPassword()));
        newUser.setType(Role.USER);
        newUser.setActive(true);
        userRepo.save(newUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}