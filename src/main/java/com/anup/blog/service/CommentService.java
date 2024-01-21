package com.anup.blog.service;

import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto) throws ResourceNotFoundException;

}
