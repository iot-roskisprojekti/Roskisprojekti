package fi.roskisprojekti.domain.entity.site;

import fi.roskisprojekti.domain.validation.DomainValidationException;

import static fi.roskisprojekti.domain.validation.DomainValidator.notNull;

public record Name(String value) {
    public Name{
        notNull(value, "Name ");
    }
}
