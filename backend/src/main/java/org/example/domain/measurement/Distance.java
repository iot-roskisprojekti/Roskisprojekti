package org.example.domain.measurement;



public record Distance(double distance) {

    public Distance{
        if(distance < 0.0) {
            throw new IllegalArgumentException("Distance must be positive.");
        }

    }
}
