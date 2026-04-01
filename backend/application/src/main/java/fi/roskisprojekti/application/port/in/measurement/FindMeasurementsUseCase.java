package fi.roskisprojekti.application.port.in.measurement;

import fi.roskisprojekti.domain.measurement.Measurement;

import java.util.List;

public interface FindMeasurementsUseCase {
    public List<Measurement> findAllMeasurements();
}
