package fi.roskisprojekti.bootstrap.domain;

import fi.roskisprojekti.application.port.in.measurement.FindMeasurementsUseCase;
import fi.roskisprojekti.application.port.in.telemetry.ProcessTelemetryUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.application.service.measurement.FindMeasurementsService;
import fi.roskisprojekti.application.service.telemetry.ProcessTelemetryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MeasurementDomainConfig {
    @Bean
    public ProcessTelemetryUseCase recordMeasurementUseCase(
            MeasurementRepository measurementRepository,
            BinRepository binRepository) {
        return new ProcessTelemetryService(measurementRepository, binRepository);
    }


    @Bean
    public FindMeasurementsUseCase findMeasurementsUseCase(
            MeasurementRepository measurementRepository) {
        return new FindMeasurementsService(measurementRepository);
    }
}
