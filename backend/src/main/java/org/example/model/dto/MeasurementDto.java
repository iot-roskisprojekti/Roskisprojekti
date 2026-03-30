package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MeasurementDto(
        Long id, // id of measurement
        @JsonProperty(value = "binId", required = true)
        Long siteId,
        @JsonProperty(value = "fillPercent", required = true)
        Double fillPercent,
        @JsonProperty(value = "timestamp", required = true)
        LocalDateTime measuredAt
) {
}