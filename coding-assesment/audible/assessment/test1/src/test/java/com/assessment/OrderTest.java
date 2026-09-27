package com.assessment;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    void newOrderShouldStartAsPending() {

        Order order = TestData.order();

        assertEquals(
            OrderStatus.PENDING,
            order.getStatus()
        );
    }

    @Test
    void totalShouldBeCalculatedCorrectly() {

        Order order = new Order(
            "ORD-1",
            "CUSTOMER-1",
            List.of(
                new OrderItem(
                    "P-1",
                    new BigDecimal("10.00"),
                    2
                ),
                new OrderItem(
                    "P-2",
                    new BigDecimal("5.50"),
                    3
                )
            )
        );

        assertEquals(
            new BigDecimal("36.50"),
            order.getTotal()
        );
    }

    @Test
    void itemsShouldNotBeExternallyMutable() {

        List<OrderItem> items = new ArrayList<>();
        items.add(TestData.item());

        Order order = new Order(
            "ORD-1",
            "CUSTOMER-1",
            items
        );

        items.clear();

        assertEquals(1, order.getItems().size());
    }
}