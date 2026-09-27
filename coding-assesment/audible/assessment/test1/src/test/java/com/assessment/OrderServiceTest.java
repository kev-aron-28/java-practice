package com.assessment;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.assessment.domain.exceptions.ExistingOrderException;

class OrderServiceTest {

    private OrderService service;

    @BeforeEach
    void setUp() {
        service = new OrderService();
    }

    @Test
    void shouldCreateOrder() {

        Order order = TestData.order();

        service.create(order);

        assertEquals(
            Optional.of(order),
            service.findById(order.getId())
        );
    }

    @Test
    void shouldRejectDuplicateOrderId() {

        Order order = TestData.order();

        service.create(order);

        assertThrows(
            ExistingOrderException.class,
            () -> service.create(order)
        );
    }

    @Test
    void shouldProcessPendingOrder() {

        Order order = TestData.order();

        service.create(order);

        service.process(order.getId());
    }

    @Test
    void shouldNotCompletePendingOrder() {

        Order order = TestData.order();

        service.create(order);

    }

    @Test
    void shouldCancelPendingOrder() {

        Order order = TestData.order();

        service.create(order);

        service.cancel(order.getId());
    }

    @Test
    void shouldNotModifyOrderReturnedByCustomerSearch() {

        Order order = TestData.order();

        service.create(order);

        List<Order> result =
            service.findByCustomer(order.getCustomerId());

        result.clear();

        assertEquals(
            1,
            service.findByCustomer(order.getCustomerId()).size()
        );
    }
}