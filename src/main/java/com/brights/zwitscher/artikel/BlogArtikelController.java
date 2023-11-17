package com.brights.zwitscher.artikel;
//As an administrator, I can name registered users as administrators so that they can support me in editing and managing the blog.<br>

import com.brights.zwitscher.kommentar.Kommentar;
import com.brights.zwitscher.kommentar.KommentarRepository;
import com.brights.zwitscher.kommentar.KommentarRequestDTO;
import com.brights.zwitscher.user.User;
import com.brights.zwitscher.user.UserDTO;
import com.brights.zwitscher.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class BlogArtikelController {

    private final BlogArtikelRepository blogRepository;
    private final KommentarRepository kommentarRepository;
    private final UserRepository userRepository;

    @Autowired
    public BlogArtikelController(BlogArtikelRepository blogRepository, KommentarRepository kommentarRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.kommentarRepository = kommentarRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/artikel")
    public List<BlogArtikelDTO> bekommeAlleArtikel() {
        Iterable<BlogArtikel> blogArtikelListe = blogRepository.findAll();
        List<BlogArtikelDTO> dtoListe = new ArrayList<>();

        for (BlogArtikel blogArtikel : blogArtikelListe) {
            BlogArtikelDTO dto = convertToDTO(blogArtikel);
            dtoListe.add(dto);
        }
        return dtoListe;
    }

    @PostMapping("/kommentar")
    public ResponseEntity<List<BlogArtikelDTO>> erstelleKommentar(@RequestBody KommentarRequestDTO kommentarDTO,
                                                                  @ModelAttribute("sessionUser") Optional<User> sessionUser) {
        if (!sessionUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        User user = sessionUser.get();
        BlogArtikel blogArtikel = blogRepository.findById(kommentarDTO.getBlogArtikelId()).orElse(null);

        if (blogArtikel == null) {
            return ResponseEntity.badRequest().build();
        }

        Kommentar kommentar = new Kommentar(user, kommentarDTO.getInhalt(), blogArtikel);
        kommentarRepository.save(kommentar);

        return ResponseEntity.ok(bekommeAlleArtikel());
    }

    // Hilfsmethode zur Konvertierung von BlogArtikel zu BlogArtikelDTO
    private BlogArtikelDTO convertToDTO(BlogArtikel blogArtikel) {

        return new BlogArtikelDTO(
                blogArtikel.getId(),
                blogArtikel.getTitel(),
                blogArtikel.getInhalt(),
                blogArtikel.getBildUrl(),
                blogArtikel.getKommentarList()
        );
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
