package com.brights.zwitscher.artikel;

public class BlogArtikelResponseDTO {
    private String nachricht;

    public BlogArtikelResponseDTO(String nachricht) {
        this.nachricht = nachricht;
    }

    public String getNachricht() {
        return nachricht;
    }

    public void setNachricht(String nachricht) {
        this.nachricht = nachricht;
    }
}
