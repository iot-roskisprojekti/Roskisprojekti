package fi.roskisprojekti.domain.event.alert;

import fi.roskisprojekti.domain.entity.alert.AlertId;
import fi.roskisprojekti.domain.entity.bin.BinId;

import java.time.Instant;

public record AlertCreatedEvent(AlertId alertId, BinId binId, Instant occurredAt) implements  AlertDomainEvent {
}
