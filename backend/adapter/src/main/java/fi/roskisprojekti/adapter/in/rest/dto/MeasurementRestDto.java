package fi.roskisprojekti.adapter.in.rest.dto;

import java.time.Instant;

public record MeasurementRestDto(Long binId, Instant measuredAt, double distance) {
}
