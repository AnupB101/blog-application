package com.anup.blog.service;

import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto) throws ResourceNotFoundException;


    List<CommentDto> getCommentsByPostId(long postId) throws ResourceNotFoundException;



    public CommentDto getComment(Long cid) throws ResourceNotFoundException;

    public String updateComment(Long cid,CommentDto commentDto);

    public String deleteComment(Long cid) throws ResourceNotFoundException;







}
