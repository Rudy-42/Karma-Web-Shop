package com.codemart.karmawebshop.repository;

import com.codemart.karmawebshop.entity.Cart;
import com.codemart.karmawebshop.entity.Product;

import java.util.List;

public interface CartRepository extends BaseRepository<Cart>{
    Cart getByUserId(long id);
    Cart getByActiveTrueAndUserId(long id);
    Cart getByActiveTrueAndUserUsername(String user_username);
}
