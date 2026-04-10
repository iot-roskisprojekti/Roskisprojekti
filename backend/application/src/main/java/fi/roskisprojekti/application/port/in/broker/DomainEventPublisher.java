package fi.roskisprojekti.application.port.in.broker;

import fi.roskisprojekti.domain.event.alert.AlertDomainEvent;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.task.TaskDomainEvent;
import fi.roskisprojekti.domain.event.telemetry.bin.BinTelemetryDomainEvent;

public interface DomainEventPublisher {
    void publishBinDomainEvent(BinDomainEvent event);
    void publishBinTelemetryDomainEvent(BinTelemetryDomainEvent event);
    void publishTaskDomainEvent(TaskDomainEvent event);
    void publishAlertDomainEvent(AlertDomainEvent event);
}
