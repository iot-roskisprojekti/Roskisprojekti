package fi.roskisprojekti.application.service.event;

import fi.roskisprojekti.application.port.in.broker.DomainEventBroker;
import fi.roskisprojekti.domain.event.alert.AlertDomainEvent;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.task.TaskDomainEvent;
import fi.roskisprojekti.domain.event.telemetry.bin.BinTelemetryDomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class InternalDomainEventBroker implements DomainEventBroker {

    private final List<Consumer<BinDomainEvent>> binSubscribers = new ArrayList<>();
    private final List<Consumer<BinTelemetryDomainEvent>> binTelemetrySubscribers = new ArrayList<>();
    private final List<Consumer<AlertDomainEvent>> alertSubscribers = new ArrayList<>();
    private final List<Consumer<TaskDomainEvent>> taskSubscribers = new ArrayList<>();

    @Override
    public void publishBinDomainEvent(BinDomainEvent event) {
        binSubscribers.forEach(subscriber -> invokeSubscriber(subscriber, event));
    }

    @Override
    public void publishBinTelemetryDomainEvent(BinTelemetryDomainEvent event) {
        binTelemetrySubscribers.forEach(subscriber -> invokeSubscriber(subscriber, event));
    }

    @Override
    public void publishTaskDomainEvent(TaskDomainEvent event) {
        taskSubscribers.forEach(subscriber -> invokeSubscriber(subscriber, event));
    }

    @Override
    public void publishAlertDomainEvent(AlertDomainEvent event) {
        alertSubscribers.forEach(subscriber -> invokeSubscriber(subscriber, event));
    }

    @Override
    public void subscribeToBinDomainEvents(Consumer<BinDomainEvent> subscriber) {
        this.binSubscribers.add(subscriber);
    }

    @Override
    public void subscribeToBinTelemetryDomainEvents(Consumer<BinTelemetryDomainEvent> subscriber) {
        this.binTelemetrySubscribers.add(subscriber);
    }

    @Override
    public void subscribeToAlertDomainEvents(Consumer<AlertDomainEvent> subscriber) {
        this.alertSubscribers.add(subscriber);
    }

    @Override
    public void subscribeToTaskDomainEvents(Consumer<TaskDomainEvent> subscriber) {
        this.taskSubscribers.add(subscriber);
    }

    private <T> void invokeSubscriber(Consumer<T> subscriber, T event) {
        try {
            subscriber.accept(event);
        } catch (Exception e) {

        }
    }

}
