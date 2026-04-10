package fi.roskisprojekti.application.port.in.broker;

import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.telemetry.bin.BinTelemetryReceivedEvent;

public interface ProcessDomainEventsUseCase {
    void processBinEvent(BinDomainEvent binDomainEvent);
    void processBinTelemetryEvent(BinTelemetryReceivedEvent binTelemetryReceivedEvent);
    void processTaskEvent();
    void processALertEvent();
    void processMessageEvent();
}
