package fi.roskisprojekti.domain.entity.bin;

import java.time.Instant;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidTimeStamp;

public record LastUpdated(Instant value) {
    public LastUpdated{
        isValidTimeStamp(value, "Last updated ");
    }

}
