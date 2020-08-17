package com.codemart.karmawebshop.service;

import com.codemart.karmawebshop.dto.CategoryAndSize;
import com.codemart.karmawebshop.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ProductService {
    void save(MultipartFile image, Product product);

    List<Product> getAllActive();

    Product getProductById(long id);

    void deactivateProduct(long id);

    List<Product> getProductsByCategory(String category);

    List<Product> getProductsByPriceRange(long p1, long p2);

    List<String> getProductsCategory();

    List<CategoryAndSize> getCategoryAndSize();
}
