package com.divakar.taskmanagement.service;

import com.divakar.taskmanagement.dto.TaskRequest;
import com.divakar.taskmanagement.dto.TaskResponse;
import com.divakar.taskmanagement.exception.TaskNotFoundException;
import com.divakar.taskmanagement.model.Category;
import com.divakar.taskmanagement.model.Task;
import com.divakar.taskmanagement.repository.CategoryRepository;
import com.divakar.taskmanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;
import com.divakar.taskmanagement.exception.CategoryNotFoundException;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public TaskService(TaskRepository taskRepository,
                       CategoryRepository categoryRepository) {
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TaskResponse createTask(TaskRequest request) {

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
		task.setDueDate(request.getDueDate());

        if (request.getCategoryId() != null) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() ->
                            new CategoryNotFoundException(
        "Category not found with id: " + request.getCategoryId()));

            task.setCategory(category);
        }

        Task savedTask = taskRepository.save(task);

        return mapToResponse(savedTask);
    }

    public TaskResponse getTaskById(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id: " + id));

        return mapToResponse(task);
    }

    public TaskResponse updateTask(Long id, TaskRequest request) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() ->
                        new TaskNotFoundException(
                                "Task not found with id: " + id));

        existingTask.setTitle(request.getTitle());
        existingTask.setDescription(request.getDescription());
        existingTask.setStatus(request.getStatus());
        existingTask.setPriority(request.getPriority());
		existingTask.setDueDate(request.getDueDate());

        if (request.getCategoryId() != null) {

            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() ->
                            new CategoryNotFoundException(
        "Category not found with id: " + request.getCategoryId()));

            existingTask.setCategory(category);

        } else {
            existingTask.setCategory(null);
        }

        Task updatedTask = taskRepository.save(existingTask);

        return mapToResponse(updatedTask);
    }

    public void deleteTask(Long id) {

        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(
                    "Task not found with id: " + id);
        }

        taskRepository.deleteById(id);
    }

    private TaskResponse mapToResponse(Task task) {

        Long categoryId = null;
        String categoryName = null;

        if (task.getCategory() != null) {
            categoryId = task.getCategory().getId();
            categoryName = task.getCategory().getName();
        }

        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                categoryId,
                categoryName,
				task.getDueDate(),
				task.getCreatedAt(),
				task.getUpdatedAt()
        );
    }
}