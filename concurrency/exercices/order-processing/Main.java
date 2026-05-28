package exercices.order-processing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        InventoryService inventoryService = new InventoryService();

        ExecutorService executor = Executors.newFixedThreadPool(20);

        AtomicInteger successCount = new AtomicInteger();

        for(int i = 0; i < 100; i++) {
            executor.submit(() -> {
                boolean success = inventoryService.reserve("Laptop", 1);

                if(success) {
                    successCount.incrementAndGet();

                    System.out.println("Laptop reserved");
                }
            });
        }

        executor.shutdown();

        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Succesful reservcations: " + successCount.get());

        System.out.println("Final stock: " + inventoryService.getStock("Laptop"));

    }
}
