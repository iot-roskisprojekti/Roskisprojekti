package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.adapter.EmailAdapter;
import org.example.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final EmailAdapter emailAdapter;

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeTask(@PathVariable Long id) {
        taskService.completeTask(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/test-email")
    public ResponseEntity<Void> testEmail() {
        emailAdapter.sendAlert("jorkki25@gmail.com", "Testi", "Hei tämä on testi!");
        return ResponseEntity.ok().build();
    }
}