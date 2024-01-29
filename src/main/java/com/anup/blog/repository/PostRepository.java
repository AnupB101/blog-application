package com.anup.blog.repository;

import com.anup.blog.entity.Post;
import com.anup.blog.payload.PostDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {




}
