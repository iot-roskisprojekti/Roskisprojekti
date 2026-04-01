package fi.roskisprojekti.bootstrap;

import fi.roskisprojekti.application.port.in.bin.FindBinsUseCase;
import fi.roskisprojekti.application.port.in.measurement.FindMeasurementsUseCase;
import fi.roskisprojekti.application.port.in.site.FindSitesUseCase;
import fi.roskisprojekti.application.port.in.telemetry.ProcessTelemetryUseCase;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.application.port.out.persistence.MeasurementRepository;
import fi.roskisprojekti.application.port.out.persistence.SiteRepository;
import fi.roskisprojekti.application.service.bin.FindBinsService;
import fi.roskisprojekti.application.service.measurement.FindMeasurementsService;
import fi.roskisprojekti.application.service.site.FindSitesService;
import fi.roskisprojekti.application.service.telemetry.ProcessTelemetryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Jotenkin maagisesti parsii eri moduulien välisiä rajoja yhteen tai jotakin?
@Configuration
public class DomainConfig {

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

    @Bean
    public FindSitesUseCase findSitesUseCase(SiteRepository siteRepository) {
        return new FindSitesService(siteRepository);
    }

    @Bean
    public FindBinsUseCase findBinsUseCase(BinRepository binRepository) {
        return new FindBinsService(binRepository);
    }

}