package fi.roskisprojekti.adapter.in.rest.controller.task;

import fi.roskisprojekti.adapter.in.rest.dto.TaskRestDto;
import fi.roskisprojekti.adapter.in.rest.mapper.TaskRestMapper;
import fi.roskisprojekti.application.port.in.task.ManageTasksUseCase;
import fi.roskisprojekti.domain.entity.task.TaskStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class FindTasksController {

    private final ManageTasksUseCase manageTasksUseCase;
    private final TaskRestMapper taskRestMapper;

    @GetMapping
    public List<TaskRestDto> getAllTasks() {
        return manageTasksUseCase.findAll().stream()
                .filter(task -> task.getTaskStatus() != TaskStatus.COMPLETED)
                .map(taskRestMapper::toRestDto)
                .toList();
    }

}
