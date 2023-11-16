package com.brights.zwitscher.artikel;

import com.brights.zwitscher.kommentar.Kommentar;
import com.brights.zwitscher.kommentar.KommentarRepository;
import com.brights.zwitscher.kommentar.KommentarRequestDTO;
import com.brights.zwitscher.user.User;
import com.brights.zwitscher.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    public ResponseEntity<?> erstelleKommentar(@RequestBody KommentarRequestDTO kommentarDTO,
                                               @ModelAttribute("sessionUser") Optional<User> sessionUser) {
        if (!sessionUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Nicht angemeldet.");
        }

        User user = sessionUser.get();
        BlogArtikel blogArtikel = blogRepository.findById(kommentarDTO.getBlogArtikelId()).orElse(null);

        if (blogArtikel == null) {
            return ResponseEntity.badRequest().body("Blogartikel nicht gefunden");
        }

        Kommentar kommentar = new Kommentar(user, kommentarDTO.getInhalt(), blogArtikel);
        kommentarRepository.save(kommentar);

        return ResponseEntity.ok().body("Kommentar erfolgreich hinzugefügt");
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


}
