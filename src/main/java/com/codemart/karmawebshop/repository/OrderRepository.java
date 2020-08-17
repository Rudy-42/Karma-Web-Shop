package com.codemart.karmawebshop.repository;

import com.codemart.karmawebshop.entity.Order;

import java.util.List;

public interface OrderRepository extends BaseRepository<Order> {
    List<Order> getAllByCartUserUsername(String username);
}
