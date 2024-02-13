package com.anup.blog.controller;

import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.CommentDto;
import com.anup.blog.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")

    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId")
                                                        long postId,@RequestBody CommentDto commentDto) throws ResourceNotFoundException {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);


    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<Map<String, List<CommentDto>>> getCommentsByPost(@PathVariable(value = "postId") long postId) throws ResourceNotFoundException {
        List<CommentDto> getComments = commentService.getCommentsByPostId(postId);
        String message = "comments of pid:" + postId;
        Map<String, List<CommentDto>> response = new HashMap<>();
        response.put(message, getComments);
        return new ResponseEntity<>(response,HttpStatus.OK);

    }
    @GetMapping("get/{cid}")
    public ResponseEntity<Map<String, CommentDto>> getComment(@PathVariable("cid") Long cid) throws ResourceNotFoundException {
        CommentDto getComment = commentService.getComment(cid);
        String message = "Comment";
        Map<String, CommentDto> response = new HashMap<>();
        response.put(message, getComment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{cid}")
    public ResponseEntity<Map<String, String>> updateComment(@PathVariable("cid") Long cid,
                                                             @RequestBody CommentDto commentDto) {
        String updateComment = commentService.updateComment(cid, commentDto);
        String message = "Message";
        Map<String, String> response = new HashMap<>();
        response.put(message, updateComment);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{cid}")
    public ResponseEntity<Map<String, String>> deleteComment(@PathVariable("cid") Long cid) throws ResourceNotFoundException {
        String deleteComment = commentService.deleteComment(cid);
        String message = "Message";
        Map<String, String> response = new HashMap<>();
        response.put(message, deleteComment);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }



}
