package com.anup.blog.controller;

import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.PostDto;
import com.anup.blog.service.PostService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping //http://localhost:8080/api/posts
    public ResponseEntity<PostDto>createPost(@Valid @RequestBody PostDto postDto){

       return new ResponseEntity<>(postService.createPost(postDto),HttpStatus.CREATED);
    }

    //Get all post

    @GetMapping //http://localhost:8080/api/posts
    public List<PostDto>getAllPosts(
            @RequestParam(value ="pageNo", defaultValue = "0", required = false)int pageNo,
            @RequestParam(value = "pagesize", defaultValue = "10", required = false) int pageSize
    ){
        return postService.getAllPosts(pageNo,pageSize);
    }

    @GetMapping("/{id}") //http://localhost:8080/api/posts/id
    public ResponseEntity<PostDto> getPostById(@PathVariable(name="id")long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(postService.findById(id));

    }

    @PutMapping("/{id}") //http://localhost:8080/api/posts/?

    public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto, @PathVariable(name ="id")long id) throws ResourceNotFoundException {
        PostDto postResponse = postService.updatePost(postDto, id);
        return new ResponseEntity<>(postResponse,HttpStatus.OK);
    }

    //Delete post Rest API
    @DeleteMapping("/{id}")//http://localhost:8080/api/posts/id
    public ResponseEntity<String>deletePost(@PathVariable(name="id")long id) throws ResourceNotFoundException {

        postService.deletePostById(id);
        return new ResponseEntity<>("post deleted successfully", HttpStatus.OK);

    }

}
