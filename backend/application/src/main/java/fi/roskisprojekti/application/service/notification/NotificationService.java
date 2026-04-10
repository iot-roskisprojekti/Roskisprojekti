package fi.roskisprojekti.application.service.notification;

import fi.roskisprojekti.application.port.in.notification.SendNotificationUseCase;
import fi.roskisprojekti.application.port.out.notification.NotificationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements SendNotificationUseCase {
    private final List<NotificationPort> notificationPorts; //Täällä on sms+email adapterit sim sa la bim
    @Override
    public void send() {


    }
}
