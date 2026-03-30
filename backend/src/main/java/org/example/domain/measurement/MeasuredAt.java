package org.example.domain.measurement;

import java.time.LocalDateTime;
import java.util.Objects;

//Long, datetime, joku muu, miksi?

//DTO:ssa LocalDateTime, joten käytänne sitä
public record MeasuredAt(LocalDateTime value) {

    public MeasuredAt{
        Objects.requireNonNull(value, "MeasuredAt cannot be null");
    }
}
