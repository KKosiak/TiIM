package pl.edu.wat.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dtos.CategoryRequest;
import pl.edu.wat.backend.dtos.CategoryResponse;
import pl.edu.wat.backend.entities.CategoryEntity;
import pl.edu.wat.backend.repositories.CategoryRepository;
import pl.edu.wat.backend.services.CategoryService;

import java.util.List;

@RestController

@CrossOrigin("http://localhost:3000")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryResponse>> getCategorys() {
        List<CategoryResponse> categories = categoryService.getAllCategorys();
        System.out.println(categories);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryID}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Long categoryID) {
        CategoryResponse category = categoryService.getCategory(categoryID);
        System.out.println(category);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity addCategory(@RequestBody CategoryRequest categoryRequest) {
        System.out.println(categoryRequest);
        categoryService.addCategory(categoryRequest);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/categories/{categoryID}")
    public ResponseEntity updateCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.updateCategory(categoryRequest);
        System.out.println(categoryRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryID}")
    public ResponseEntity deleteCategory(@PathVariable Long categoryID) {
        categoryService.deleteCategory(categoryID);
        return new ResponseEntity(HttpStatus.OK);
    }


}
