package com.divakar.taskmanagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
	@Size(max = 100, message = "Title must not exceed 100 characters")
	private String title;

	@Size(max = 500, message = "Description must not exceed 500 characters")
	private String description;

    @Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	@Enumerated(EnumType.STRING)
	private TaskPriority priority;

    public Task() {
    }

    public Task(Long id, String title, String description, TaskStatus status,TaskPriority priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
		this.priority = priority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
	
	public TaskPriority getPriority(){
		return priority;
	}
	
	public void setPriority(TaskPriority priority){
		this.priority = priority;
	}
	
}
