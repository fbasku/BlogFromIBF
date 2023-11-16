package com.brights.zwitscher.artikel;

import com.brights.zwitscher.kommentar.Kommentar;
import com.brights.zwitscher.kommentar.KommentarRepository;
import com.brights.zwitscher.kommentar.KommentarRequestDTO;
import com.brights.zwitscher.user.User;
import com.brights.zwitscher.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    @PostMapping("/artikel")
    public Kommentar fügeKommentar(@RequestBody KommentarRequestDTO DTO) {
        BlogArtikel blogArtikel = blogRepository.findById(DTO.getBlogArtikelId()).orElseThrow(()
                -> new NoSuchElementException("Blogartikel nicht gefunden"));
        User verfasser = userRepository.findById(DTO.getUserId()).orElseThrow(()
                -> new NoSuchElementException("Benutzer nicht gefunden"));

        Kommentar blogKommentar = new Kommentar();

        blogKommentar.setBlogArtikel(blogArtikel);
        blogKommentar.setVerfasser(verfasser);
        blogKommentar.setInhalt(DTO.getInhalt());
        blogKommentar.setDatum(Instant.now());

        kommentarRepository.save(blogKommentar);

        return blogKommentar;
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
