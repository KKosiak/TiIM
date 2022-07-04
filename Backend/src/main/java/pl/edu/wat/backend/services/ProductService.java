package pl.edu.wat.backend.services;

import org.springframework.http.ResponseEntity;
import pl.edu.wat.backend.dtos.CategoryRequest;
import pl.edu.wat.backend.dtos.ProductRequest;
import pl.edu.wat.backend.dtos.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    List<ProductResponse> getAllProductsByCategoryID(Long orderID);

    ResponseEntity deleteProduct(Long orderID, Long productID);

    ProductResponse addProduct(ProductRequest productRequest, CategoryRequest categoryRequest);

    ProductResponse updateProduct(ProductRequest productRequest);


    List<ProductResponse> getProductByCategoryIdAndProductId(Long orderID, Long productID);

    List<ProductResponse> getProductById(Long productID);
}
