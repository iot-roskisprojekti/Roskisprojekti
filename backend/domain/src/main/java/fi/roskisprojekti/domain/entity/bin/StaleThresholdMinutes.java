package fi.roskisprojekti.domain.entity.bin;

import fi.roskisprojekti.domain.validation.DomainValidationException;


public record StaleThresholdMinutes(int value) {
    public StaleThresholdMinutes {
        if(value < 10) {
            throw new DomainValidationException("Minimum stale alert limit is 10 minutes");
        }

        if(value > 60) {
            throw new DomainValidationException("Maximum stale alert limit is 60 minutes");
        }
    }
}
