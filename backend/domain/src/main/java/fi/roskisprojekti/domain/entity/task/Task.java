package fi.roskisprojekti.domain.entity.task;

import fi.roskisprojekti.domain.common.EndTimeStamp;
import fi.roskisprojekti.domain.common.SingleTimeStamp;
import fi.roskisprojekti.domain.common.StartTimeStamp;
import fi.roskisprojekti.domain.entity.alert.AlertId;
import fi.roskisprojekti.domain.entity.employee.EmployeeId;
import fi.roskisprojekti.domain.event.task.BinEmptyingTaskCompletedEvent;
import fi.roskisprojekti.domain.event.task.TaskDomainEvent;
import fi.roskisprojekti.domain.validation.DomainValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Task {
    private final TaskId taskId;
    private final AlertId alertId;
    private final EmployeeId assignedEmployeeId;
    private  TaskStatus taskStatus;
    private final StartTimeStamp createdAt;
    private final SingleTimeStamp assignedAt;
    private  EndTimeStamp completedAt;


    public static Task createNew(AlertId alertId, EmployeeId employeeId) {
        return new Task(
                null,
                alertId,
                employeeId,
                TaskStatus.OPEN,
                StartTimeStamp.now(),
                null,
                null
        );
    }
    /**
    public List<TaskDomainEvent> complete(EndTimeStamp end) {
        if (createdAt.value().isAfter(end.value())) {
            throw new DomainValidationException("Task can't end before it started!");
        }

        this.completedAt = end;
        this.taskStatus = TaskStatus.COMPLETED;

        return List.of(new BinEmptyingTaskCompletedEvent(this.taskId, this.alertId, end.value()));
    }
    */
}
