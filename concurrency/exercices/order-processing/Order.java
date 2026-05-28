package exercices.order-processing;

import java.util.UUID;

public class Order {
    private final UUID id;
    private final String customerName;
    private final String productKey;
    private final int quantity;

    public Order(String customerName, String productKey, int quantity) {
        this.customerName = customerName;
        this.productKey = productKey;
        this.quantity = quantity;
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductKey() {
        return productKey;
    }

    public int getQuantity() {
        return quantity;
    }    
}
