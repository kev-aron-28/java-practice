package com.assessment;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.assessment.domain.exceptions.InvalidParameterException;
import com.assessment.domain.exceptions.OrderWithoutItemsException;

public class Order {

    private final String id;
    private final String customerId;
    private final List<OrderItem> items;

    private OrderStatus status;
    private Instant createdAt;

    public Order(
            String id,
            String customerId,
            List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;

        if (id == null || customerId == null) {
            throw new InvalidParameterException("id must not be null");
        }
            
        if (items == null || items.isEmpty()) {
            throw new OrderWithoutItemsException();
        }

        this.items = items;
        this.status = OrderStatus.PENDING;
        this.createdAt = Instant.now();
    }

    public void process() {
        if (status == OrderStatus.PENDING || status == OrderStatus.CANCELLED) {
            status = OrderStatus.PROCESSING;
        }
    }

    public void complete() {
        if (status == OrderStatus.PROCESSING || status == OrderStatus.CANCELLED) {
            status = OrderStatus.COMPLETED;
        }
    }

    public void cancel() {
        status = OrderStatus.CANCELLED;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;

        for (OrderItem item : items) {
            total = total.add(item.getSubtotal());
        }

        return total;
    }
}