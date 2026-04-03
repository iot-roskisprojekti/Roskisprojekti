package fi.roskisprojekti.domain.entity.message;

import fi.roskisprojekti.domain.validation.DomainValidationException;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidID;

public record MessageId(Long value) {
    public MessageId{
        isValidID(value, "Message id");
    }
}
