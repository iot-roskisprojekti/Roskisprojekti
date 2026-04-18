package fi.roskisprojekti.bootstrap.adapter;

import fi.roskisprojekti.adapter.out.notification.email.EmailAdapter;
import fi.roskisprojekti.application.port.out.notification.NotificationPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender; // Lisää tämä import

@Configuration
public class NotificationAdapterConfig {

    @Bean
    public NotificationPort notificationPort(JavaMailSender mailSender) {
        // Annetaan mailSender adapterille
        return new EmailAdapter(mailSender);
    }
} 