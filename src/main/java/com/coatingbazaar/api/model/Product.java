package com.coatingbazaar.api.model;

public class Product {
    private String id;
    private String name;
    private String grade;
    private String brand;
    private String location;
    private double price;
    private String unit;
    private double change;
    private String updatedAgo;
    private String categoryId;

    public Product() {}

    public Product(String id, String name, String grade, String brand, String location,
                   double price, String unit, double change, String updatedAgo, String categoryId) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.brand = brand;
        this.location = location;
        this.price = price;
        this.unit = unit;
        this.change = change;
        this.updatedAgo = updatedAgo;
        this.categoryId = categoryId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    public double getChange() { return change; }
    public void setChange(double change) { this.change = change; }
    public String getUpdatedAgo() { return updatedAgo; }
    public void setUpdatedAgo(String updatedAgo) { this.updatedAgo = updatedAgo; }
    public String getCategoryId() { return categoryId; }
    public void setCategoryId(String categoryId) { this.categoryId = categoryId; }
}
