package fi.roskisprojekti.domain.entity.alert;

import java.time.Instant;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidTimeStamp;

public record ClosedAt(Instant value) {
    public ClosedAt {
        isValidTimeStamp(value, "Closed at");
    }
}
