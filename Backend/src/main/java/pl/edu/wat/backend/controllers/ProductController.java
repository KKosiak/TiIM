package pl.edu.wat.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.backend.dtos.CategoryRequest;
import pl.edu.wat.backend.dtos.ProductRequest;
import pl.edu.wat.backend.dtos.ProductResponse;
import pl.edu.wat.backend.entities.ProductEntity;
import pl.edu.wat.backend.services.ProductService;

import java.util.List;
@RestController

@CrossOrigin("http://localhost:3000")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductResponse>> getProducts() {
        List<ProductResponse> products = productService.getAllProducts();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/product/{productID}")
    public ResponseEntity<List<ProductResponse>> getProduct(@PathVariable Long productID) {
        List<ProductResponse> products = productService.getProductById(productID);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryID}/product")
    public ResponseEntity<List<ProductResponse>> getAllProductsByOrderId( @PathVariable Long categoryID) {
        List<ProductResponse> products = productService.getAllProductsByCategoryID(categoryID);

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/categories/{categoryID}/product/{productID}")
    public ResponseEntity<List<ProductResponse>> getProductByOrderIdAndProductId( @PathVariable Long categoryID, @PathVariable Long productID) {
        List<ProductResponse> products = productService.getProductByCategoryIdAndProductId(categoryID, productID);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/categories/{categoryID}/product")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest, CategoryRequest categoryRequest) {
        productService.addProduct(productRequest, categoryRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/categories/{categoryID]/product/{productID}")
    public ResponseEntity updateMember(@RequestBody ProductRequest productRequest) {
        productService.updateProduct(productRequest);
        System.out.println(productRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/categories/{categoryID}/product/{productID}")
    public ResponseEntity deleteProduct(@PathVariable Long categoryID,
                                       @PathVariable Long productID) {
        return productService.deleteProduct(categoryID, productID);
    }


}
