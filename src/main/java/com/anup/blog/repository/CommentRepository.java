package com.anup.blog.repository;

import com.anup.blog.entity.Comment;
import com.anup.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment>findByPostId(long postId);



}


