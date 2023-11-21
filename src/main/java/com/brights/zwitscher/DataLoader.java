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
import java.util.stream.IntStream;

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
        User user1 = new User("User1", "12345");
        userRepository.save(user1);

        // Create admin
        User user2= new User("Admin","12345");
        user2.setAdmin(true);
        userRepository.save(user2);;

        User admin1 = new User("FinanzMeister", "AdminPass123");
        admin1.setAdmin(true);
        userRepository.save(admin1);

        User admin2 = new User("InvestorGenie", "SecurePass456");
        admin2.setAdmin(true);
        userRepository.save(admin2);

        User admin3 = new User("MarktStratege", "MarketPass789");
        admin3.setAdmin(true);
        userRepository.save(admin3);

        // Create Regular Users
        String[] userNames = {"GeldGuru", "AktienAss", "RisikoRechner", "KreditKönig",
                "DepotDoktor", "FondsFee", "BörsenBaron", "VermögenVirtuose", "KapitalKapitän",
                "AnlageMeister", "ZinsZauberer", "DividendenDuke", "SparSultan",
                "RenteRanger"};

        for (String userName : userNames) {
            User user = new User(userName, "UserPass" + userName.hashCode());
            userRepository.save(user);
        }

        BlogArtikel blogArtikel1 = new BlogArtikel( "Die Auswirkungen der Zinserhöhung auf Kredite und Spareinlagen",
                "Angesichts der jüngsten Zinserhöhung durch die Europäische Zentralbank untersuchen wir, wie sich dies auf Kredite und Spareinlagen auswirkt. Höhere Zinsen können sowohl Vorteile für Sparer als auch Herausforderungen für Kreditnehmer bedeuten. In diesem Artikel betrachten wir verschiedene Szenarien und geben Tipps, wie man sich am besten anpasst.",
                user1, "https://i0.web.de/image/492/38175492,pd=2/europaeische-zentralbank-frankfurt-main.jpg"
        );
        blogArtikelRepository.save(blogArtikel1);

        BlogArtikel blogArtikel3 = new BlogArtikel(
                "Kryptowährungen: Risiken und Chancen",
                "Kryptowährungen sind ein heißes Thema in der Finanzwelt. Doch welche Risiken und Chancen sind mit dieser neuen Art von Währung verbunden? In diesem Artikel analysieren wir die Volatilität, die Sicherheitsaspekte und das Potenzial von Kryptowährungen im aktuellen Wirtschaftsklima.",
                admin2, "https://coinsundtokens.com/wp-content/uploads/2020/02/Kryptow%C3%A4hrungen.jpg"
        );
        blogArtikelRepository.save(blogArtikel3);



        BlogArtikel blogArtikel2 = new BlogArtikel("Sparen für den Ruhestand: Ein Leitfaden","Der Ruhestand mag noch weit entfernt sein, aber es ist nie zu früh,\n" +
                "um mit dem Sparen zu beginnen. In diesem Blog werden wir verschiedene\n" +
                "Sparstrategien für den Ruhestand vorstellen und Tipps geben, wie man am\n" +
                "besten für die goldenen Jahre vorsorgen kann.",user1,"https://media.deutsche-handwerks-zeitung.de/uploads/2021/07/AdobeStock_127075319-scaled-1024x683.jpeg");
        blogArtikelRepository.save(blogArtikel2);


        BlogArtikel blogArtikel4 = new BlogArtikel(
                "Nachhaltige Investments: Trend oder Zukunft?",
                "Nachhaltige Investments gewinnen an Beliebtheit. Doch handelt es sich dabei nur um einen kurzfristigen Trend oder ist Nachhaltigkeit ein dauerhafter Bestandteil der Finanzwelt? Dieser Artikel beleuchtet die Entwicklung grüner Investments und deren langfristige Auswirkungen auf den Markt.",
                admin1, "https://finment.com/wp-content/uploads/2021/06/nachhaltige-aktien-2021_gruene_aktien_energie_aktien_cleantech-aktien_aktien-der-zukunft_aktien-erneuerbare-energien_header.jpg"
        );
        blogArtikelRepository.save(blogArtikel4);

        BlogArtikel blogArtikel5 = new BlogArtikel("Die Zukunft der Kryptowährungen",
                "Die Zukunft der Kryptowährungen ist vielversprechend. " +
                        "Ihr Einzug in den Mainstream-Finanzmarkt wächst, unterstützt von Regierungen und Institutionen. " +
                        "Regulierung wird wichtiger für Vertrauen, während technologische Fortschritte wie skalierbare Blockchains und Nachhaltigkeit ihr Potenzial erweitern. " +
                        "Kryptowährungen könnten die Finanzinklusion weltweit vorantreiben. Trotz Herausforderungen bleibt ihre Zukunft dank Fokus und Wachstumspotenzial vielversprechend.",admin2,"https://miro.medium.com/v2/resize:fit:1400/1*zwgbjQRY9vGhM3ENBeSKGA.png");

        blogArtikelRepository.save(blogArtikel5);



        // Kommentare
        kommentarRepository.save(new Kommentar(user1, "Interessanter Artikel! Gute Zeit, um Sparanlagen zu überdenken.", blogArtikel1));
        kommentarRepository.save(new Kommentar(user2, "Kreditnehmer müssen jetzt aufpassen. Höhere Zinsen bedeuten höhere Kosten.", blogArtikel1));
        kommentarRepository.save(new Kommentar(admin3, "Guter Überblick über die Auswirkungen der Zinserhöhung.", blogArtikel1));

        kommentarRepository.save(new Kommentar(admin3, "Nachhaltigkeit ist definitiv die Zukunft der Finanzen.", blogArtikel4));
        kommentarRepository.save(new Kommentar(admin2, "Interessant, aber es bleibt abzuwarten, wie sich die Renditen entwickeln.", blogArtikel4));
        kommentarRepository.save(new Kommentar(admin1, "Guter Einblick in grüne Investments.", blogArtikel4));
        kommentarRepository.save(new Kommentar(admin3, "Mehr Unternehmen sollten nachhaltige Praktiken übernehmen.", blogArtikel4));

        kommentarRepository.save(new Kommentar(user1, "Krypto bleibt ein riskantes Spiel.", blogArtikel3));
        kommentarRepository.save(new Kommentar(user2, "Großes Potenzial, aber man muss vorsichtig sein.", blogArtikel3));
        kommentarRepository.save(new Kommentar(admin2, "Interessante Perspektiven auf Kryptos.", blogArtikel3));
        kommentarRepository.save(new Kommentar(user1, "Nichts für schwache Nerven!", blogArtikel3));
        kommentarRepository.save(new Kommentar(user2, "Gut, über die Risiken aufzuklären.", blogArtikel3));




        Kommentar kommentar = new Kommentar(user1,"Super Artikel!", blogArtikel1);
        Kommentar kommentar1 = new Kommentar(user2,"es ist sehr interesant",blogArtikel2);

        Kommentar kommentar2 = new Kommentar(admin3,"Bei Krypto bin ich immer vorsichtig!",blogArtikel5);
        Kommentar kommentar3 = new Kommentar(admin3,"Die Rente allein wird leider nicht genug sein.",blogArtikel2);
        kommentarRepository.save(new Kommentar(user2, "Großes Potenzial, aber man muss vorsichtig sein.", blogArtikel5));
        kommentarRepository.save(new Kommentar(admin2, "Interessante Perspektiven auf Kryptos.", blogArtikel5));
        kommentarRepository.save(kommentar);
        kommentarRepository.save(kommentar1);
        kommentarRepository.save(kommentar2);
        kommentarRepository.save(kommentar3);

    }

}
