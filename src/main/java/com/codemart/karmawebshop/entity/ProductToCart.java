package com.codemart.karmawebshop.entity;

import javax.persistence.*;

@Entity
public class ProductToCart {
    @EmbeddedId
    private ProductCartID productCartID;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("cartId")
    private Cart cart;
    private int quantity;

    public ProductToCart(Product product, Cart cart, int quantity) {
        this.productCartID = new ProductCartID(product.getId(), cart.getId());
        this.product = product;
        this.cart = cart;
        this.quantity = quantity;
    }

    public ProductToCart() {
    }

    public ProductCartID getProductCartID() {
        return productCartID;
    }

    public void setProductCartID(ProductCartID productCartID) {
        this.productCartID = productCartID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductToCart{" +
                "productCartID=" + productCartID +
                ", product=" + product +
                ", cart=" + cart +
                ", quantity=" + quantity +
                '}';
    }
}
