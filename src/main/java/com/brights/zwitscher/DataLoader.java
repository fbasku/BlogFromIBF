package com.brights.zwitscher;

import com.brights.zwitscher.artikel.BlogArtikel;
import com.brights.zwitscher.artikel.BlogArtikelRepository;
import com.brights.zwitscher.user.User;
import com.brights.zwitscher.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private BlogArtikelRepository blogArtikelRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, BlogArtikelRepository blogArtikelRepository) {
        this.userRepository = userRepository;
        this.blogArtikelRepository = blogArtikelRepository;
    }

    public void run(ApplicationArguments args) {
        // Create user
        User user1= new User("user1","12345");
        userRepository.save(user1);

        // Create admin
        User user2= new User("admin","12345");
        user2.setAdmin(true);
        userRepository.save(user2);

        BlogArtikel blogArtikel1 = new BlogArtikel("skjhsdk","djskhgkjshksjhd",user1,"https://imgv3.fotor.com/images/homepage-feature-card/Car-PNG.jpg");
        blogArtikelRepository.save(blogArtikel1);
    }

}
