package com.example.blog_app.controllers;


import com.example.blog_app.payloads.ApiResponse;
import com.example.blog_app.payloads.CategoryDto;
import com.example.blog_app.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

//    create
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return ResponseEntity.ok().body(createdCategory);
    }


//    update
    @PutMapping("/{catId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto,@PathVariable Integer catId) {
        CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,catId);
        return new  ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }
//    delete
    @DeleteMapping("/{catId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer catId) {
        this.categoryService.deleteCategory(catId);
        return new  ResponseEntity<>(new ApiResponse("Category deleted successfully!!",true),HttpStatus.OK);
    }
//    get
    @GetMapping("/{catId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer catId) {
        CategoryDto categoryDto = this.categoryService.getCategory(catId);
        return  new  ResponseEntity<>(categoryDto,HttpStatus.OK);
    }
//    getAll
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = this.categoryService.getAllCategories();
        return ResponseEntity.ok().body(categories);
    }
}
