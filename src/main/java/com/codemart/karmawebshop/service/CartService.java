package com.codemart.karmawebshop.service;

import com.codemart.karmawebshop.entity.Cart;

public interface CartService {
    void createCart(Cart cart);

    Cart getUserActiveCart(long id);

    Cart getUserActiveCart(String username);

    Cart getCartByUserId(long id);

    Cart getCartById(long id);

    long getTotal(long cartId);

}
