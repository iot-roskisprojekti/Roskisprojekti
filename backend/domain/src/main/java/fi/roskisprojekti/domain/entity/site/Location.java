package fi.roskisprojekti.domain.entity.site;

import fi.roskisprojekti.domain.validation.DomainValidationException;

import static fi.roskisprojekti.domain.validation.DomainValidator.notNull;

public record Location(String value) {
    public Location{
        notNull(value, "Location ");

    }
}
