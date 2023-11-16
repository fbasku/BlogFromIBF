package com.brights.zwitscher.kommentar;

import com.brights.zwitscher.user.User;

public class KommentarRequestDTO {
    private Long userId;
    private Long blogArtikelId;
    private String inhalt;

    public KommentarRequestDTO(Long userId, Long blogArtikelId, String inhalt) {
        this.userId = userId;
        this.blogArtikelId = blogArtikelId;
        this.inhalt = inhalt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBlogArtikelId() {
        return blogArtikelId;
    }

    public void setBlogArtikelId(Long blogArtikelId) {
        this.blogArtikelId = blogArtikelId;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }
}
