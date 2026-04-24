package fi.roskisprojekti.application.port.in.task;

import fi.roskisprojekti.domain.entity.task.Task;

import java.util.List;

public interface ManageTasksUseCase {
    List<Task> findAll();
    void save(Task task);
    void complete(Long taskId);
}
