package fi.roskisprojekti.adapter.in.rest.dto;

import java.time.Instant;

public record BinTelemetryRestDto(Long binId, Instant measuredAt, double distance) {
}
