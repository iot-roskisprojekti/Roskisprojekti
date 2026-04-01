package fi.roskisprojekti.domain.bin;

//tarvitaanko edes muita kuin korkeus? 

//Toistaiseksi turha?

public record Dimensions(
    double height,
    double width, 
    double depth
) {

    public Dimensions{

        if (height <= 0){
            throw new IllegalArgumentException("height must be positive");
        }

        if (width <= 0){
            throw new IllegalArgumentException("width must be positive");
        }

        if (depth <= 0){
            throw new IllegalArgumentException("depth must be postive");
        }
    }


    




}
