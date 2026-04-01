package fi.roskisprojekti.domain.bin;


import fi.roskisprojekti.domain.site.SiteId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import fi.roskisprojekti.domain.measurement.Distance;


@Getter
@AllArgsConstructor
public class Bin {
    private final BinId binId;
    private final SiteId siteId;
    private final String name;
    private final Double capacity;
    private Dimensions dimensions;
    private FillLevel fillLevel;
    private AlertThreshold alertThreshold;
    private LastUpdated lastUpdated;

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

    public void updateFillLevelFromCensor(Distance distance){


        double percent = (dimensions.height() - distance.value()) / dimensions.height() * 100;
        this.fillLevel = new FillLevel(percent);

    }

    //Tarvitaanko 
    public boolean needsCollection() {
        return getStatus() == BinStatus.CRITICAL;
    }




}
