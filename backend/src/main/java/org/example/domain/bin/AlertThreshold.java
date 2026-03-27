package org.example.domain.bin;

import java.util.Objects;

public record AlertThreshold(Integer warning, Integer critical) {
    public AlertThreshold{
        Objects.requireNonNull(warning, "Threshold 'warning' can't be null");
        Objects.requireNonNull(critical, "Threshold 'critical' can't be null");


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
