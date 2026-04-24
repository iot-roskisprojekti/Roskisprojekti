package fi.roskisprojekti.domain.entity.alert;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidID;

public record AlertId(Long value) {
    public AlertId{
        isValidID(value, "Alert id");
    }
}
