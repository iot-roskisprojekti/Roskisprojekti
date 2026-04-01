package fi.roskisprojekti.adapter.in.rest.dto;

import java.time.Instant;

public record MeasurementRestDto(Instant measuredAt, double distance) {
}
