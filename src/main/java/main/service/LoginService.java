package main.service;


import lombok.RequiredArgsConstructor;
import main.api.request.DTOErrorDescription;
import main.api.request.LoginRequest;
import main.domain.User;
import main.repos.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public boolean login(LoginRequest loginRequest) {
        Optional<User> findUser = userRepository.findByEmail(loginRequest.getEmail());

        if (findUser.isEmpty()) return false;

        User user = findUser.get();
        if (new BCryptPasswordEncoder(12).matches(loginRequest.getPassword(), user.getPassword())) {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(auth);

            return true;
        }
        return false;
    }
}
