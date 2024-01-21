package com.anup.blog.service;

import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    List<PostDto> getAllPosts(int pageNo, int pageSize);

    PostDto findById(long id) throws ResourceNotFoundException;


    PostDto updatePost(PostDto postDto, long id) throws ResourceNotFoundException;


}
