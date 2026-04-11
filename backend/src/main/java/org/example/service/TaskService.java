package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.entity.TaskEntity;
import org.example.model.enums.TaskStatus;
import org.example.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;


@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    @Transactional
    public void completeTask(Long id) {
        TaskEntity task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found: " + id));
        task.setStatus(TaskStatus.VALMIS);
        task.setDoneAt(Instant.now());
        taskRepository.save(task);
    }
}