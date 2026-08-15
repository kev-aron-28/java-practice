# Behavorial patterns

## Strategy
Strategy encapsulates interchangeable algorithms behind an interface.

So instead of:

``` java
if (type.equals("CARD")) {
    ...
} else if (type.equals("PAYPAL")) {
    ...
} else if (type.equals("CRYPTO")) {
    ...
}
```

we create strategies

``` java
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardStrategy implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("Credit card");
    }
}

class PaypalStrategy implements PaymentStrategy {

    public void pay(double amount) {
        System.out.println("PayPal");
    }
}
```

and then

``` java
class PaymentService {

    private final PaymentStrategy strategy;

    public PaymentService(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void pay(double amount) {
        strategy.pay(amount);
    }
}
```

## Observer

One object changes state and automatically notifies multiple interested objects.


``` java
interface Observer {
    void update(String message);
}

class EventPublisher {

    private final List<Observer> observers = new ArrayList<>();

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void publish(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}
```

## Command
Command turns an operation into an object.


Instead of:

``` java
editor.save();
editor.copy();
editor.delete();
```

you represent operations as objects

``` java
interface Command {
    void execute();
}

class SaveCommand implements Command {

    private final Editor editor;

    public SaveCommand(Editor editor) {
        this.editor = editor;
    }

    public void execute() {
        editor.save();
    }
}

Command command = new SaveCommand(editor);

command.execute();
```
Now commands can be:
- queued
- logged
- undone
- retried
- scheduled

