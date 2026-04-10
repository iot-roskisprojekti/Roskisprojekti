package fi.roskisprojekti.bootstrap.adapter;

import fi.roskisprojekti.adapter.out.notification.email.EmailAdapter;
import fi.roskisprojekti.adapter.out.notification.sms.SmsAdapter;
import fi.roskisprojekti.application.port.out.notification.NotificationPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationAdapterConfig {

    @Bean
    public NotificationPort emailAdapter() {
        return new EmailAdapter();
    }

    @Bean
    public NotificationPort smsAdapter() {
        return new SmsAdapter();
    }
}