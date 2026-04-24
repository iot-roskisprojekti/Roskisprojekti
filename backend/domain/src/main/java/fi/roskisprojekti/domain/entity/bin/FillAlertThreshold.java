package fi.roskisprojekti.domain.entity.bin;

import java.util.Objects;

import static fi.roskisprojekti.domain.validation.DomainValidator.notNull;

public record FillAlertThreshold(Integer warning, Integer critical) {
    public FillAlertThreshold {
        notNull(warning, "Warning threshold ");
        notNull(warning, "Critical threshold ");


        if(warning < 0 || critical < 0) {
            throw new IllegalArgumentException("Threshold value must be a positive integer");
        }
        if(warning > 100 || critical > 100) {
            throw new IllegalArgumentException("Threshold value can't exceed 100");
        }

        if (warning >= critical) {
            throw new IllegalArgumentException("Warning threshold must be less than critical threshold");
        }

    }
}
