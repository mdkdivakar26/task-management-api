package com.divakar.taskmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleTaskNotFound(TaskNotFoundException exception) {

        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", 404,
                "error", "Not Found",
                "message", exception.getMessage()
        );
    }
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, Object> handleValidationErrors(
			MethodArgumentNotValidException exception) {

		Map<String, String> errors = new HashMap<>();

		exception.getBindingResult()
				.getFieldErrors()
				.forEach(error ->
						errors.put(error.getField(), error.getDefaultMessage()));

		return Map.of(
				"timestamp", LocalDateTime.now(),
				"status", 400,
				"error", "Bad Request",
				"messages", errors
		);
	}
}

