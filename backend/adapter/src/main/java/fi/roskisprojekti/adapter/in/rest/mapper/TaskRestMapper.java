package fi.roskisprojekti.adapter.in.rest.mapper;

import fi.roskisprojekti.adapter.in.rest.dto.TaskRestDto;
import fi.roskisprojekti.domain.entity.task.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskRestMapper {

    public TaskRestDto toRestDto(Task task) {
        if (task == null) return null;

        return new TaskRestDto(
                task.getTaskId().value(),
                task.getAlertId().value(),
                task.getCreatedAt() != null ? task.getCreatedAt().value() : null,
                task.getTaskStatus() != null ? task.getTaskStatus().name() : null,
                task.getAssignedAt() != null ? task.getAssignedAt().value() : null,
                task.getCompletedAt() != null ? task.getCompletedAt().value() : null
        );
    }
}