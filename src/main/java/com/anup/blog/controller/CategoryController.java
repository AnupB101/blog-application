package com.anup.blog.controller;

import com.anup.blog.entity.Category;
import com.anup.blog.exception.ResourceNotFoundException;
import com.anup.blog.payload.CategoryDto;
import com.anup.blog.service.CategoryService;
import com.anup.blog.service.impl.CategoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/cat/")
public class CategoryController {
    private final CategoryServiceImpl categoryServiceImpl;

    public CategoryController(CategoryServiceImpl categoryServiceImpl) {
        this.categoryServiceImpl = categoryServiceImpl;
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, String>>createCategory(@RequestBody CategoryDto cat){
        String message = "message";
        String catSave = categoryServiceImpl.addCategory(cat);
        Map<String, String> response = new HashMap<>();
        response.put(message, catSave);
        return new ResponseEntity<>(response, HttpStatus.CREATED);


    }


    @DeleteMapping("/{cid}")//http://localhost:8080/api/cat/{cid}
    public ResponseEntity<Map<String, String>>deleteCategoryById(@PathVariable("cid") long cid) throws ResourceNotFoundException {
        String message = "message";
        String catDelete = categoryServiceImpl.deleteCategory(cid);
        Map<String, String> response = new HashMap<>();
        response.put(message, catDelete);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


        //Get all Categories api

    @GetMapping("/all")//http://localhost:8080/api/cat/all
    public ResponseEntity<?>getAllCategories(){
        List<CategoryDto> category = categoryServiceImpl.findAll();
        String message = "Categories";
        Map<String, List<CategoryDto>> response = new HashMap<>();
        response.put(message,category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping("/{cid}")
    public ResponseEntity<CategoryDto>getCategory(@PathVariable(value = "cid") Long cid) throws ResourceNotFoundException {
        CategoryDto categoryDto = categoryServiceImpl.getCategoryById(cid);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
        //return ResponseEntity.ok(categoryDto);
    }

        //update category


    @PutMapping("/{cid}")
   public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto,@PathVariable(value = "cid") Long cid) throws ResourceNotFoundException {
        return ResponseEntity.ok(categoryServiceImpl.updateCategoryById(categoryDto, cid));
   }

}
