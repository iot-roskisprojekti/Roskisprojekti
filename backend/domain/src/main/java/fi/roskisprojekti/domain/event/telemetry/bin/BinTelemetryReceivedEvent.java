package fi.roskisprojekti.domain.event.telemetry.bin;

import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;

public record BinTelemetryReceivedEvent(BinTelemetry binTelemetry) implements BinTelemetryDomainEvent {
}
