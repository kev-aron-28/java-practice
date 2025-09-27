# What is lambda in java?

Essentially a way to write anonymous functions without a name. Introduced in Java 8
Before lambdas, you had to use anonymous inner classes

Ex.
Runnable r = () -> System.out.println();

# Functional interfaces

A lambda always implements a functional interface (an interface with exactly one abstract method, 
called a SAM – Single Abstract Method).

```java
@FunctionalInterface
interface MyInterface {
	int apply(int x, int y)
}

MyInterface add = (a, b) -> a + b
```

## Built in
Common built-in functional interfaces in java.util.function:

Predicate<T> → returns boolean
Function<T,R> → transforms T → R
Consumer<T> → takes T, returns nothing
Supplier<T> → takes nothing, returns T
BiFunction<T,U,R> → two inputs, one output

# Method references
Lambdas can be replaced with method references when possible:
list.forEach(System.out::println);

Types:
- Static method → ClassName::methodName
- Instance method → obj::methodName
- Constructor → ClassName::new

# Variable Capture

Lambdas can access:
Instance variables (like normal inner classes).
Local variables from enclosing scope – but they must be effectively final


