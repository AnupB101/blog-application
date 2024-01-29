package com.anup.blog.service.impl;

import com.anup.blog.entity.Comment;
import com.anup.blog.entity.Post;
import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.CommentDto;
import com.anup.blog.repository.CommentRepository;
import com.anup.blog.repository.PostRepository;
import com.anup.blog.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public List<CommentDto> getCommentsByPostId(long postId) throws ResourceNotFoundException {
        //Post existingPost = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "id", postId));
        List<Comment> comments = commentRepository.findByPostId(postId);
        if(comments.isEmpty()){
            throw new ResourceNotFoundException("comment","id", postId);

            }
        return comments.stream().map(this::mapToDTO).collect(Collectors.toList());


    }

    @Override
    public CommentDto getComment(Long cid) throws ResourceNotFoundException {
        Comment cmt = commentRepository.findById(cid)
                .orElseThrow(() -> new ResourceNotFoundException("comment","id", cid));
        CommentDto commentDto = new CommentDto();

        BeanUtils.copyProperties(cmt, commentDto);

        return commentDto;

    }

    @Override
    public String updateComment(Long cid, CommentDto commentDto) {
        return null;
    }

    @Override
    public String deleteComment(Long cid) throws ResourceNotFoundException {
        String message = "Comment deleted";
        Comment existingComment = commentRepository.findById(cid)
                .orElseThrow(() -> new ResourceNotFoundException("comment","cid", cid));
        commentRepository.delete(existingComment);
        return message;
    }







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
