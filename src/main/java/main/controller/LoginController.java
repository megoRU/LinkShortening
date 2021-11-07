package main.controller;

import lombok.AllArgsConstructor;
import main.api.request.LoginRequest;
import main.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login() {
        System.out.println("@GetMapping(\"/login\")");
        return "login";
    }

    @PostMapping("/api/login")
    public String login(LoginRequest loginRequest) throws Exception {
        boolean login = loginService.login(loginRequest);

        if (!(login)) {
            return "redirect:/login";
        } else {
            return "redirect:/main";
        }
    }
}
