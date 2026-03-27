package org.example.domain.measurement;

import org.example.domain.bin.BinId;

public class Measurement {
    private final BinId binId;
    private final Distance distance;
    private final MeasuredAt measuredAt;
    public Measurement(BinId binId, Distance distance, MeasuredAt measuredAt) {
        this.binId = binId;
        this.distance = distance;
        this.measuredAt = measuredAt;
    }
}
