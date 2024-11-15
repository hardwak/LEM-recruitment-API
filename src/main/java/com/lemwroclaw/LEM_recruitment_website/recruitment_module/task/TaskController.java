package com.lemwroclaw.LEM_recruitment_website.recruitment_module.task;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.dto.TaskCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.dto.TaskResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<TaskResponseDTO> createTask(@Valid @RequestBody TaskCreationDTO dto) {
        return taskService.createTask(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public ResponseEntity<List<TaskResponseDTO>> getTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EDITOR')")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
    }

}
