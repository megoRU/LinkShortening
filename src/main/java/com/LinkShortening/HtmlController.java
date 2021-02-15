package com.LinkShortening;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping(value = "/")
    public String index() {
        return "main";
    }
}
