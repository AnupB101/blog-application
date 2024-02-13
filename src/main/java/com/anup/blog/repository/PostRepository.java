package com.anup.blog.repository;

import com.anup.blog.entity.Post;
import com.anup.blog.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface PostRepository extends JpaRepository<Post, Long> {






}
