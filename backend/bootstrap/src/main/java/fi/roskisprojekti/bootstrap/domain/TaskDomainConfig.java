package fi.roskisprojekti.bootstrap.domain;

import fi.roskisprojekti.application.port.in.task.ManageTasksUseCase;
import fi.roskisprojekti.application.port.out.persistence.TaskRepository;
import fi.roskisprojekti.application.service.task.ManageTasksService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskDomainConfig {

    @Bean
    public ManageTasksUseCase findTasksUseCase(TaskRepository taskRepository) {
        return new ManageTasksService(taskRepository);
    }
}
