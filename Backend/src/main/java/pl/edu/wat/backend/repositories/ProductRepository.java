package pl.edu.wat.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wat.backend.entities.ProductEntity;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findByCat_CategoryID(Long categoryID);
    Optional<ProductEntity> findByProductIDAndCat_CategoryID(Long productID, Long categoryID);
    List<ProductEntity> findByCat_CategoryIDAndProductID(Long productID, Long categoryID);

    List<ProductEntity> findByProductID(Long productID);
}
