package fi.roskisprojekti.application.service.measurement;

import fi.roskisprojekti.application.port.in.measurement.FindMeasurementsUseCase;
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.domain.measurement.Measurement;

import java.util.List;

public class FindMeasurementsService implements FindMeasurementsUseCase {
    private final MeasurementRepository measurementRepository;

    public FindMeasurementsService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public List<Measurement> findAllMeasurements() {
        return measurementRepository.findAllMeasurements();
    }
}
