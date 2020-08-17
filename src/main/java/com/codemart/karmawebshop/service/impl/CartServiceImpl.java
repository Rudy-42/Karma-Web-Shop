package com.codemart.karmawebshop.service.impl;

import com.codemart.karmawebshop.entity.Cart;
import com.codemart.karmawebshop.entity.ProductToCart;
import com.codemart.karmawebshop.entity.User;
import com.codemart.karmawebshop.repository.CartRepository;
import com.codemart.karmawebshop.service.CartService;
import com.codemart.karmawebshop.service.ProductToCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductToCartService productToCartService;

    @Override
    public void createCart(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public Cart getUserActiveCart(long id) {
        return cartRepository.getByActiveTrueAndUserId(id);
    }
    @Override
    public Cart getUserActiveCart(String username) {
        return cartRepository.getByActiveTrueAndUserUsername(username);
    }

    @Override
    public Cart getCartByUserId(long id) {
        return cartRepository.getByUserId(id);
    }

    @Override
    public Cart getCartById(long id) {
        return cartRepository.getOne(id);
    }

    @Override
    public long getTotal(long cartId) {
        return productToCartService.getAllProductsFromCart(getCartById(cartId))
                .stream()
                .reduce(0, (partialResult, x)-> Math.toIntExact(partialResult + (x.getProduct().getPrice() * x.getQuantity())), Integer::sum);

    }
}
