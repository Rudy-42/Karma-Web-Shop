package com.codemart.karmawebshop.dto;

public class CategoryAndSize {
    String category;
    int size;

    public CategoryAndSize(String category, int size) {
        this.category = category;
        this.size = size;
    }

    public CategoryAndSize() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "CategoryAndSize{" +
                "category='" + category + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
