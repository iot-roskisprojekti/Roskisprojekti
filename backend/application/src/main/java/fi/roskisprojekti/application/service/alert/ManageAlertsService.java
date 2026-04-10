package fi.roskisprojekti.application.service.alert;

import fi.roskisprojekti.application.port.in.alert.ManageAlertsUseCase;
import fi.roskisprojekti.application.port.in.broker.DomainEventPublisher;
import fi.roskisprojekti.application.port.in.broker.DomainEventSubscriber;
import fi.roskisprojekti.application.port.out.persistence.AlertRepository;
import fi.roskisprojekti.domain.entity.alert.Alert;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.bin.BinEmptyingRequiredEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
public class ManageAlertsService implements ManageAlertsUseCase {
    private final AlertRepository alertRepository;
    /**
    private final DomainEventSubscriber eventSubscriber;
    private final DomainEventPublisher eventPublisher;
     */
    /**
    @PostConstruct
    public void init() {
        eventSubscriber.subscribeToBinDomainEvents(this::onBinEvent);
    }

    private void onBinEvent(BinDomainEvent event) {
        if (event instanceof BinEmptyingRequiredEvent e) {

            if(alertRepository.existsByBinIdAndAlertState())
            alertRepository.save(Alert.createNew());
        }
    }
    */
    @Override
    public List<Alert> findAll() {
        return alertRepository.findAll();
    }

    @Override
    public void save(Alert alert) {
        alertRepository.save(alert);
    }


}
