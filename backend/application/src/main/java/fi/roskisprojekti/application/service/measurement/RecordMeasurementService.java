package fi.roskisprojekti.application.service.measurement;

import fi.roskisprojekti.application.port.in.measurement.RecordMeasurementUseCase;
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.domain.measurement.Measurement;

public class RecordMeasurementService implements RecordMeasurementUseCase {
    private final MeasurementRepository measurementRepository;

    public RecordMeasurementService(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @Override
    public void saveMeasurement(Measurement measurement) {
        measurementRepository.save(measurement);
    }
}
