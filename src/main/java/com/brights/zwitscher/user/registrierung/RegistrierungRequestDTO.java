package com.brights.zwitscher.user.registrierung;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistrierungRequestDTO {
    @NotBlank(message = "Benutzername erforderlich.")
    @Size(min = 4, max = 15, message = "Benutzername muss zwischen 4 und 15 Zeichen lang sein")
    private String username;
    @NotBlank (message = "Passwort nicht gesetzt.")
    @Size(min = 4, max = 15, message = "Passwort muss zwischen 4 und 15 Zeichen lang sein")
    private String password;
    private String password2;

    public RegistrierungRequestDTO(String username, String password, String password2) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public boolean arePasswordsMatching() {
        return this.password != null && this.password.equals(this.password2);
    }
}
