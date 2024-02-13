package com.anup.blog.service;

import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.CategoryDto;

import java.util.LinkedList;
import java.util.List;

public interface CategoryService {
 String addCategory(CategoryDto categoryDto);

 String deleteCategory(long cid) throws ResourceNotFoundException;

 List<CategoryDto>findAll();

 CategoryDto getCategoryById(long cid) throws ResourceNotFoundException;

CategoryDto updateCategoryById(CategoryDto categoryDto, long cid) throws ResourceNotFoundException;

}
