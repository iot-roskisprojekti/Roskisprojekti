package fi.roskisprojekti.domain.entity.task;

import fi.roskisprojekti.domain.entity.alert.AlertId;
import fi.roskisprojekti.domain.entity.employee.EmployeeId;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Task {
    private final TaskStatus taskStatus;
    private final AlertId alertId;
    private final EmployeeId assignedEmployeeId;
    private final CreatedAt createdAt;
    private final AssignedAt assignedAt;
    private final CompletedAt completedAt;

}
