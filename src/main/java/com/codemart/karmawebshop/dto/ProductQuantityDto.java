package com.codemart.karmawebshop.dto;

import com.codemart.karmawebshop.entity.Product;


public class ProductQuantityDto {
    private long id;
    private String name;
    private long price;
    private String category;
    private String imgPath;
    private int quantity;

    public ProductQuantityDto() {
    }

    public ProductQuantityDto(Product p, int quantity) {
        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
        this.category = p.getCategory();
        this.imgPath = p.getImgPath();
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
