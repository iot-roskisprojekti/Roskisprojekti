package fi.roskisprojekti.bootstrap.domain;

import fi.roskisprojekti.application.port.in.task.ManageTasksUseCase;
import fi.roskisprojekti.application.port.out.persistence.AlertRepository;
import fi.roskisprojekti.application.port.out.persistence.BinRepository;
import fi.roskisprojekti.application.port.out.persistence.TaskRepository;
import fi.roskisprojekti.application.service.task.ManageTasksService;
import fi.roskisprojekti.application.port.out.persistence.AlertRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskDomainConfig {

    @Bean
public ManageTasksUseCase findTasksUseCase(
        TaskRepository taskRepository,
        AlertRepository alertRepository,
        BinRepository binRepository) {
    return new ManageTasksService(taskRepository, alertRepository, binRepository);
}
}
