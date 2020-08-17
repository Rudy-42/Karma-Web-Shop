package com.codemart.karmawebshop.repository;

import com.codemart.karmawebshop.entity.Product;
import com.codemart.karmawebshop.entity.ProductToCart;

import java.util.List;

public interface ProductToCartRepository extends BaseRepository<ProductToCart> {
    List<ProductToCart> getAllByCartId(long id);
    ProductToCart getByProductIdAndCart_Id(long product_id, long cart_id);
}
