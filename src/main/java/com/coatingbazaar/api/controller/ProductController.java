package com.coatingbazaar.api.controller;

import com.coatingbazaar.api.model.Product;
import com.coatingbazaar.api.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProductsFlat();
    }

    @GetMapping("/by-category")
    public Map<String, List<Product>> getProductsByCategory() {
        return productService.getAllProducts();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable String categoryId) {
        return productService.getProductsByCategory(categoryId);
    }
}
