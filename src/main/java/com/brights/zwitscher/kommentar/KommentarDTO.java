package com.brights.zwitscher.kommentar;

import java.time.Instant;

public class KommentarDTO {
    private Long id;
    private Instant datum;
    private String verfasserName;
    private String inhalt;

    public KommentarDTO() {
    }

    public KommentarDTO(Long id, Instant datum, String verfasserName, String inhalt) {
        this.id = id;
        this.datum = datum;
        this.verfasserName = verfasserName;
        this.inhalt = inhalt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDatum() {
        return datum;
    }

    public void setDatum(Instant datum) {
        this.datum = datum;
    }

    public String getVerfasserName() {
        return verfasserName;
    }

    public void setVerfasserName(String verfasserName) {
        this.verfasserName = verfasserName;
    }

    public String getInhalt() {
        return inhalt;
    }

    public void setInhalt(String inhalt) {
        this.inhalt = inhalt;
    }

}
