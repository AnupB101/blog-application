package com.anup.blog.service.impl;

import com.anup.blog.entity.Category;
import com.anup.blog.entity.Post;
import com.anup.blog.entity.User;
import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.PostDto;
import com.anup.blog.repository.CategoryRepository;
import com.anup.blog.repository.PostRepository;
import com.anup.blog.repository.UserRepository;
import com.anup.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

   private final PostRepository postRepository;
   private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public PostDto createPost(PostDto postDto, Long uid, String catName) throws ResourceNotFoundException {
        User user = userRepository.findById(uid)
                .orElseThrow(() -> new ResourceNotFoundException("User","uid", uid));
        Optional<Category> cat = categoryRepository.findByTitle(catName);
        Category category;
        if(cat.isEmpty()) {
            category=new Category();
            category.setTitle(catName);
        }else {
            category=cat.get();
        }
        String message = "Post not created";
        Post post = new Post();
        BeanUtils.copyProperties(postDto, post);

        // Setting the user for the post
        post.(user);

        // Adding the category to the post
        post.getCategory().add(category);

        category.getCategoriesPosts().add(post);

        Post savedPost = postDaoImpl.save(post);

        if (savedPost != null) {
            message = "Post created, in category: " + category.getCategoryName();
        } else {
            message = "Failed to create post.";
        }
        return message;
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
       //get post by id from the database
        PostDto pd = new PostDto();
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatePost = postRepository.save(post);
        BeanUtils.copyProperties(updatePost, pd);

        return pd;
    }

    @Override
    public void deletePostById(long id) throws ResourceNotFoundException {
        Post post    = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));
        postRepository.delete(post);
    }

    private PostDto mapToDTO(Post post){
        return modelMapper.map(post, PostDto.class);
    }

    private Post mapToEntity(PostDto postDto){
        return modelMapper.map(postDto, Post.class);
    }

}

