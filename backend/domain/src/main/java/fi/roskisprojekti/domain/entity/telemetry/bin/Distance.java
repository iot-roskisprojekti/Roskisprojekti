package fi.roskisprojekti.domain.entity.telemetry.bin;



public record Distance(double value) {

    public Distance{
        if(value < 0.0) {
            throw new IllegalArgumentException("Distance must be positive.");
        }

    }
}
