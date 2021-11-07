package main.controller;

import lombok.AllArgsConstructor;
import main.api.request.UrlRequest;
import main.api.response.UrlsListResponse;
import main.domain.User;
import main.domain.UserUrls;
import main.repos.UserRepository;
import main.repos.UserUrlsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("url")
public class UrlController {

    private final UserUrlsRepository userUrlsRepository;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAll(Principal principal) {

        Optional<User> findUser = userRepository.findByEmail(principal.getName());

        List<UserUrls> userUrls = userUrlsRepository.findByAuthor(findUser.get());
        List<UrlsListResponse> urlsListResponses = new ArrayList<>();

        for (int i = 0; i < userUrls.size(); i++) {
            urlsListResponses.add(new UrlsListResponse(
                    userUrls.get(i).getId(),
                    userUrls.get(i).getSourceUrl(),
                    userUrls.get(i).getDestinationUrl()
                    ));
        }

        return new ResponseEntity<>(urlsListResponses, HttpStatus.OK);
    }

    //Save
    @PostMapping
    public ResponseEntity<?> save(@RequestBody UrlRequest urlRequest, Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Optional<User> currentUser = userRepository.findByEmail(principal.getName());

        if (urlRequest.getText() != null && currentUser.isPresent()) {
            UserUrls userUrls = new UserUrls();
            userUrls.setAuthor(currentUser.get());
            userUrls.setSourceUrl(urlRequest.getText());
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");

            userUrls.setDestinationUrl(uuid);

            userUrlsRepository.save(userUrls);

            urlRequest.setId(userUrls.getId());
            urlRequest.setDestinationUrl(uuid);
            urlRequest.setSourceUrl(urlRequest.getText());

            return new ResponseEntity<>(urlRequest, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable String id, Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Optional<User> currentUser = userRepository.findByEmail(principal.getName());
        if (id != null && currentUser.isPresent()) {

            Optional<UserUrls> userUrls = userUrlsRepository.findUrlById(Long.parseLong(id));

            if (userUrls.isPresent()) {
                if (currentUser.get().getId().equals(userUrls.get().getAuthor().getId())) {
                    userUrlsRepository.delete(userUrls.get());
                    return new ResponseEntity<>(id, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
                }
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}