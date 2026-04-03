package fi.roskisprojekti.domain.entity.task;

import fi.roskisprojekti.domain.validation.DomainValidationException;

import java.time.Instant;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidTimeStamp;

public record AssignedAt(Instant value) {
    public AssignedAt{
        isValidTimeStamp(value, "Assigned at");

    }
}
