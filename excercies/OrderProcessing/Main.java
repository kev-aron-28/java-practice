
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Order {
    private int id;
    private int amount;

    public Order(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }
}

class OrderProducer implements  Runnable {
    private BlockingQueue<Order> queue;
    private final int totalOrders;
    private final int poisonPill;

    public OrderProducer(BlockingQueue<Order> queue, int totalOrders, int poisonPillConsumers) {
        this.queue = queue;
        this.totalOrders = totalOrders;
        this.poisonPill = poisonPillConsumers;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < totalOrders; i++) {
                Order order = new Order(i, 100);
                queue.put(order);
            }

            for(int i = 0; i < poisonPill; i++) {
                queue.put(new Order(-1, 0));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}

class Consumer implements  Runnable {
    private final BlockingQueue<Order> queue;
    private final AtomicInteger totalRevenue;
    private final AtomicInteger processedOrders;

    public Consumer(BlockingQueue<Order> queue, AtomicInteger totalRevenue, AtomicInteger processedOrders) {
        this.queue = queue;
        this.totalRevenue = totalRevenue;
        this.processedOrders = processedOrders;
    }

    @Override
    public void run() {
       try {
            while (true) { 
                Order order = queue.take();

                if(order.getId() == -1) {
                    break;
                }

                Thread.sleep(100);

                totalRevenue.addAndGet(order.getAmount());
                processedOrders.incrementAndGet();
            }
       } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
       }
    }
}

public class Main {    
    public static void main(String[] args) throws InterruptedException {
        int totalOrders = 100;
        int comsumers = 4;
        
        BlockingQueue<Order> queue = new LinkedBlockingQueue<>();

        AtomicInteger totalRevenue = new AtomicInteger(0);
        AtomicInteger processedOrders = new AtomicInteger(0);

        ExecutorService pool = Executors.newFixedThreadPool(comsumers);

        for(int i = 0; i < comsumers; i++) {
            pool.submit(new Consumer(queue, totalRevenue, processedOrders ));
        }

        Thread producer = new Thread(new OrderProducer(queue, totalOrders, comsumers));

        producer.start();

        producer.join();

        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);

        System.out.println("Processed orders: " + processedOrders.get());
        System.out.println("Total revenue: " + totalRevenue.get());
        
    }
}
