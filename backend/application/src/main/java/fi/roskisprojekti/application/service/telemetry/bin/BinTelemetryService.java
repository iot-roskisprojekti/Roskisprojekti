package fi.roskisprojekti.application.service.telemetry.bin;

import fi.roskisprojekti.application.port.in.broker.DomainEventPublisher;
import fi.roskisprojekti.application.port.out.persistence.AlertRepository;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.application.port.out.persistence.BinTelemetryRepository;
import fi.roskisprojekti.application.port.out.persistence.TaskRepository;
import fi.roskisprojekti.application.service.event.InternalDomainEventBroker;
import fi.roskisprojekti.domain.entity.alert.Alert;
import fi.roskisprojekti.domain.entity.alert.AlertState;
import fi.roskisprojekti.domain.entity.alert.AlertType;
import fi.roskisprojekti.domain.entity.bin.Bin;
import fi.roskisprojekti.domain.entity.bin.BinStatus;
import fi.roskisprojekti.domain.entity.employee.EmployeeId;
import fi.roskisprojekti.domain.entity.task.Task;
import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;
import fi.roskisprojekti.domain.event.telemetry.bin.BinTelemetryReceivedEvent;
import lombok.RequiredArgsConstructor;
import fi.roskisprojekti.application.port.in.telemetry.bin.HandleInBoundTelemetryUseCase;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class BinTelemetryService implements HandleInBoundTelemetryUseCase {

    private final BinTelemetryRepository telemetryRepository;
    private final BinRepository binRepository;
    private final AlertRepository alertRepository;
    private final TaskRepository taskRepository;
    private final DomainEventPublisher domainEventPublisher;
    @Override
    @Transactional
    public void handleBinTelemetry(BinTelemetry telemetry) {
        telemetryRepository.save(telemetry);
        
        BinTelemetryReceivedEvent event = new BinTelemetryReceivedEvent(telemetry);
        domainEventPublisher.publishBinTelemetryDomainEvent(event);
         

        Bin bin = binRepository.findById(telemetry.getBinId())
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        bin.updateFromTelemetry(telemetry);
        binRepository.save(bin);

        boolean exists;

        if (bin.getBinStatus() == BinStatus.NEEDS_EMPTYING) {

            exists = alertRepository.existsByBinIdAndAlertState(bin.getBinId(), AlertState.OPEN);

            if (!exists) {
                Alert newAlert = Alert.createNew(bin.getBinId(), AlertType.ALERT);
                Alert savedAlert = alertRepository.save(newAlert);

                Task newTask = Task.createNew(savedAlert.getAlertId(), new EmployeeId(1L));
                taskRepository.save(newTask);


            }
        } else if (bin.getBinStatus() == BinStatus.STALE_TELEMETRY) {
            exists = alertRepository.existsByBinIdAndAlertState(bin.getBinId(), AlertState.OPEN);

            if (!exists) {
                Alert staleAlert = Alert.createNew(bin.getBinId(), AlertType.WARNING);
                alertRepository.save(staleAlert);

            }
        }
    }

}


