package com.anup.blog.repository;

import com.anup.blog.entity.Category;
import jakarta.persistence.Id;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@EnableJpaRepositories
public interface CategoryRepository extends JpaRepository<Category, Long> {

   Optional<Category>findByTitle(String title);
}

