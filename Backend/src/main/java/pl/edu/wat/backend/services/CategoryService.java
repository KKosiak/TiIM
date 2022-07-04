package pl.edu.wat.backend.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import pl.edu.wat.backend.dtos.CategoryRequest;
import pl.edu.wat.backend.dtos.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategorys();

    CategoryResponse addCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategory(CategoryRequest categoryRequest);

    ResponseEntity deleteCategory(Long categoryID);

    CategoryResponse getCategory(Long categoryID);
}
