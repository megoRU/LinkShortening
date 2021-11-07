package main.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {

    @GetMapping("/main")
    public String main(Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        return "main";
    }
}
