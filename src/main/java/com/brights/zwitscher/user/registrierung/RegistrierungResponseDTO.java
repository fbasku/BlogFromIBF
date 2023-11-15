package com.brights.zwitscher.user.registrierung;

public class RegistrierungResponseDTO {
    private String username;

    public RegistrierungResponseDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
