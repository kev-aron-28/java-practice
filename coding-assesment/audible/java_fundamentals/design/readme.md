# Design practices

What good design actually means?
The code makes the intended behavior easy to understand, change, test, and extend without causing unreleated parts of the system break

A good design usually has several properties:
- High cohesion
- Low coupling
- Clear responsabilities
- Explicit dependencies
- Encapsulation
- Appropiate abstractions
- Small, meaningful interfaces
- Predictable behavior
- Testability
- Changeability

## The fundamental problem: change
This is probably the most important concept

``` java
class OrderService {

    public void createOrder(Order order) {
        // validate
        // calculate price
        // save to database
        // send email
        // update inventory
        // generate invoice
    }
}
```

It may work perfectly, the problem appears when requirements change

# Cohesion
Cohesion describes how strongly the responsibilites inside a module / class belong together. A class should have a strong reason to exists, not necessarily one method, not necessarily one respo, but its responsabilities shoould form a coherent concept

# Coupling
Coupling is about how strongly one component depends on other, so is better when the service depends on contracts, not implementations

Abstract when the abstraction represents a meaningful boundary or variation

# Encapsulation
An object controls its own invariants and prevents outside code from putting it into an invalid state

# Tell dont ask
A useful OO principle is:
"Tell objects what to do instead of asking them for their data and doing the work externall", this is much more than style, the business rule is now located where the state lives

# Avoid anemic domain objects when behavior belongs there
An anemic often looks like:

``` java
class Order {

    private BigDecimal total;
    private OrderStatus status;

    // getters/setters
}

class OrderService {

    void confirm(Order order) {

        if (order.getStatus() == PENDING) {
            order.setStatus(CONFIRMED);
        }
    }
}
```

The object is essentially a data container, sometimes thats perfectly fine but when the object has meaningful business rules, putting all behavior into services can create procedural code disguised as OO

Anemic models arent automatically bad, DTO, request objects, persistence projects, and simple data structes can legitimiely be data-oriented

# Single responsability principle
A class should have coherent responsibility and therefore a coherent reason to change

Consider:

``` java
class InvoiceService {

    void calculateInvoice() {}
    void saveInvoice() {}
    void sendInvoiceEmail() {}
    void generatePdf() {}
}
```

There are multiple independent reasons to change: pricing rules, database, email system, PDF format and thats a design smell

# Prefer composition over inheritance
Inheritance is powerful, but it creates a strong coupling between parent and child

# Interfaces should represent capabilities / contracts

# Value objects
A powerful design technique is replacing primitive combinations with meaningful types

Instead of:
``` java
class InvoiceService {

    void calculateInvoice() {}
    void saveInvoice() {}
    void sendInvoiceEmail() {}
    void generatePdf() {}
}
```

you might have 

``` java
void transfer(
    AccountNumber account,
    Money amount
)
```

now the type system communicates the domain, all

# Law of demeter
A useful heuristic: An object should generally talk to its immediate collaboratos rather than navigating deep object graphs
The smell is deep knowledge of another objets internal structure

# Avoid boolean parameters when they obscure meaning

This: processOrder(order, true, false);
is difficult to understand, what do true and false mean? Better:

``` java
processOrder(
    order,
    ProcessingOptions.withNotification()
);
```

or an explicit configuration / value object

# make invalid states difficult to represent
Not every primitive needs a wrapper, but domain-critical concepts often benefit from one

# Dont make everything public
PUblic API are contracts, prefer exposing operations rather than arbitrary setters

# Good method design
A good method usually has:
- clear name
- focused responsability
- limited parameters
- predictable behavior
- explicit dependencies
- minimal hidden state
- useful return value
- meaningful exceptions

# Testing is a desing signal
Good testability is often evidence of good separation of concerns

# The biggets design principle
If i need to change X, how much unrelated code do i have to touch?