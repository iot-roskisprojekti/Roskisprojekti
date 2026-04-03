package fi.roskisprojekti.domain.entity.message;

import java.time.Instant;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidTimeStamp;

public record SentAt(Instant value) {
    public SentAt{
        isValidTimeStamp(value, "Sent at");
    }
}
