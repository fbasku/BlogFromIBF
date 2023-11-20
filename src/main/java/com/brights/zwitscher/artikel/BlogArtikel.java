package com.brights.zwitscher.artikel;

import com.brights.zwitscher.kommentar.Kommentar;
import com.brights.zwitscher.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
public class BlogArtikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titel;
    @Size(min = 5, max = 500)
    private String inhalt;
    private LocalDate datum;
    private String  bildUrl;
    @ManyToOne
    private User verfasser;
    @OneToMany(mappedBy = "blogArtikel")
    private List<Kommentar> kommentarList;

    public BlogArtikel() {
    }

    public BlogArtikel(String titel, String inhalt, User user, String bildUrl) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.verfasser = user;
        this.bildUrl = bildUrl;
        this.datum = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

    public User getVerfasser() {
        return verfasser;
    }

    public void setVerfasser(User verfasser) {
        this.verfasser = verfasser;
    }

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public List<Kommentar> getKommentarList() {
        return kommentarList;
    }

    public void setKommentarList(List<Kommentar> kommentarList) {
        this.kommentarList = kommentarList;
    }
}
