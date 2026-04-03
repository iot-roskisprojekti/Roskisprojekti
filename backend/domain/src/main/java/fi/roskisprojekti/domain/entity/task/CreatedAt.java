package fi.roskisprojekti.domain.entity.task;

import fi.roskisprojekti.domain.validation.DomainValidationException;

import java.time.Instant;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidTimeStamp;

public record CreatedAt(Instant value) {
    public CreatedAt{
        isValidTimeStamp(value, "Created at");

    }
}
