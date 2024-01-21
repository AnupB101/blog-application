package com.anup.blog.service.impl;

import com.anup.blog.entity.Comment;
import com.anup.blog.entity.Post;
import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.CommentDto;
import com.anup.blog.repository.CommentRepository;
import com.anup.blog.repository.PostRepository;
import com.anup.blog.service.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;



    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) throws ResourceNotFoundException {
        Comment comment = mapToCommentEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "id", postId));

        //Set post to comment entity
        comment.setPost(post);
        //save comment entity to data base
        Comment newComment = commentRepository.save(comment);


        return mapToDTO(newComment);
    }







    private CommentDto mapToDTO(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;

    }

    private Comment mapToCommentEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setBody(commentDto.getBody());
        comment.setId(commentDto.getId());
        comment.setEmail(commentDto.getEmail());
        comment.setName(commentDto.getName());

       return comment;

    }
}
