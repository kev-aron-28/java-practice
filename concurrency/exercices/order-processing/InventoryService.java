package exercices.order-processing;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class InventoryService {
    private final Map<String, Integer> inventory = new ConcurrentHashMap<>();

    public InventoryService() {
        inventory.put("Laptop", 10);
        inventory.put("Mouse", 50);
    }

    public boolean reserve(String productId, int quantity) {
        AtomicBoolean success = new AtomicBoolean(false);

        inventory.compute(productId, (key, stock) -> {
            if(stock == null) {
                return null;
            }

            if(stock >= quantity) {
                success.set(true);

                return stock - quantity;

            }

            return stock;
        });

        return success.get();
    }

    public int getStock(String productId) {
        return this.inventory.getOrDefault(productId, 0);
    }

}
