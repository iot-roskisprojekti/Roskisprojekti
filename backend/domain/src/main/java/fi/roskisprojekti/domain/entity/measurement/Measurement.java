package fi.roskisprojekti.domain.entity.measurement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import fi.roskisprojekti.domain.entity.bin.BinId;


@Getter
@AllArgsConstructor
public class Measurement {
    private final MeasurementId measurementId;
    private final BinId binId;
    private final Distance distance;
    private final MeasuredAt measuredAt;

    public static Measurement createWithoutId(BinId binId, Distance distance, MeasuredAt measuredAt) {
        return new Measurement(null, binId, distance, measuredAt);
    }

    public static Measurement createWithId(MeasurementId id, BinId binId, Distance distance, MeasuredAt measuredAt) {
        return new Measurement(id, binId, distance, measuredAt);
    }

}
