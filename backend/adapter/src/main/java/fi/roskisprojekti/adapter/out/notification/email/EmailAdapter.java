package fi.roskisprojekti.adapter.out.notification.email;

import fi.roskisprojekti.application.port.in.notification.SendNotificationUseCase;
import fi.roskisprojekti.application.port.out.notification.NotificationPort;
import org.springframework.stereotype.Component;

public class EmailAdapter implements NotificationPort {
    @Override
    public void send() {
    }
}
