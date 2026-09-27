package com.assessment;

import java.math.BigDecimal;

public class OrderItem {

    private String productId;
    private BigDecimal unitPrice;
    private int quantity;

    public OrderItem(String productId, BigDecimal unitPrice, int quantity) {
        this.productId = productId;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getSubtotal() {
        return unitPrice.multiply(
            BigDecimal.valueOf(quantity)
        );
    }
}