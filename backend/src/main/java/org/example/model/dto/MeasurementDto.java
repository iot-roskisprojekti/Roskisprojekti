package org.example.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MeasurementDto(
        Long id, // id of measurement
        Long siteId,
        Double fillPercent,
        Long measuredAt
) {
}