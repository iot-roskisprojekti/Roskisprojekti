package org.example.domain.bin;


//Nykyinen
public record FillLevel(double percent) {

    public FillLevel{

        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Fill percent must be between 0 and 100");
        }
    }
}
