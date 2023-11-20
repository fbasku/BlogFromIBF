package com.brights.zwitscher.artikel;
//As an administrator, I can name registered users as administrators so that they can support me in editing and managing the blog.<br>

import com.brights.zwitscher.kommentar.Kommentar;
import com.brights.zwitscher.kommentar.KommentarRepository;
import com.brights.zwitscher.kommentar.KommentarRequestDTO;
import com.brights.zwitscher.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class BlogArtikelController {

    private final BlogArtikelRepository blogRepository;
    private final KommentarRepository kommentarRepository;


    @Autowired
    public BlogArtikelController(BlogArtikelRepository blogRepository, KommentarRepository kommentarRepository) {
        this.blogRepository = blogRepository;
        this.kommentarRepository = kommentarRepository;
    }

    @GetMapping("/artikel")
    public List<BlogArtikelDTO> bekommeAlleArtikel() {
        Iterable<BlogArtikel> blogArtikelListe = blogRepository.findAll();
        List<BlogArtikelDTO> dtoListe = new ArrayList<>();

        for (BlogArtikel blogArtikel : blogArtikelListe) {
            BlogArtikelDTO dto = convertToDTO(blogArtikel);
            dtoListe.add(dto);
        }
        dtoListe.sort(Comparator.comparing(BlogArtikelDTO::getId, Comparator.reverseOrder()));

        return dtoListe;
    }

    @PostMapping("/artikel")
    public ResponseEntity<String> erstelleArtikel(@RequestBody BlogArtikelRequestDTO artikelDTO,
                                                  @ModelAttribute("sessionUser") Optional<User> sessionUser) {
        if (!sessionUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Benutzer ist nicht angemeldet.");
        }

        // Standardbild, falls keine Bild-URL angegeben ist
        String bildUrl = artikelDTO.getBildUrl();
        if (bildUrl.isEmpty()){
            bildUrl = "https://i.computer-bild.de/imgs/1/4/9/1/3/2/3/7/cb-finanzen-newsletter-a-a331c5e14e8a5164.jpg?impolicy=full_content";
        }

        BlogArtikel neuerArtikel = new BlogArtikel(
                artikelDTO.getTitel(),
                artikelDTO.getInhalt(),
                sessionUser.get(),
                bildUrl
        );
        blogRepository.save(neuerArtikel);

        return ResponseEntity.ok("Ihr Artikel wurde erfolgreich hinzugefügt.");
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
                blogArtikel.getDatum(),
                blogArtikel.getBildUrl(),
                blogArtikel.getVerfasser(),
                blogArtikel.getKommentarList()
        );
    }
}