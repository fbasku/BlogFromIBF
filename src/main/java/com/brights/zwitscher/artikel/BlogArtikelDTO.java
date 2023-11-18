package com.brights.zwitscher.artikel;

import com.brights.zwitscher.kommentar.Kommentar;
import com.brights.zwitscher.user.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BlogArtikelDTO {
    private Long id;
    private String titel;
    private String inhalt;
    private LocalDate datum;
    private String bildUrl;
    private User verfasser;
    private List<Kommentar> kommentare;

    public BlogArtikelDTO() {

    }

    public BlogArtikelDTO(Long id, String titel, String inhalt, LocalDate datum, String bildUrl, User verfasser, List<Kommentar> kommentare) {
        this.id = id;
        this.titel = titel;
        this.inhalt = inhalt;
        this.datum = datum;
        this.bildUrl = bildUrl;
        this.verfasser = verfasser;
        this.kommentare = kommentare;
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

    public String getDatum() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return datum.format(formatter);
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public User getVerfasser() {
        return verfasser;
    }

    public void setVerfasser(User verfasser) {
        this.verfasser = verfasser;
    }

    public List<Kommentar> getKommentare() {
        return kommentare;
    }

    public void setKommentare(List<Kommentar> kommentare) {
        this.kommentare = kommentare;
    }

}