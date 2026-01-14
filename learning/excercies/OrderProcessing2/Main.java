package excercies.OrderProcessing2;

enum order {
    PENDING,
    PROCESSING,
    COMPLETED,
    FAILED
}

interface Item {
    String getName();
    double getPrice();
}

class Product implements Item {
    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

}

public class Main {
    public static void main(String[] args) {
        
    }
}
