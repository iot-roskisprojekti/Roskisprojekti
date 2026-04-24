package fi.roskisprojekti.adapter.out.notification.email;

import fi.roskisprojekti.application.port.out.notification.NotificationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAdapter implements NotificationPort {

    private final JavaMailSender mailSender;

    @Override
    public void send(String binName, String siteName) {
        System.out.println("DEBUG: Lähetetään ilmoitus roskiksesta: " + binName);
        
        try {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("roskis@projekti.fi");
        message.setTo("ruuhilahtimika@gmail.com"); 
        
        // Aiheeksi roskiksen nimi (otsikko)
        message.setSubject("⚠️ TYHJENNYS TILATTU: " + binName);
        
        // Rakennetaan viesti vastaamaan UI-näkymää
        String body = String.format(
            "⚠️ UUSI TYHJENNYSTEHTÄVÄ\n" +
            "------------------------------------------\n" +
            "KOHDE:    %s\n" +       // Esim. Kuopion Tori
            "SIJAINTI: %s\n" +       // Esim. Katu 1
            "------------------------------------------\n" +
            "TILA:     Täynnä\n" +
            "TOIMENPIDE: Tyhjennys tilattu automaattisesti.\n\n" +
            "Tämä on järjestelmän automaattinen ilmoitus.",
            binName.toUpperCase(), siteName // Tehdään kohteesta ISOILLA kirjaimilla selkeyden vuoksi
        );

        message.setText(body);
        mailSender.send(message);
        System.out.println("DEBUG: Tilannekuvan mukainen hälytys lähetetty!");
    } catch (Exception e) {
        System.err.println("VIRHE: Lähetys epäonnistui: " + e.getMessage());
    }
    } 
} 