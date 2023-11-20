package com.brights.zwitscher.user;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
public class AdministratorController {

    private final UserRepository userRepository;

    @Autowired
    public AdministratorController (UserRepository userRepository) {

        this.userRepository = userRepository;
    }


    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> userDTOs = new ArrayList<>();
            Iterable<User> allUsers = userRepository.findAll();

            for (User user : allUsers) {
                UserDTO userDTO = new UserDTO(user.getUsername(), user.isAdmin());
                userDTOs.add(userDTO);
            }

            return ResponseEntity.ok(userDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Beispiel für die Behandlung von Datenbankfehlern
    @ExceptionHandler(org.springframework.dao.DataAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleDatabaseException(org.springframework.dao.DataAccessException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database error occurred");
    }

    @PostMapping("/users/{username}")
    public ResponseEntity<List<UserDTO>> aendereUserStatus(@PathVariable("username") String username) {
        try {
            User user = userRepository.findByUsername(username).orElse(null);

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            user.setAdmin(true);
            userRepository.save(user);

            return getAllUsers();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
