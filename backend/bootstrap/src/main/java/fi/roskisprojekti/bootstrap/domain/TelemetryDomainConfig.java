package fi.roskisprojekti.bootstrap.domain;

import fi.roskisprojekti.application.port.in.bin.FindBinTelemetryUseCase;
import fi.roskisprojekti.application.port.in.broker.DomainEventPublisher;
import fi.roskisprojekti.application.port.in.telemetry.bin.HandleInBoundTelemetryUseCase;
import fi.roskisprojekti.application.port.out.persistence.AlertRepository;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.application.port.out.persistence.BinTelemetryRepository;
import fi.roskisprojekti.application.port.out.persistence.TaskRepository;
import fi.roskisprojekti.application.service.bin.FindBinTelemetryService;
import fi.roskisprojekti.application.service.event.InternalDomainEventBroker;
import fi.roskisprojekti.application.service.telemetry.bin.BinTelemetryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelemetryDomainConfig {

    @Bean
    public FindBinTelemetryUseCase findBinTelemetryUseCase(BinTelemetryRepository telemetryRepository) {
        return new FindBinTelemetryService(telemetryRepository);
    }

    @Bean
    public HandleInBoundTelemetryUseCase processBinTelemetryUseCase(
            BinTelemetryRepository telemetryRepository,
            BinRepository binRepository,
            AlertRepository alertRepository,
            TaskRepository taskRepository,
            DomainEventPublisher domainEventPublisher) {

        return new BinTelemetryService(
                telemetryRepository,
                binRepository,
                alertRepository,
                taskRepository,
                domainEventPublisher
        );
    }
}