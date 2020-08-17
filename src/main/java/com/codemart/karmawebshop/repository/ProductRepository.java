package com.codemart.karmawebshop.repository;

import com.codemart.karmawebshop.dto.CategoryAndSize;
import com.codemart.karmawebshop.entity.Product;

import java.util.List;

public interface ProductRepository extends BaseRepository<Product> {
    List<Product> getAllByActiveTrue();
    List<Product> getAllByCategoryAndActiveTrue(String category);
    List<Product> getAllByPriceBetween(long p1, long p2);
    int countProductByCategoryAndActiveTrue(String category);
}
