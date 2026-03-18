package org.example.domain.bin;
import org.example.domain.site.SiteId;


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

    //rosentteina 0-100
    public void updateFillLevel(FillLevel percent){
        this.fillLevel = percent;
    }


    //Status (tarkista vielä prosentit ja tilat miten suunnitelmassa)

    public BinStatus getStatus() {
        double percent = fillLevel.percent();

        if (percent >= 90){
            return BinStatus.CRITICAL;
        } 
        
        if (percent >= 70){
            return BinStatus.WARNING;
        }
        
        return BinStatus.OK;
    }


    //toistaiseksi turha? jos muodoltaan symmetrinen riittää korkeus
    //Laskenta kapasiteetilla vai korkeudella? Tarvitaanko kapasiteettia? 

    public void updateFillLevelFromCensor(double distance){

        double percent = (dimensions.height() - distance) / dimensions.height() * 100;
        this.fillLevel = new FillLevel(percent);

    }

    //Tarvitaanko 
    public boolean needsCollection() {
        return getStatus() == BinStatus.CRITICAL;
    }

    //Getterit, binid, filllevel, siteid 

    public FillLevel getFillLevel() {
        return fillLevel;
    }

    public BinId getBinId() {
        return binId;
    }

    public SiteId getSiteId() {
        return siteId;
    }


}
