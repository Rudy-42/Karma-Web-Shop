package com.codemart.karmawebshop.service;

import com.codemart.karmawebshop.dto.ProductQuantityDto;
import com.codemart.karmawebshop.dto.ProductQuantityDtoList;
import com.codemart.karmawebshop.entity.Cart;
import com.codemart.karmawebshop.entity.ProductToCart;

import java.util.List;

public interface ProductToCartService {
    void save(ProductToCart productToCart);

    void update(List<ProductQuantityDto> productToCartList, Cart cart);

    List<ProductToCart> getAllProductsFromCart(Cart cart);
}
