package fi.roskisprojekti.application.port.in.measurement;

import fi.roskisprojekti.domain.measurement.Measurement;

public interface RecordMeasurementUseCase {
    void saveMeasurement(Measurement measurement);
}
