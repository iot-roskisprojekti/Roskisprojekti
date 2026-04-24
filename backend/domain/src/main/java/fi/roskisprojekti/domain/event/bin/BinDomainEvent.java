package fi.roskisprojekti.domain.event.bin;

import fi.roskisprojekti.domain.entity.bin.BinId;

import java.time.Instant;

public sealed interface BinDomainEvent permits BinEmptyingRequiredEvent, BinStaleEvent{
    BinId binId();
    Instant occurredAt();
}
