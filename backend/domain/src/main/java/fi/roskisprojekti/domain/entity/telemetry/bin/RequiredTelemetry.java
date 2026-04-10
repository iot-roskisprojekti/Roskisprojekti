package fi.roskisprojekti.domain.entity.telemetry.bin;

import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.entity.bin.BinId;

public record RequiredTelemetry(BinId binId, Distance distance, SingleTimeStamp measuredAt) {
    public RequiredTelemetry{

    }
}
