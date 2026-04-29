package com.coatingbazaar.api.controller;

import com.coatingbazaar.api.model.Category;
import com.coatingbazaar.api.model.Product;
import com.coatingbazaar.api.service.CategoryService;
import com.coatingbazaar.api.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/prices")
@CrossOrigin(origins = "*")
public class PricesController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public PricesController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public List<Map<String, Object>> getAllPrices() {
        List<Map<String, Object>> result = new ArrayList<>();
        for (Category category : categoryService.getAllCategories()) {
            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("category", category);
            entry.put("products", productService.getProductsByCategory(category.getId()));
            result.add(entry);
        }
        return result;
    }
}
