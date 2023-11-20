package com.brights.zwitscher.kommentar;

import com.brights.zwitscher.user.User;

public class KommentarRequestDTO {
    private String inhalt;

    public KommentarRequestDTO(Long userId, Long blogArtikelId, String inhalt) {

        this.inhalt = inhalt;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }
}
