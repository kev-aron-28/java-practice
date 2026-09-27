# Assessment

1. Scenario

You are working on an internal Order Processing Engine.

The system receives orders containing multiple products. Orders can be created, modified, cancelled and processed.

A simplified version of the existing system has already been implemented, but it contains design and implementation problems.

Your task is to:

Understand the existing code.
Fix the existing implementation.
Complete missing functionality.
Improve the design where necessary.
Make all provided tests pass.
Add your own tests for cases you consider important.

The goal is not simply to make the tests pass.

Your implementation should be production-quality Java.

2. Business Rules

An order has:

orderId
customerId
a collection of OrderItem
a status
creation timestamp

An OrderItem contains:

productId
unitPrice
quantity

The following order statuses exist:

- PENDING
- PROCESSING
- COMPLETED
- CANCELLED

Valid transitions
PENDING      -> PROCESSING
PENDING      -> CANCELLED

PROCESSING   -> COMPLETED
PROCESSING   -> CANCELLED

These transitions are invalid:

COMPLETED -> anything
CANCELLED -> anything
PROCESSING -> PENDING
COMPLETED -> CANCELLED

Invalid state transitions must result in an exception.

# Existing code
You should determine whether this class correctly represents its domain.

Consider:

Invalid quantities.
Invalid prices.
Mutability.
Equality.
Null values.
Whether the object can be safely used inside collections.

This code is intentionally simplistic.

You need to determine:

What should happen when an order doesn't exist?
Where should state-transition rules live?
Should callers be able to mutate an Order directly?
Is HashMap appropriate?
What happens if two threads process the same order?
Is findByCustomer acceptable?
What should happen when an order with the same ID is created twice?

Do not blindly rewrite everything.

Make design decisions based on the domain.

# Requirements you must implement

A. Order creation

The service must reject:

null orders
duplicate IDs
orders without items
invalid order data

Define appropriate exceptions.

[x] Validate null orders 
[x] duplicate id
[x] order without items

B. Order lookup

Implement:

Optional<Order> findById(String id);

The method must not return null.

C. Customer lookup

Implement:

List<Order> findByCustomer(String customerId);

Requirements:

No null results.
Caller must not be able to mutate the service's internal state.
Results should have deterministic ordering.

Choose and document a reasonable ordering strategy.

D. Searching

``` java
List<Order> search(OrderSearchCriteria criteria);
```

The criteria object should support: customerId, status, minimumTotal, maximumTotal, for example

``` java
new OrderSearchCriteria(
    "C001",
    OrderStatus.COMPLETED,
    BigDecimal.valueOf(100),
    BigDecimal.valueOf(500)
);
```

should return completed orders for customer C001 whose total is between 100 and 500.

You need to decide:

- How null criteria is handled.
- Whether boundaries are inclusive.
- Ordering of results.
- Whether the criteria object should be mutable.

E. Order Statistics

``` java
OrderStatistics getStatistics();
```

OrderStatistics should expose at least:
- totalOrders
- pendingOrders
- processingOrders
- completedOrders
- cancelledOrders
- totalRevenue

# Concurrency
The service may now be accessed by multiple threads.
The implementation must be thread-safe.

However, there is an important requirement: An order transition must be atomic

# Idempotency
Processing the same order twice should be considered an error.
Implement appropriate behavior based on your domain model.

# Tests
The provided tests are not enough.
Write tests for at least:

- null order
- null ID
- empty ID
- null customer
- empty customer
- empty items
- null item
- invalid quantity
- invalid price

Test every valid transition and every invalid transition.