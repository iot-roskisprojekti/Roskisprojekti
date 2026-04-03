package fi.roskisprojekti.adapter.in.mqtt.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
public record MeasurementMqttDto(
        @JsonProperty("binId") Long binId,
        @JsonProperty("distance") int distance,
        @JsonProperty("timestamp") Instant measuredAt
) {
}