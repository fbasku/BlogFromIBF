package com.brights.zwitscher.user;

import com.brights.zwitscher.artikel.BlogArtikel;
import com.brights.zwitscher.komentar.Kommentar;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private boolean isAdmin = false;

    @OneToMany(mappedBy = "user")
    private List<BlogArtikel> artikel = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Kommentar> kommentar = new ArrayList<>();

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
