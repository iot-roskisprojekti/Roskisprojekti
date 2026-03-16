package org.example.domain.bin;

public class Bin {
    private final BinId binId;
    private final SiteId siteId;
    private Dimensions dimensions;
    private FillLevel fillLevel;

    public Bin(BinId binId, SiteId siteId, Dimensions dimensions, FillLevel fillLevel){
        this.binId = binId;
        this.siteId = siteId;
        this.dimensions = dimensions;
        this.fillLevel = fillLevel;
    }


    public void updateFillLevel(FillLevel newFillLevel){
        this.fillLevel = newFillLevel;
    }

}
