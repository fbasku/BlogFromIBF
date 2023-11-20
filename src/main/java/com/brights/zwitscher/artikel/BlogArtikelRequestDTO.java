package com.brights.zwitscher.artikel;

public class BlogArtikelRequestDTO {
    private String titel;
    private String inhalt;
    private String bildUrl;

    public BlogArtikelRequestDTO() {}

    public BlogArtikelRequestDTO(String titel, String inhalt, String bildUrl) {
        this.titel = titel;
        this.inhalt = inhalt;
        this.bildUrl = bildUrl;
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
}
