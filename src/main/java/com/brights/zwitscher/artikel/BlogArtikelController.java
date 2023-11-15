package com.brights.zwitscher.artikel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogArtikelController {

    private BlogArtikelRepository blogRepository;

    @Autowired
    public BlogArtikelController(BlogArtikelRepository blogRepository){
        this.blogRepository = blogRepository;
    }

    @GetMapping("/posts")
    public List<BlogArtikel> getalleArtikle() {
        return blogRepository.findAll();
    }
}
