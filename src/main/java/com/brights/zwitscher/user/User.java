package com.brights.zwitscher.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(unique = true)
    @NotBlank(message = "Benutzername erforderlich.")
    @Size(min = 4, max = 15, message = "Benutzername muss zwischen 4 und 15 Zeichen lang sein")
    private String username;

    @JsonIgnore
    @NotBlank (message = "Passwort nicht gesetzt.")
    @Size(min = 4, max = 15, message = "Passwort muss zwischen 4 und 15 Zeichen lang sein")
    private String password;

    private boolean isAdmin = false;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
