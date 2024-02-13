package com.anup.blog.repository;

import com.anup.blog.entity.Comment;
import com.anup.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment>findByPostId(long postId);



}


