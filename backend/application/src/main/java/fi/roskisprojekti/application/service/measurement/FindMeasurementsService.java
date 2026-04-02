package fi.roskisprojekti.application.service.measurement;

import fi.roskisprojekti.application.port.in.measurement.FindMeasurementsUseCase;
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.domain.measurement.Measurement;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FindMeasurementsService implements FindMeasurementsUseCase {
    private final MeasurementRepository measurementRepository;

    @Override
    public List<Measurement> findAllMeasurements() {
        return measurementRepository.findAllMeasurements();
    }
}
