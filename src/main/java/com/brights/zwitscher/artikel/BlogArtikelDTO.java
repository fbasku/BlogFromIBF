package com.brights.zwitscher.artikel;

import com.brights.zwitscher.kommentar.KommentarDTO;

import java.util.List;

public class BlogArtikelDTO {
    private Long id;
    private String titel;
    private String inhalt;
    private String bildUrl;
    private List<KommentarDTO> kommentare;

    public BlogArtikelDTO() {

    }

    public BlogArtikelDTO(Long id, String titel, String inhalt, String bildUrl, List<KommentarDTO> kommentare) {
        this.id = id;
        this.titel = titel;
        this.inhalt = inhalt;
        this.bildUrl = bildUrl;
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

    public String getBildUrl() {
        return bildUrl;
    }

    public void setBildUrl(String bildUrl) {
        this.bildUrl = bildUrl;
    }

    public List<KommentarDTO> getKommentare() {
        return kommentare;
    }

    public void setKommentare(List<KommentarDTO> kommentare) {
        this.kommentare = kommentare;
    }
}