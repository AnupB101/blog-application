package com.anup.blog.service.impl;

import com.anup.blog.entity.Post;
import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.PostDto;
import com.anup.blog.repository.PostRepository;
import com.anup.blog.service.PostService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

   private PostRepository postRepository;

   public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
       //convert dto to entity
       Post post = new Post();
     // post.setTitle(postDto.getTitle());
     //post.setId(postDto.getId());
     // post.setDescription(postDto.getDescription());
     // post.setContent(postDto.getContent());

      BeanUtils.copyProperties(postDto, post);
      Post newPost = postRepository.save(post);

      //convert entity to Dto
        PostDto newPostDto = new PostDto();
        BeanUtils.copyProperties(postDto, newPostDto);
       // postResponse.setId(newPost.getId());
        //postResponse.setDescription(newPost.getDescription());
        //postResponse.setTitle(newPost.getTitle());
       // postResponse.setContent(newPost.getContent());


        return newPostDto;
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);

       Page<Post> posts = postRepository.findAll(pageable);

       List<Post>vewPosts = posts.getContent();

       List<PostDto>post = new ArrayList<PostDto>();

       for(Post pt: vewPosts)
       {
           PostDto pdto = new PostDto();
           BeanUtils.copyProperties(pt, pdto);
           post.add(pdto);
       }

        return post;
    }

    @Override
    public PostDto findById(long id) throws ResourceNotFoundException {
       PostDto pd = new PostDto();
      Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        BeanUtils.copyProperties(post, pd);
        return pd;

    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) throws ResourceNotFoundException {
       //get pose by id from the data base
        PostDto pd = new PostDto();
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost = postRepository.save(post);
        BeanUtils.copyProperties(updatePost, pd);

        return pd;
    }


}
