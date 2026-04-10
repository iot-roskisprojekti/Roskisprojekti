package fi.roskisprojekti.domain.entity.telemetry.bin;

import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.entity.bin.BinId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BinTelemetry {
    private final BinId binId;
    private final Distance distance;
    private final SingleTimeStamp measuredAt;

}
