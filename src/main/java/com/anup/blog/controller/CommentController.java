package com.anup.blog.controller;

import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.CommentDto;
import com.anup.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId")
                                                        long postId,@RequestBody CommentDto commentDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);


    }
}
