package fi.roskisprojekti.domain.measurement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import fi.roskisprojekti.domain.bin.BinId;


@Getter
@AllArgsConstructor
public class Measurement {
    private final BinId binId;
    private final Distance distance;
    private final MeasuredAt measuredAt;

}
