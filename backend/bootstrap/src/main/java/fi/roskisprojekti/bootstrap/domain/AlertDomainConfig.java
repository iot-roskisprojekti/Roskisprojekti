package fi.roskisprojekti.bootstrap.domain;

import fi.roskisprojekti.application.port.in.alert.ManageAlertsUseCase;
import fi.roskisprojekti.application.port.out.persistence.AlertRepository;
import fi.roskisprojekti.application.service.alert.ManageAlertsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlertDomainConfig {

    @Bean
    public ManageAlertsUseCase findAlertsUseCase(AlertRepository alertRepository) {
        return new ManageAlertsService(alertRepository);
    }
}