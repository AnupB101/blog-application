package com.anup.blog.service.impl;

import com.anup.blog.entity.Category;
import com.anup.blog.exception.DuplicateItemException;
import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.CategoryDto;
import com.anup.blog.repository.CategoryRepository;
import com.anup.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;


    private final ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public String addCategory(CategoryDto categoryDto) {
        if(categoryRepository.findByTitle(categoryDto.getTitle()).isPresent()){
            throw new DuplicateItemException("The Category" + categoryDto.getTitle()+"is already exits");
        }
        Category category = modelMapper.map(categoryDto, Category.class);
        Category saveCategory = categoryRepository.save(category);
        String message = "Cannot Create Category";
        message = "Category created";
        return message;


    }

    @Override
    public String deleteCategory(long cid) throws ResourceNotFoundException {
        String message = "Category not deleted";
        categoryRepository.findById(cid).orElseThrow(()-> new ResourceNotFoundException("category", "cid", cid));
        categoryRepository.deleteById(cid);
        message = "Category deleted";
        return message;

    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());

   }

    @Override
    public CategoryDto getCategoryById(long cid) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(cid).orElseThrow(()->new ResourceNotFoundException("category", "id", cid));
        return modelMapper.map(category, CategoryDto.class);

    }

    @Override
    public CategoryDto updateCategoryById(CategoryDto categoryDto, long cid) throws ResourceNotFoundException {

        Category category = categoryRepository.findById(cid).orElseThrow(()->new ResourceNotFoundException("category", "cid", cid));
        category.setId(cid);
        category.setTitle(categoryDto.getTitle());
        Category updatedCategory = categoryRepository.save(category);

        return modelMapper.map(updatedCategory, CategoryDto.class);



    }
}
