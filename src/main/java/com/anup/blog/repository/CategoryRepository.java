package com.anup.blog.repository;

import com.anup.blog.entity.Category;
import jakarta.persistence.Id;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
