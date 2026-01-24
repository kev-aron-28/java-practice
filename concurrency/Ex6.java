
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;


enum Status {
    CREATED,
    PAID,
    SHIPPED
};

class Order {
    private int id;
    private Status status;

    public Order(int id, Status status) {
        this.id = id;
        this.status = status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
}

class ProcessCreatedOrders implements Runnable {
    private BlockingQueue<Order> createdOrders;
    private BlockingQueue<Order> paidOrders;

    public ProcessCreatedOrders(BlockingQueue<Order> createdOrders, BlockingQueue<Order> paidOrders) {
        this.createdOrders = createdOrders;
        this.paidOrders = paidOrders;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Order order = createdOrders.take();  
                order.setStatus(Status.PAID);
                Thread.sleep(1000);
                paidOrders.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class ProcessPaidOrders implements Runnable {
    private final BlockingQueue<Order> paidOrders;
    private final BlockingQueue<Order> shippedOrders;

    public ProcessPaidOrders(BlockingQueue<Order> paidOrders, BlockingQueue<Order> shippedOrders) {
        this.paidOrders = paidOrders;
        this.shippedOrders = shippedOrders;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Order order = paidOrders.take();  
                order.setStatus(Status.SHIPPED);
                Thread.sleep(1000);
                shippedOrders.put(order);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class PrintShippedOrders implements  Runnable {
    private final BlockingQueue<Order> shippedOrders;

    public PrintShippedOrders(BlockingQueue<Order> shippedOrders) {
        this.shippedOrders = shippedOrders;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Order order = shippedOrders.take();

                System.out.println("ORDER " + order.getId() + " Proccessed");

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }
    }
}

class CreateOrders implements  Runnable {

    private BlockingQueue<Order> createdOrders;

    public CreateOrders(BlockingQueue<Order> createdOrders) {
        this.createdOrders = createdOrders;
    }

    @Override
    public void run() {
        try {
            for(int i = 0; i < 10; i++) {
                createdOrders.put(new Order(i, Status.CREATED));
            }
        } catch (InterruptedException e) {
        
        }

        return;
    }
}

public class Ex6 {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Order> createdOrders = new ArrayBlockingQueue<>(5);
        BlockingQueue<Order> paid = new ArrayBlockingQueue<>(5);
        BlockingQueue<Order> shipped = new ArrayBlockingQueue<>(5);

        ThreadFactory factory = (r) -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler(() -> {
                
            });
            return t;
        };

        ExecutorService pool = Executors.newFixedThreadPool(5);

        CreateOrders create = new CreateOrders(createdOrders);

        ProcessCreatedOrders processCreated = new ProcessCreatedOrders(createdOrders, paid);

        ProcessPaidOrders processPaid  = new ProcessPaidOrders(paid, shipped);
        
        PrintShippedOrders print = new PrintShippedOrders(shipped);


        pool.execute(create);

        pool.execute(processCreated);

        pool.execute(processPaid);

        pool.execute(print);

        pool.shutdown();
        pool.awaitTermination(10000, TimeUnit.SECONDS);

    }
}
