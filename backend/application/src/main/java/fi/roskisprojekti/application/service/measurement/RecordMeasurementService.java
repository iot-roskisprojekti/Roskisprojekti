package fi.roskisprojekti.application.service.measurement;

import fi.roskisprojekti.application.port.in.measurement.RecordMeasurementUseCase;
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.domain.measurement.Measurement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RecordMeasurementService implements RecordMeasurementUseCase {
    private final MeasurementRepository measurementRepository;

    @Override
    public void saveMeasurement(Measurement measurement) {
        measurementRepository.save(measurement);
    }
}
