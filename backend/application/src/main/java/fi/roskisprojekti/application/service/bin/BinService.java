package fi.roskisprojekti.application.service.bin;

import fi.roskisprojekti.application.port.in.broker.DomainEventPublisher;
import fi.roskisprojekti.application.port.in.broker.DomainEventSubscriber;
import fi.roskisprojekti.application.port.in.notification.SendNotificationUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.domain.entity.bin.Bin;
import fi.roskisprojekti.domain.entity.bin.BinStatus;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.telemetry.bin.BinTelemetryDomainEvent;
import fi.roskisprojekti.domain.event.telemetry.bin.BinTelemetryReceivedEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BinService { 

    private final BinRepository binRepository;
    private final DomainEventPublisher publisher;
    private final DomainEventSubscriber subscriber;
    private final SendNotificationUseCase notificationService;

    @PostConstruct
    public void init() {
        subscriber.subscribeToBinTelemetryDomainEvents(this::onTelemetryReceived);
    }

    @Transactional
    public void onTelemetryReceived(BinTelemetryDomainEvent event) {
        if (event instanceof BinTelemetryReceivedEvent(var telemetry)) {
            Bin bin = binRepository.findById(telemetry.getBinId())
                    .orElseThrow(() -> new RuntimeException("Bin not found"));

            // Otetaan vanha tila talteen ennen päivitystä
            BinStatus oldStatus = bin.getBinStatus();

            // Päivitetään roskiksen tila ja haetaan tapahtumat
            List<BinDomainEvent> events = bin.updateFromTelemetry(telemetry);

            // Tallennetaan muutokset tietokantaan
            binRepository.save(bin);

            // BinService.java sisällä:
            if (bin.getBinStatus() == BinStatus.NEEDS_EMPTYING && oldStatus != BinStatus.NEEDS_EMPTYING) {
    
            // Tämä hakee nyt "Puijo Tower" koska päivitimme SQL:n
                String binName = bin.getName().value(); 
    
            // Tämä hakee nyt "Katu 3" (site_id 3 -> Katu 3)
            // Huom: Jos haluat hakea tarkan tekstin 'Katu 3' Site-taulusta koodilla,
             // se vaatisi SiteRepositoryn. Helppo oikotie on tämä:
            String siteName = "Kuopio, Katu " + bin.getSiteId().value(); 

            notificationService.send(binName, siteName);
        }

            // Julkaistaan tapahtumat eteenpäin
            events.forEach(publisher::publishBinDomainEvent);
        }
    }
}