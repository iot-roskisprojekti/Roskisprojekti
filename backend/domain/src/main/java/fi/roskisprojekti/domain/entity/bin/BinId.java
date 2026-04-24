package fi.roskisprojekti.domain.entity.bin;

import fi.roskisprojekti.domain.validation.DomainValidationException;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidID;

public record BinId(Long value) {

    public BinId {
        isValidID(value, "Bin id");
    }
}
