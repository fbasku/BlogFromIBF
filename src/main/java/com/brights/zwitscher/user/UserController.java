package com.brights.zwitscher.user;

import com.brights.zwitscher.user.registrierung.RegistrierungRequestDTO;
import com.brights.zwitscher.user.registrierung.RegistrierungResponseDTO;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Kein valider Login"));
        return new UserDTO(sessionUser.getUsername(), sessionUser.isAdmin());
    }

    @PostMapping("/registrieren")
    public RegistrierungResponseDTO registrieren(@RequestBody @Valid RegistrierungRequestDTO registrieren, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Benutzername muss zwischen 4 und 15 Zeichen lang sein");
        }

        if (!registrieren.arePasswordsMatching()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Passwörter stimmen nicht überein");
        }

        if (userRepository.findByUsername(registrieren.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Benutzername bereits vergeben");
        }

        User newUser = new User(registrieren.getUsername(), registrieren.getPassword());

        userRepository.save(newUser);

        return new RegistrierungResponseDTO(registrieren.getUsername());
    }
}