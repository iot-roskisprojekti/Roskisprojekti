package fi.roskisprojekti.domain.entity.task;

import java.time.Instant;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidTimeStamp;

public record CompletedAt(Instant value) {
    public CompletedAt {
        isValidTimeStamp(value, "Completed at");

    }

}
