package com.lemwroclaw.LEM_recruitment_website.recruitment_module.task;

import com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.dto.TaskCreationDTO;
import com.lemwroclaw.LEM_recruitment_website.recruitment_module.task.dto.TaskResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public ResponseEntity<TaskResponseDTO> createTask(TaskCreationDTO dto){
        Task task = taskMapper.toTask(dto);
        taskRepository.save(task);
        return ResponseEntity.ok(taskMapper.toTaskResponseDTO(task));
    }

    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(){
        List<TaskResponseDTO> tasks = taskRepository
                .findAll()
                .stream()
                .map(taskMapper::toTaskResponseDTO)
                .toList();

        if(tasks.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ResponseEntity.ok(tasks);
    }

    public ResponseEntity<TaskResponseDTO> getTaskById(Long id){
        Task task = taskRepository.findById(id).orElse(null);
        if(task == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(taskMapper.toTaskResponseDTO(task));
    }

    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }

}
