package fi.roskisprojekti.domain.event.telemetry.bin;

import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;

public sealed interface BinTelemetryDomainEvent permits BinTelemetryReceivedEvent {
    BinTelemetry binTelemetry();
}
