package fi.roskisprojekti.application.port.in.broker;

import fi.roskisprojekti.domain.event.alert.AlertDomainEvent;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.task.TaskDomainEvent;
import fi.roskisprojekti.domain.event.telemetry.bin.BinTelemetryDomainEvent;

import java.util.function.Consumer;

public interface DomainEventSubscriber {
    void subscribeToBinDomainEvents(Consumer<BinDomainEvent> subscriber);
    void subscribeToBinTelemetryDomainEvents(Consumer<BinTelemetryDomainEvent> event);
    void subscribeToAlertDomainEvents(Consumer<AlertDomainEvent> event);
    void subscribeToTaskDomainEvents(Consumer<TaskDomainEvent> event);

}
