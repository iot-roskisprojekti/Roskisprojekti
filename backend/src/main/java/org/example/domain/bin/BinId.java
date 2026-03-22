package org.example.domain.bin;

public record BinId(Long value) {

    public BinId {
        if (value == null || value <=0){ //?? 
            throw new IllegalArgumentException("Invalid bin id"); 
        }
    }
}
