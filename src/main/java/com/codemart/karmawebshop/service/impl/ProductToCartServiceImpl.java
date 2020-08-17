package com.codemart.karmawebshop.service.impl;

import com.codemart.karmawebshop.dto.ProductQuantityDto;
import com.codemart.karmawebshop.entity.Cart;
import com.codemart.karmawebshop.entity.ProductToCart;
import com.codemart.karmawebshop.repository.ProductToCartRepository;
import com.codemart.karmawebshop.service.ProductToCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductToCartServiceImpl implements ProductToCartService {

    @Autowired
    ProductToCartRepository productToCartRepository;

    @Override
    public void save(ProductToCart productToCart) {
        productToCartRepository.save(productToCart);
    }

    @Transactional
    @Override
    public void update(List<ProductQuantityDto> productQuantityDtos, Cart cart) {
        List<ProductToCart> productToCarts = getAllProductsFromCart(cart);

        productQuantityDtos.forEach(x-> productToCartRepository.getByProductIdAndCart_Id(x.getId(),cart.getId()).setQuantity(x.getQuantity()));

    }

    @Override
    public List<ProductToCart> getAllProductsFromCart(Cart cart) {
        return productToCartRepository.getAllByCartId(cart.getId());
    }

}
