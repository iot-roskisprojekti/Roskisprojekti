package org.example.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailAdapter {
    private final JavaMailSender mailSender;

    public void sendAlert(String to, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        
        // LISÄÄ TÄMÄ RIVI:
        email.setFrom("roskisvahti@esimerkki.fi"); 
        
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }
}