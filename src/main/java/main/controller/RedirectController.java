package main.controller;

import lombok.AllArgsConstructor;
import main.domain.UserUrls;
import main.repos.UserUrlsRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class RedirectController {

    private final UserUrlsRepository userUrlsRepository;

    @GetMapping("/r/**")
    public String redirect(HttpServletRequest request) {

        Optional<UserUrls> userUrls = userUrlsRepository
                .findSourceUrlByDestinationUrl(request
                        .getRequestURL()
                        .toString()
                        .substring(request
                                .getRequestURL().toString().lastIndexOf("/") + 1));

        return userUrls.map(urls -> "redirect:" + urls.getSourceUrl()).orElse("redirect:/404");
    }
}
