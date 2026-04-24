package fi.roskisprojekti.domain.event.bin;

import fi.roskisprojekti.domain.entity.bin.BinId;

import java.time.Instant;

public record BinStaleEvent(BinId binId, Instant occurredAt) implements BinDomainEvent {
}
