package com.brights.zwitscher.artikel;

import java.util.List;

public class BlogArtikelListeDTO {
    private Iterable<BlogArtikel> blogArtikelList;

    public BlogArtikelListeDTO(Iterable<BlogArtikel> blogArtikelList) {
        this.blogArtikelList = blogArtikelList;
    }

    public Iterable<BlogArtikel> getBlogArtikelList() {
        return blogArtikelList;
    }

    public void setBlogArtikelList(List<BlogArtikel> blogArtikelList) {
        this.blogArtikelList = blogArtikelList;
    }
}
