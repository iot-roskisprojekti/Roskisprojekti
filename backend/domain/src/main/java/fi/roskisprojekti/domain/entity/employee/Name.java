package fi.roskisprojekti.domain.entity.employee;

import java.util.Objects;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidID;
import static fi.roskisprojekti.domain.validation.DomainValidator.notNull;

public record Name(String value) {
    public Name{
        notNull(value, "Name ");

    }
}
