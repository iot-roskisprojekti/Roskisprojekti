package fi.roskisprojekti.domain.common;

import fi.roskisprojekti.domain.validation.DomainValidator;

import java.time.Instant;

public record EndTimeStamp(Instant value) {
    public EndTimeStamp {
        DomainValidator.isValidTimeStamp(value);
    }
    public static EndTimeStamp now() {
        return new EndTimeStamp(Instant.now());
    }
}
