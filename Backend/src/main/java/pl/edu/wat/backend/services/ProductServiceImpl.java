package pl.edu.wat.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.edu.wat.backend.dtos.CategoryRequest;
import pl.edu.wat.backend.dtos.ProductRequest;
import pl.edu.wat.backend.dtos.ProductResponse;
import pl.edu.wat.backend.entities.ProductEntity;
import pl.edu.wat.backend.entities.CategoryEntity;
import pl.edu.wat.backend.repositories.CategoryRepository;
import pl.edu.wat.backend.repositories.ProductRepository;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<ProductResponse> getAllProducts()
    {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(entity -> new ProductResponse(entity.getProductID(), entity.getName(), entity.getPrice(), entity.getAmount(), entity.getCat().getCategoryID()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductById(Long productID)
    {
        return StreamSupport.stream(productRepository.findByProductID(productID).spliterator(), false)
                .map(entity -> new ProductResponse(entity.getProductID(), entity.getName(), entity.getPrice(), entity.getAmount(), entity.getCat().getCategoryID()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getAllProductsByCategoryID(Long orderID)
    {
        return StreamSupport.stream(productRepository.findByCat_CategoryID(orderID).spliterator(), false)
                .map(entity -> new ProductResponse(entity.getProductID(), entity.getName(), entity.getPrice(), entity.getAmount(), entity.getCat().getCategoryID()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> getProductByCategoryIdAndProductId(Long orderID, Long productID)
    {
        return StreamSupport.stream(productRepository.findByCat_CategoryIDAndProductID(productID, orderID).spliterator(), false)
                .map(entity -> new ProductResponse(entity.getProductID(), entity.getName(), entity.getPrice(), entity.getAmount(), entity.getCat().getCategoryID()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse addProduct(ProductRequest productRequest, CategoryRequest categoryRequest)
    {
        System.out.println(productRequest);
        ProductEntity productEntity = new ProductEntity();
        CategoryEntity categoryEntity = new CategoryEntity();
        productEntity.setProductID(productRequest.getProductID());
        productEntity.setName(productRequest.getName());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setAmount(productRequest.getAmount());
        categoryEntity.setCategoryID(categoryRequest.getCategoryID());

        ProductEntity prod = productRepository.save(productEntity);
        CategoryEntity cat = categoryRepository.save(categoryEntity);
        return new ProductResponse(prod.getProductID(), prod.getName(), prod.getPrice(), prod.getAmount(), cat.getCategoryID());
    }

    @Override
    public ResponseEntity deleteProduct(Long orderID, Long productID) {
        return productRepository.findByProductIDAndCat_CategoryID(productID, orderID).map(productEntity -> {
            productRepository.delete(productEntity);

            return ResponseEntity.ok().build();
        }).orElseThrow(() -> {
            throw new IllegalArgumentException("Member does not exists, memberId: " + orderID + "groupId, " + productID);
        });
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest) {
        return productRepository.findByProductIDAndCat_CategoryID(productRequest.getProductID(), productRequest.getProductID()).map(product -> {
            product.setProductID(productRequest.getProductID());
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());

            ProductEntity prod = productRepository.save(product);

            return new ProductResponse(prod.getProductID(), prod.getName(), prod.getPrice(), prod.getAmount(), prod.getCat().getCategoryID());
        }).orElseThrow(() -> new IllegalArgumentException("order not found, id: " + productRequest.getCategoryID()));
    }


}
