package main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String site() {
        return "site";
    }

    @GetMapping("/404")
    public String errorPage() {
        return "404";
    }

}
