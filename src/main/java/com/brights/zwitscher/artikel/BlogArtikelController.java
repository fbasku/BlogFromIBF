package com.brights.zwitscher.artikel;

import com.brights.zwitscher.kommentar.Kommentar;
import com.brights.zwitscher.kommentar.KommentarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping()
public class BlogArtikelController {

    private final BlogArtikelRepository blogRepository;

    @Autowired
    public BlogArtikelController(BlogArtikelRepository blogRepository){
        this.blogRepository = blogRepository;
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



    // Hilfsmethode zur Konvertierung von BlogArtikel zu BlogArtikelDTO
    private BlogArtikelDTO convertToDTO(BlogArtikel blogArtikel) {
        List<KommentarDTO> kommentarDTOs = new ArrayList<>();
        if (blogArtikel.getKommentarList() != null) {
            for (Kommentar kommentar : blogArtikel.getKommentarList()) {
                kommentarDTOs.add(new KommentarDTO(
                        kommentar.getId(),
                        kommentar.getDatum(),
                        kommentar.getVerfasser().getUsername(),
                        kommentar.getInhalt()
                ));
            }
        }

        return new BlogArtikelDTO(
                blogArtikel.getId(),
                blogArtikel.getTitel(),
                blogArtikel.getInhalt(),
                blogArtikel.getBildUrl(),
                kommentarDTOs
        );
    }
}
