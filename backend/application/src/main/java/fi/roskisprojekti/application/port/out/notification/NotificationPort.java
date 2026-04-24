package fi.roskisprojekti.application.port.out.notification;

public interface NotificationPort {
    // Lisää nämä kaksi parametria tänne!
    void send(String binName, String siteName);
}