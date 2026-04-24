package fi.roskisprojekti.adapter.out.persistence.jpa.mapper;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.AlertJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.entity.TaskJpaEntity;
import fi.roskisprojekti.domain.common.EndTimeStamp;
import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.common.StartTimeStamp;
import fi.roskisprojekti.domain.entity.alert.AlertId;
import fi.roskisprojekti.domain.entity.employee.EmployeeId;
import fi.roskisprojekti.domain.entity.task.Task;
import fi.roskisprojekti.domain.entity.task.TaskId;
import org.springframework.stereotype.Component;

@Component
public class TaskPersistenceMapper {

    public Task toDomainEntity(TaskJpaEntity jpaEntity) {
        if (jpaEntity == null) return null;

        TaskId taskId = new TaskId(jpaEntity.getId());
        AlertId alertId = new AlertId(jpaEntity.getAlertJpaEntity().getId());
        StartTimeStamp createdAt = new StartTimeStamp(jpaEntity.getCreatedAt());

        SingleTimeStamp assignedAt = (jpaEntity.getAssignedAt() != null)
                ? new SingleTimeStamp(jpaEntity.getAssignedAt())
                : null;

        EndTimeStamp doneAt = (jpaEntity.getDoneAt() != null)
                ? new EndTimeStamp(jpaEntity.getDoneAt())
                : null;

        EmployeeId employeeId = (jpaEntity.getAssignedEmployeeJpaEntity() != null)
                ? new EmployeeId(jpaEntity.getAssignedEmployeeJpaEntity().getId())
                : null;

        return new Task(
                taskId,
                alertId,
                employeeId,
                jpaEntity.getStatus(),
                createdAt,
                assignedAt,
                doneAt
        );
    }

    public TaskJpaEntity toJpaEntity(Task task, AlertJpaEntity alertJpaEntity) {
        if (task == null) return null;

        TaskJpaEntity jpaEntity = new TaskJpaEntity();

        if (task.getTaskId() != null) {
            jpaEntity.setId(task.getTaskId().value());
        }

        jpaEntity.setAlertJpaEntity(alertJpaEntity);

        jpaEntity.setStatus(task.getTaskStatus());

        jpaEntity.setCreatedAt(task.getCreatedAt() != null ? task.getCreatedAt().value() : null);
        jpaEntity.setAssignedAt(task.getAssignedAt() != null ? task.getAssignedAt().value() : null);
        jpaEntity.setDoneAt(task.getCompletedAt() != null ? task.getCompletedAt().value() : null);


        return jpaEntity;
    }
}