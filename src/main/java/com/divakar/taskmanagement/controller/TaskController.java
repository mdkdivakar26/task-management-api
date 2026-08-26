package com.divakar.taskmanagement.controller;

import com.divakar.taskmanagement.model.Task;
import com.divakar.taskmanagement.service.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
	
	@GetMapping("/{id}")
	public Task getTaskById(@PathVariable Long id) {
		return taskService.getTaskById(id);
	}

    @PostMapping
	public Task createTask(@Valid @RequestBody Task task) {
        return taskService.createTask(task);
    }
	
	@PutMapping("/{id}")
	public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task) {
		return taskService.updateTask(id, task);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable Long id) {
		taskService.deleteTask(id);
	}
}