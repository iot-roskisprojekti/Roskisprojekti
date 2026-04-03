package fi.roskisprojekti.domain.validation;

import java.time.Instant;

public class DomainValidator {
    public static void notNull(Object value, String message) {
        if (value == null) throw new DomainValidationException(message + " can't be null");
    }

    public static void isValidID(Long value, String fieldName) {
        if (value == null) {
            throw new DomainValidationException(fieldName + " can't be null");
        }
        if (value <= 0) {
            throw new DomainValidationException(fieldName + " must be a positive number");
        }
    }
    public static void isValidTimeStamp(Instant value, String fieldName) {
        if (value == null) {
            throw new DomainValidationException(fieldName + " can't be null");
        }

        if (value.isAfter(Instant.now())) {
            throw new DomainValidationException(fieldName + " timestamp can't be in the future");
        }

    }
}
