package exercices.order-processing;

import java.util.concurrent.BlockingQueue;

public class OrderConsumer implements  Runnable {
    private final BlockingQueue<Order> queue;
    private final InventoryService inventory;

    public OrderConsumer(BlockingQueue<Order> queue, InventoryService inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    @Override
    public void run() {
        while (true) { 
            try {

                Order order = queue.take();

                System.out.println("PROCESSING " + order.getId());

                boolean reserved = inventory.reserve(order.getProductKey(), order.getQuantity());

                if(reserved) {
                    System.out.println("STOCK RESERVED FOR " + order.getId());
                } else {
                    System.out.println("OUT OF STOCK FOR " + order.getId());
                }
 

                Thread.sleep(1000);
                
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                break;
            }
        }
    }
    
}
 