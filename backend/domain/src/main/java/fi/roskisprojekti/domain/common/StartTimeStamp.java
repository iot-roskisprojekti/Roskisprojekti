package fi.roskisprojekti.domain.common;

import fi.roskisprojekti.domain.validation.DomainValidator;

import java.time.Instant;

public record StartTimeStamp(Instant value) {
    public StartTimeStamp{
        DomainValidator.isValidTimeStamp(value);
    }

    public static StartTimeStamp now() {
        return new StartTimeStamp(Instant.now());
    }
}
