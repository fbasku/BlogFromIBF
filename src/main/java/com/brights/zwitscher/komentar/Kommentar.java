package com.brights.zwitscher.komentar;

import com.brights.zwitscher.artikel.BlogArtikel;
import com.brights.zwitscher.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
public class Kommentar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant datum;
    @ManyToOne
    @JsonIgnore
    private User user;
    private String inhalt;

    @ManyToOne
    @JsonIgnore
    private BlogArtikel blogArtikel;
}
