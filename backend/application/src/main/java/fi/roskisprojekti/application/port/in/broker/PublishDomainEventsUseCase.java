package fi.roskisprojekti.application.port.in.broker;

import fi.roskisprojekti.domain.event.alert.AlertDomainEvent;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.task.TaskDomainEvent;
import fi.roskisprojekti.domain.event.telemetry.bin.BinTelemetryDomainEvent;

public interface PublishDomainEventsUseCase {
    void publishBinEvent(BinDomainEvent event);
    void publishBinTelemetryEvent(BinTelemetryDomainEvent event);
    void publishTaskEvent(TaskDomainEvent event);
    void publishAlertEvent(AlertDomainEvent event);
}
