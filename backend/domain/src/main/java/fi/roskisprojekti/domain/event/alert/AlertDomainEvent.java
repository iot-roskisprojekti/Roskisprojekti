package fi.roskisprojekti.domain.event.alert;

import fi.roskisprojekti.domain.entity.alert.AlertId;
import fi.roskisprojekti.domain.entity.bin.BinId;

import java.time.Instant;

public sealed interface AlertDomainEvent permits AlertCreatedEvent, AlertClosedEvent{
    AlertId alertId();
    BinId binId();
    Instant occurredAt();
}
