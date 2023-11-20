package com.brights.zwitscher.session;

import com.brights.zwitscher.user.registrierung.RegistrierungRequestDTO;
import com.brights.zwitscher.user.registrierung.RegistrierungResponseDTO;
import com.brights.zwitscher.user.User;
import com.brights.zwitscher.user.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

@RestController
public class    SessionController {

    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @Autowired
    public SessionController(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginRequestDTO login, HttpServletResponse response) {
        Optional<User> userOptional = userRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());

        if (userOptional.isPresent()) {
            //expires one week from now
            Session session = new Session(userOptional.get(), Instant.now().plusSeconds(7*24*60*60));
            sessionRepository.save(session);

            //store the session ID in a cookie
            Cookie cookie = new Cookie("sessionId", session.getId());
            response.addCookie(cookie);

            // Login successful
            return new LoginResponseDTO(login.getUsername());
        }

        // When login-does not work
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Benutzername oder Passwort ist falsch");
    }

    @PostMapping("/logout")
    public LogoutResponseDTO logout(@CookieValue(value = "sessionId", defaultValue = "") String sessionId, HttpServletResponse response) {
        Optional<Session> sessionOptional = sessionRepository.findByIdAndExpiresAtAfter(sessionId, Instant.now());
        // Delete session in database
        sessionOptional.ifPresent(session -> sessionRepository.delete(session));

        // Delete session cookie at client
        Cookie cookie = new Cookie("sessionId", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new LogoutResponseDTO();
    }


}
