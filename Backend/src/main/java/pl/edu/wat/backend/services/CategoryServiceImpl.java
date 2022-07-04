package pl.edu.wat.backend.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.CategoryRequest;
import pl.edu.wat.backend.dtos.CategoryResponse;
import pl.edu.wat.backend.entities.CategoryEntity;
import pl.edu.wat.backend.repositories.CategoryRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setCategoryID(categoryRequest.getCategoryID());
        categoryEntity.setName(categoryRequest.getName());
        CategoryEntity o = categoryRepository.save(categoryEntity);

        return new CategoryResponse(o.getCategoryID(), o.getName());
    }

    @Override
    public ResponseEntity deleteCategory(Long categoryID) {
        return categoryRepository.findByCategoryID(categoryID).map(category -> {
            categoryRepository.delete(category);

            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new IllegalArgumentException("group not found, id: " + categoryID));
    }

    @Override
    public List<CategoryResponse> getAllCategorys() {

        return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
                .map(entity -> new CategoryResponse(entity.getCategoryID(), entity.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse updateCategory(CategoryRequest categoryRequest)
    {
        return categoryRepository.findByCategoryID(categoryRequest.getCategoryID()).map(category -> {
            category.setCategoryID(categoryRequest.getCategoryID());
            category.setName(categoryRequest.getName());

            CategoryEntity o = categoryRepository.save(category);

            return new CategoryResponse(o.getCategoryID(), o.getName());
        }).orElseThrow(() -> new IllegalArgumentException("category not found, id: " + categoryRequest.getCategoryID()));
    }

    @Override
    public CategoryResponse getCategory(Long categoryID) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findByCategoryID(categoryID);
        if(optionalCategory.isPresent()){
            CategoryEntity categoryEntity = optionalCategory.get();
            return new CategoryResponse(categoryEntity.getCategoryID(), categoryEntity.getName());
        }else
            throw new IllegalArgumentException("There is no category Entity with id" + categoryID);
    }



}
