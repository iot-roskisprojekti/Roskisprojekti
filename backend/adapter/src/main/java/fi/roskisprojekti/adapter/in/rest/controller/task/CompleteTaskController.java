package fi.roskisprojekti.adapter.in.rest.controller.task;

import lombok.RequiredArgsConstructor;
import fi.roskisprojekti.application.port.in.task.ManageTasksUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")

public class CompleteTaskController {
    

    private final ManageTasksUseCase manageTasksUseCase;

    @PostMapping("/tasks/{taskId}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable Long taskId) {
        manageTasksUseCase.complete(taskId);
        return ResponseEntity.ok().build();
    }
}
