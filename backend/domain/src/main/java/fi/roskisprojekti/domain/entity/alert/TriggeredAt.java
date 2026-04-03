package fi.roskisprojekti.domain.entity.alert;

import java.time.Instant;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidTimeStamp;

public record TriggeredAt(Instant value) {
    public TriggeredAt{
        isValidTimeStamp(value, "Triggered at");
    }
}
