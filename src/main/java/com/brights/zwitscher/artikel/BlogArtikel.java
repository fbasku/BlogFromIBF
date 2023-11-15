package com.brights.zwitscher.artikel;

import com.brights.zwitscher.komentar.Kommentar;
import com.brights.zwitscher.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
public class BlogArtikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titel;
    private String inhalt;

    public BlogArtikel(String titel, String inhalt, User user, String bildUrl) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.user = user;
        this.bildUrl = bildUrl;
    }

    public BlogArtikel() {
    }

    @ManyToOne
    @JsonIgnore
    private User user;

    private String  bildUrl;
    private Instant datum;


@OneToMany(mappedBy = "blogArtikel")
   private List<Kommentar> kommentarList;

}
