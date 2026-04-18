package fi.roskisprojekti.application.service.notification;

import fi.roskisprojekti.application.port.in.notification.SendNotificationUseCase;
import fi.roskisprojekti.application.port.out.notification.NotificationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService implements SendNotificationUseCase {

    private final NotificationPort notificationPort;

    @Override
    public void send(String binName, String siteName) {
        // Välitetään parametrit eteenpäin adapterille
        notificationPort.send(binName, siteName);
    }
}