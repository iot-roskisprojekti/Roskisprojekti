package fi.roskisprojekti.application.port.out.persistence;

import fi.roskisprojekti.domain.entity.alert.Alert;
import fi.roskisprojekti.domain.entity.task.Task;
import fi.roskisprojekti.domain.entity.task.TaskId;

import java.util.Optional;
import java.util.List;

public interface TaskRepository {
    List<Task> findAll();
    void save(Task task);
    Optional<Task> findById(TaskId taskId);

}
