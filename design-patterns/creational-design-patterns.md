# Factory method
Instead of creating objects directly with new, you delegate creation to a method / factory

The client does not need to know the concrete class beign instantiated

Imagine:

``` java
Payment payment;

if (type.equals("CREDIT_CARD")) {
    payment = new CreditCardPayment();
} else if (type.equals("PAYPAL")) {
    payment = new PaypalPayment();
}
```

As the number of payment types grows this becomes messy

``` java
interface Payment {
    void pay(double amount);
}

class CreditCardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paying with credit card");
    }
}

class PaypalPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Paying with PayPal");
    }
}
```

Then the factory:

``` java
class PaymentFactory {

    public static Payment create(String type) {

        return switch (type) {
            case "CREDIT_CARD" -> new CreditCardPayment();
            case "PAYPAL" -> new PaypalPayment();
            default -> throw new IllegalArgumentException("Unknown payment");
        };
    }
}

Payment payment = PaymentFactory.create("PAYPAL");

payment.pay(100);
```


# Builder
Builder separates the construction of a complex object from the objects final representation

Instead of:
``` java
User user = new User(
    "Kevin",
    "email",
    25,
    true,
    "Mexico",
    ...
);

User user = new User.Builder()
        .name("Kevin")
        .email("email")
        .age(25)
        .active(true)
        .country("Mexico")
        .build();
```

# SIngleton

Guarantees that a class has one instance and provides access to it

``` java
class DatabaseConnection {

    private static DatabaseConnection instance;

    private DatabaseConnection() {}

    public static DatabaseConnection getInstance() {

        if (instance == null) {
            instance = new DatabaseConnection();
        }

        return instance;
    }
}
```

