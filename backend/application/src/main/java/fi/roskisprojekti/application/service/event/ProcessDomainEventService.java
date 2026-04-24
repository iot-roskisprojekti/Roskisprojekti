package fi.roskisprojekti.application.service.event;

import fi.roskisprojekti.application.port.in.broker.ProcessDomainEventsUseCase;
import fi.roskisprojekti.application.port.out.persistence.AlertRepository;
import fi.roskisprojekti.application.port.out.persistence.TaskRepository;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.telemetry.bin.BinTelemetryReceivedEvent;
import lombok.RequiredArgsConstructor;
//Ihan turha, toistaiseksi?
@RequiredArgsConstructor
public class ProcessDomainEventService implements ProcessDomainEventsUseCase {
    private final AlertRepository alertRepository;
    private final TaskRepository taskRepository;

    @Override
    public void processBinEvent(BinDomainEvent binDomainEvent) {

    }

    @Override
    public void processBinTelemetryEvent(BinTelemetryReceivedEvent binTelemetryReceivedEvent) {

    }

    @Override
    public void processTaskEvent() {

    }

    @Override
    public void processALertEvent() {

    }

    @Override
    public void processMessageEvent() {

    }
}
