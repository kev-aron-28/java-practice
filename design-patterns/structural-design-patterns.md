# Structural design patterns

## Adapter
Adapter makes incompatible interfaces work together

Suppose your application  excepects:

``` java
interface PaymentProcessor {
    void processPayment(double amount);
}
```

but an external application expects:

``` java
class StripeAPI {

    public void makePayment(double amount) {
        System.out.println("Stripe payment");
    }
}
```

So the adapter

``` java
class StripeAdapter implements PaymentProcessor {

    private final StripeAPI stripe;

    public StripeAdapter(StripeAPI stripe) {
        this.stripe = stripe;
    }

    @Override
    public void processPayment(double amount) {
        stripe.makePayment(amount);
    }
}
```

## Decorator
Decorator dynamically adds behavior to an object without modifying its class.

The classic example:

```
Coffee
   |
   +-- Milk
   |
   +-- Sugar
   |
   +-- Whipped Cream
```

```
interface Coffee {
    double cost();
}

class SimpleCoffee implements Coffee {

    public double cost() {
        return 2.0;
    }
}
```

The docorator:

``` java
class MilkDecorator implements Coffee {

    private final Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double cost() {
        return coffee.cost() + 0.5;
    }
}

class SugarDecorator implements Coffee {

    private final Coffee coffee;

    public SugarDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    public double cost() {
        return coffee.cost() + 0.2;
    }
}

Coffee coffee =
        new SugarDecorator(
            new MilkDecorator(
                new SimpleCoffee()
            )
        );

System.out.println(coffee.cost());
```

## Facade
Facade provides a simple interface over a complicated subsystem.

``` java
class OrderFacade {

    private final Inventory inventory;
    private final Payment payment;
    private final Shipping shipping;

    public OrderFacade(
            Inventory inventory,
            Payment payment,
            Shipping shipping) {
        this.inventory = inventory;
        this.payment = payment;
        this.shipping = shipping;
    }

    public void placeOrder(Order order) {

        inventory.checkStock(order);
        payment.charge(order);
        shipping.createShipment(order);
    }
}
```

## Proxy
Proxy is an object that stands in front of another object and controls access to it.


``` java
interface Image {
    void display();
}

class RealImage implements Image {

    public RealImage() {
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading...");
    }

    public void display() {
        System.out.println("Displaying");
    }
}

class ImageProxy implements Image {

    private RealImage realImage;

    public void display() {

        if (realImage == null) {
            realImage = new RealImage();
        }

        realImage.display();
    }
}
```

The real object isnot created until needed

The common use cases:
- lazy loading
- access control
- caching
- logging
- remote calls

