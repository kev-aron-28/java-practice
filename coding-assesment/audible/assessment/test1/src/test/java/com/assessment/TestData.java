package com.assessment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestData {
    public static Order order() {
        List<OrderItem> items = new ArrayList<>();
        items.add(item());

        return new Order(
            UUID.randomUUID().toString(), 
            UUID.randomUUID().toString(), 
            items
        );
    }

    public static OrderItem item() {
        return new OrderItem(
            UUID.randomUUID().toString(), 
            new BigDecimal(29),
            3);
    }
}
