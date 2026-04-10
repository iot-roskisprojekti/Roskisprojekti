package fi.roskisprojekti.application.service.bin;

import fi.roskisprojekti.application.port.in.broker.DomainEventPublisher;
import fi.roskisprojekti.application.port.in.broker.DomainEventSubscriber;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.domain.entity.bin.Bin;
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
    /**
    @PostConstruct
    public void init() {
        subscriber.subscribeToBinTelemetryDomainEvents(this::onTelemetryReceived);
    }

    @Transactional
    public void onTelemetryReceived(BinTelemetryDomainEvent event) {
        if (event instanceof BinTelemetryReceivedEvent(
                fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry telemetry
        )) {

            Bin bin = binRepository.findById(telemetry.getBinId())
                    .orElseThrow(() -> new RuntimeException("Bin not found"));

            List<BinDomainEvent> binDomainEvents = bin.updateFromTelemetry(telemetry);

            binRepository.save(bin);

            binDomainEvents.forEach(publisher::publishBinDomainEvent);
        }
    }
    */
}