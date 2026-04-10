package fi.roskisprojekti.domain.common;

import fi.roskisprojekti.domain.validation.DomainValidator;
import java.time.Instant;

public record SingleTimeStamp(Instant value) {
    public SingleTimeStamp {
        DomainValidator.isValidTimeStamp(value);
    }

    public static SingleTimeStamp now() {
        return new SingleTimeStamp(Instant.now());
    }
}