package fi.roskisprojekti.domain.event.bin;

import fi.roskisprojekti.domain.entity.bin.BinId;
import fi.roskisprojekti.domain.entity.bin.BinStatus;
import fi.roskisprojekti.domain.entity.bin.FillLevel;


public record BinUpdatedEvent(BinId binId, BinStatus newStatus, FillLevel fillLevel, java.time.Instant occurredAt) {

}
