package fi.roskisprojekti.adapter.out.persistence;

import fi.roskisprojekti.adapter.out.persistence.jpa.entity.AlertJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.entity.TaskJpaEntity;
import fi.roskisprojekti.adapter.out.persistence.jpa.mapper.TaskPersistenceMapper;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.AlertJpaRepository;
import fi.roskisprojekti.adapter.out.persistence.jpa.repository.TaskJpaRepository;
import fi.roskisprojekti.application.port.out.persistence.TaskRepository;
import fi.roskisprojekti.domain.entity.task.Task;
import fi.roskisprojekti.domain.entity.task.TaskId;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskPersistenceAdapter implements TaskRepository {
    private final TaskPersistenceMapper taskPersistenceMapper;
    private final TaskJpaRepository taskJpaRepository;
    private final AlertJpaRepository alertJpaRepository;

    @Override
    public List<Task> findAll() {
        return taskJpaRepository.findAll().stream()
                .map(taskPersistenceMapper::toDomainEntity)
                .toList();
    }

    @Override
    public void save(Task task) {
        AlertJpaEntity alertJpa = alertJpaRepository.findById(task.getAlertId().value())
                .orElseThrow(() -> new RuntimeException("Alert not found for ID: " + task.getAlertId().value()));

        taskJpaRepository.save(taskPersistenceMapper.toJpaEntity(task, alertJpa));
    }

    @Override
    public Optional<Task> findById(TaskId taskId) {
        return taskJpaRepository.findById(taskId.value())
                .map(taskPersistenceMapper::toDomainEntity);
    }
}
