package fi.roskisprojekti.application.port.out.persistence;

import fi.roskisprojekti.domain.measurement.Measurement;

import java.util.List;

public interface MeasurementRepository {
    void save(Measurement measurement);
    List<Measurement> findAllMeasurements();
}
