package fi.roskisprojekti.domain.entity.bin;

import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.entity.site.SiteId;
import fi.roskisprojekti.domain.entity.telemetry.bin.BinTelemetry;
import fi.roskisprojekti.domain.event.bin.BinDomainEvent;
import fi.roskisprojekti.domain.event.bin.BinEmptyingRequiredEvent;
import fi.roskisprojekti.domain.entity.telemetry.bin.Distance;
import lombok.AllArgsConstructor;
import lombok.Getter;

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

    public void updateFillLevel(FillLevel percent) {
        this.fillLevel = percent;
    }

    public List<BinDomainEvent> updateFromTelemetry(BinTelemetry latestTelemetry) {
        List<BinDomainEvent> events = new ArrayList<>();
        BinStatus oldStatus = this.binStatus;

        // KORJAUS TÄSSÄ: Lisätty get-etuliitteet
        this.fillLevel = updateFillLevelFromCensor(latestTelemetry.getDistance());
        this.lastUpdated = latestTelemetry.getMeasuredAt();

        this.binStatus = calculateStatus();

        if (this.binStatus == BinStatus.NEEDS_EMPTYING && oldStatus != BinStatus.NEEDS_EMPTYING) {
            events.add(new BinEmptyingRequiredEvent(this.binId, Instant.now()));
            System.out.println("DOMAIN: Kriittinen raja ylittyi! Roskis " + binId.value() + " vaatii tyhjennystä.");
        }

        return events;
    }

    private BinStatus calculateStatus() {
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

    private FillLevel updateFillLevelFromCensor(Distance distance) {
        if (dimensions == null || dimensions.height() <= 0) {
            return new FillLevel(0);
        }
        double percent = (dimensions.height() - distance.value()) / dimensions.height() * 100;
        percent = Math.max(0, Math.min(100, percent));
        return new FillLevel(percent);
    }

    public boolean staleThresholdReached() {
        if (lastUpdated == null || lastUpdated.value() == null) return false;
        return Duration.between(lastUpdated.value(), Instant.now()).toMinutes() > staleThresholdMinutes.value();
    }
}