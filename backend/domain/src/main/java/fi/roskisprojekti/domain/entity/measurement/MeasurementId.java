package fi.roskisprojekti.domain.entity.measurement;

import static fi.roskisprojekti.domain.validation.DomainValidator.isValidID;

public record MeasurementId(Long value) {
    public MeasurementId{
        isValidID(value, "Measurement Id");
    }
}
