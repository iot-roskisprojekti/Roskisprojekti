package fi.roskisprojekti.domain.measurement;

import java.time.Instant;
import java.util.Objects;

//Long, datetime, joku muu, miksi?
public record MeasuredAt(Instant value) {
    public MeasuredAt{
        Objects.requireNonNull(value, "Measurement timestamp can't be null");


    }
}
