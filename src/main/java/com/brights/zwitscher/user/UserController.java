package com.brights.zwitscher.user;

import com.brights.zwitscher.user.registrierung.RegistrierungRequestDTO;
import com.brights.zwitscher.user.registrierung.RegistrierungResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public UserDTO user(@ModelAttribute("sessionUser") Optional<User> sessionUserOptional) {
        User sessionUser = sessionUserOptional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No valid login"));
        return new UserDTO(sessionUser.getUsername(), sessionUser.isAdmin());
    }

    @PostMapping("/registrieren")
    public RegistrierungResponseDTO registrieren (@RequestBody RegistrierungRequestDTO registrieren, HttpServletResponse response) {
        Optional<User> userOptional = userRepository.findByUsername(registrieren.getUsername());

        if (userOptional.isEmpty()) {
            if(registrieren.getPassword().equals(registrieren.getPassword2())) {

                User user= new User(registrieren.getUsername(), registrieren.getPassword());
                userRepository.save(user);

                return new RegistrierungResponseDTO(registrieren.getUsername());
            }
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Passwort stimmt nicht überein");
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Benutzername schon vergeben");
    }
}