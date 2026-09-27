package com.assessment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.assessment.domain.exceptions.ExistingOrderException;
import com.assessment.domain.exceptions.OrderNotFoundException;

public class OrderService {

    private final Map<String, Order> orders = new HashMap<>();

    public void create(Order order) {
        if(order == null) throw new OrderNotFoundException("order");

        String id = order.getId();

        if(orders.containsKey(id)) throw new ExistingOrderException(id);    

        orders.put(id, order);
    }

    public Order findById(String id) {
        if (!orders.containsKey(id)) {
            throw new OrderNotFoundException(id);
        }

        return orders.get(id);
    }

    public List<Order> findByCustomer(String customerId) {

        List<Order> result = new ArrayList<>();

        for (Order order : orders.values()) {
            if (order.getCustomerId().equals(customerId)) {
                result.add(order);
            }
        }

        return result;
    }

    public void process(String orderId) {
        Order order = findById(orderId);

        order.process();
    }

    public void complete(String orderId) {
        Order order = findById(orderId);

        order.complete();
    }

    public void cancel(String orderId) {

        Order order = findById(orderId);

        order.cancel();
    }
}