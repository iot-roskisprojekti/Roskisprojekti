package fi.roskisprojekti.domain.entity.site;

import fi.roskisprojekti.domain.validation.DomainValidationException;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidID;

public record SiteId(Long value) {
    public SiteId{
        isValidID(value, "Site id");
    }
}
