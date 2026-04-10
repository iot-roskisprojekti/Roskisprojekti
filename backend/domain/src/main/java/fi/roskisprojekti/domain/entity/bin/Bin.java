package fi.roskisprojekti.domain.entity.bin;


import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.entity.site.SiteId;
import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.bin.BinEmptyingRequiredEvent;
import fi.roskisprojekti.domain.event.bin.BinStaleEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import fi.roskisprojekti.domain.entity.telemetry.bin.Distance;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Getter
@AllArgsConstructor
public class Bin {
    private final List<BinDomainEvent> domainEvents = new ArrayList<>();
    private final BinId binId;
    private final SiteId siteId;
    private final Name name;
    private final Capacity capacity;
    private Dimensions dimensions;
    private FillLevel fillLevel;
    private FillAlertThreshold fillAlertThreshold;
    private StaleThresholdMinutes staleThresholdMinutes;
    private SingleTimeStamp lastUpdated;
    private BinStatus binStatus;

    //rosentteina 0-100 -- tämä riittänee tässä vaiheessa
    public void updateFillLevel(FillLevel percent){
        this.fillLevel = percent;
    }



    private BinStatus updateStatus() {
        if (staleThresholdReached()) {
            return BinStatus.STALE_TELEMETRY;
        }

        if (fillLevel.percent() >= this.fillAlertThreshold.critical()) {
            return BinStatus.NEEDS_EMPTYING;
        }

        if (fillLevel.percent() >= fillAlertThreshold.warning()) {
            return BinStatus.WARNING;
        }

        return BinStatus.OK;



    }


    public void updateFromTelemetry(BinTelemetry latestTelemetry) {
        this.fillLevel = updateFillLevelFromCensor(latestTelemetry.getDistance());
        this.lastUpdated = latestTelemetry.getMeasuredAt();

        this.binStatus = updateStatus();
    }

    /**
    public List<BinDomainEvent> updateFromTelemetry(BinTelemetry latestTelemetry){
        List<BinDomainEvent> domainEvents = new ArrayList<>();

        BinStatus prevStatus = this.binStatus;
        this.fillLevel = updateFillLevelFromCensor(latestTelemetry.getDistance());
        this.lastUpdated = latestTelemetry.getMeasuredAt();

        this.binStatus = this.updateStatus(SingleTimeStamp.now().value());

        if(this.binStatus == BinStatus.STALE_TELEMETRY){
            domainEvents.add(new BinStaleEvent(this.getBinId(), this.lastUpdated.value()));
        }

        if(this.binStatus == BinStatus.CRITICAL && prevStatus != BinStatus.CRITICAL) {
            domainEvents.add(new BinEmptyingRequiredEvent(this.getBinId(), SingleTimeStamp.now().value()));
        }

        return domainEvents;

    }
    */
    //toistaiseksi turha? jos muodoltaan symmetrinen riittää korkeus
    //DB ei sisällä mittoja 
    //Laskenta kapasiteetilla vai korkeudella? Tarvitaanko kapasiteettia? 
    private FillLevel updateFillLevelFromCensor(Distance distance){
        double percent = (dimensions.height() - distance.value()) / dimensions.height() * 100;
        return new FillLevel(percent);

    }



    public boolean staleThresholdReached(){
        if (lastUpdated == null || lastUpdated.value() == null) return false;
        return Duration.between(lastUpdated.value(), Instant.now()).toMinutes() > staleThresholdMinutes.value();
    }



}
