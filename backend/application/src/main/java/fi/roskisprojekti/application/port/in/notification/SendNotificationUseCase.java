package fi.roskisprojekti.application.port.in.notification;

public interface SendNotificationUseCase {
    // Lisätään parametrit: kuka ja missä
    void send(String binName, String siteName);
}
