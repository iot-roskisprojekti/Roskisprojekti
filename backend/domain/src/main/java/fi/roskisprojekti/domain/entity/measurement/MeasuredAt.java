package fi.roskisprojekti.domain.entity.measurement;

import java.time.Instant;
import java.util.Objects;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidTimeStamp;

//Long, datetime, joku muu, miksi?
public record MeasuredAt(Instant value) {
    public MeasuredAt{
        isValidTimeStamp(value, "Measured at");

    }
}
