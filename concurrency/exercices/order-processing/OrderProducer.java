package exercices.order-processing;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class OrderProducer implements Runnable {

    private final BlockingQueue<Order> queue;
    private final Random random = new Random();

    public OrderProducer(BlockingQueue<Order> queue) {
        this.queue = queue;
    }


    @Override
    public void run() {
        String[] products = {
                "Laptop",
                "Mouse"
        };

        while(true) {
            try {
                Order order = new Order("Kevin", products[random.nextInt(products.length)], 1);

                queue.put(order);

                System.out.println("PRODUCED: " + order.getId());

                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

                break;
            }
        }
    }
    
    
}
