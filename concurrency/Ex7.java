
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class OrderItem {
    private final String id;
    private final int quantity;

    public OrderItem(String id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }    
}

class Order {
    private final long id;
    private final List<OrderItem> items;

    public Order(long id, List<OrderItem> items) {
        this.id = id;
        this.items = List.copyOf(items);
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public long getId() {
        return id;
    }
}

class OrderProcessor implements  Runnable {
    BlockingQueue<Order> orders;
    ConcurrentHashMap<String, Integer> quantities;

    public OrderProcessor(BlockingQueue<Order> orders, ConcurrentHashMap<String, Integer> quantities) {
        this.orders = orders;
        this.quantities = quantities;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Order order = orders.take();

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}

public class Ex7 {
    
    
    public static void main(String[] args) {
        List<OrderItem> items = Stream.iterate(1, id -> id + 1).limit(10).map(id -> new OrderItem("Product " + id, id)).toList();
        
        List<Order> orders = IntStream.range(0, 10).mapToObj(i -> new Order(i, items)).toList();
    
        
    }
}
