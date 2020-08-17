package com.codemart.karmawebshop.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProductCartID implements Serializable {
    private long productId;
    private long cartId;

    public ProductCartID(long productId, long cartId) {
        this.productId = productId;
        this.cartId = cartId;
    }

    public ProductCartID() {
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCartId() {
        return cartId;
    }

    public void setCartId(long cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductCartID that = (ProductCartID) o;
        return productId == that.productId &&
                cartId == that.cartId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, cartId);
    }
}
