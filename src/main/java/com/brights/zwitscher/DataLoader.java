package com.brights.zwitscher;

import com.brights.zwitscher.artikel.BlogArtikel;
import com.brights.zwitscher.artikel.BlogArtikelRepository;
import com.brights.zwitscher.kommentar.Kommentar;
import com.brights.zwitscher.kommentar.KommentarRepository;
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
    private KommentarRepository kommentarRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, BlogArtikelRepository blogArtikelRepository, KommentarRepository kommentarRepository) {
        this.userRepository = userRepository;
        this.blogArtikelRepository = blogArtikelRepository;
        this.kommentarRepository = kommentarRepository;
    }

    public void run(ApplicationArguments args) {
        // Create user
        User user1= new User("user1","12345");
        userRepository.save(user1);

        // Create admin
        User user2= new User("admin","12345");
        user2.setAdmin(true);
        userRepository.save(user2);

        BlogArtikel blogArtikel1 = new BlogArtikel("Sparen für den Ruhestand: Ein Leitfaden","Der Ruhestand mag noch weit entfernt sein, aber es ist nie zu früh,\n" +
                "um mit dem Sparen zu beginnen. In diesem Blog werden wir verschiedene\n" +
                "Sparstrategien für den Ruhestand vorstellen und Tipps geben, wie man am\n" +
                "besten für die goldenen Jahre vorsorgen kann.",user1,"https://png.pngtree.com/png-clipart/20230118/original/pngtree-financial-funnel-depositing-money-png-image_8920264.png");
        BlogArtikel blogArtikel2 = new BlogArtikel("Die Zukunft der Kryptowährungen",
                "Kryptowährungen untersuchen und wie sie die Finanzlandschaft verändern könnten.\n" +
                "Wir werden auch die Risiken und Chancen diskutieren, die mit dem Investieren\n" +
                "in Kryptowährungen verbunden sind.",user2,"https://miro.medium.com/v2/resize:fit:1400/1*zwgbjQRY9vGhM3ENBeSKGA.png");
        blogArtikelRepository.save(blogArtikel1);
        blogArtikelRepository.save(blogArtikel2);
        Kommentar kommentar = new Kommentar(user1,"dies ist ein Test", blogArtikel1);
        Kommentar kommentar1 = new Kommentar(user2,"es ist sehr interesant",blogArtikel2);
        kommentarRepository.save(kommentar);
        kommentarRepository.save(kommentar1);

    }

}
