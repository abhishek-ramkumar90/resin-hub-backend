package com.coatingbazaar.api.model;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private String description;
    private int productCount;
    private List<String> subcategories;

    public Category() {}

    public Category(String id, String name, String description, int productCount, List<String> subcategories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.productCount = productCount;
        this.subcategories = subcategories;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getProductCount() { return productCount; }
    public void setProductCount(int productCount) { this.productCount = productCount; }
    public List<String> getSubcategories() { return subcategories; }
    public void setSubcategories(List<String> subcategories) { this.subcategories = subcategories; }
}
