package org.example.domain.bin;



public class Bin {
    private final BinId binId;
    private final SiteId siteId;
    private Dimensions dimensions;
    private FillLevel fillLevel;
    private AlertThreshold alertThreshold;

    public Bin(BinId binId, SiteId siteId, Dimensions dimensions, FillLevel fillLevel, AlertThreshold alertThreshold){
        this.binId = binId;
        this.siteId = siteId;
        this.dimensions = dimensions;
        this.fillLevel = fillLevel;
        this.alertThreshold = alertThreshold;
    }

    //rosentteina 0-100 -- tämä riittänee tässä vaiheessa
    public void updateFillLevel(FillLevel percent){
        this.fillLevel = percent;
    }


    //Status (tarkista vielä prosentit ja tilat miten suunnitelmassa)

    public BinStatus getStatus() {
        double percent = fillLevel.percent();

        if (percent >= alertThreshold.critical()){
            return BinStatus.CRITICAL;
        } 
        
        if (percent >= alertThreshold.warning()){
            return BinStatus.WARNING;
        }
        
        return BinStatus.OK;
    }


    //toistaiseksi turha? jos muodoltaan symmetrinen riittää korkeus
    //DB ei sisällä mittoja 
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
