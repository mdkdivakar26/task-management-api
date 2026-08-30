package com.divakar.taskmanagement.repository;

import com.divakar.taskmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}