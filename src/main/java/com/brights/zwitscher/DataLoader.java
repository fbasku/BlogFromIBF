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
        User user1 = new User("user1", "12345");
        userRepository.save(user1);

        User gamerX = new User("gamerX", "g@m3rP@ss");
        userRepository.save(gamerX);

        User codeNinja = new User("codeNinja", "c0d3N1nj@");
        userRepository.save(codeNinja);

        User stealthMaster = new User("stealthMaster", "sne@kyPass");
        userRepository.save(stealthMaster);

        User dataGuru = new User("dataGuru", "D@t@Guru123");
        userRepository.save(dataGuru);

        User cyberHero = new User("cyberHero", "Cyb3rH3r0P@ss");
        userRepository.save(cyberHero);

        User quantumCoder = new User("quantumCoder", "Qu@ntumC0d3");
        userRepository.save(quantumCoder);

        User techSavvy = new User("techSavvy", "T3chS@vvy123");
        userRepository.save(techSavvy);

        User infoSecPro = new User("infoSecPro", "Inf0S3cUr1ty");
        userRepository.save(infoSecPro);

        User machineMind = new User("machineMind", "M@ch1n3M1nd");
        userRepository.save(machineMind);

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
