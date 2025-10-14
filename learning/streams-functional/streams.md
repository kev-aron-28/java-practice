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

- Predicate<T> → returns boolean
- Function<T,R> → transforms T → R
- Consumer<T> → takes T, returns nothing
- Supplier<T> → takes nothing, returns T
- BiFunction<T,U,R> → two inputs, one output

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


# Streams
Stream was introduced in Java 8, the Stream API is used to process collections of objects. 
A stream in Java is a sequence of objects that supports various methods that can be pipelined to produce the desired result.

The uses of Stream in Java are mentioned below:

- Stream API is a way to express and process collections of objects.
- Enable us to perform operations like filtering, mapping, reducing and sorting.


# How to create a stream

Stream<T> stream;


# Features
The features of Java streams are mentioned below:

- A Stream is not a data structure; it just takes input from Collections, Arrays or I/O channels.
- Streams do not modify the original data; they only produce results using their methods.
- Intermediate operations (like filter, map, etc.) are lazy and return another Stream, so you can chain them together.
- A terminal operation (like collect, forEach, count) ends the stream and gives the final result.

# Operations

1. Intermediate operations: Intermediate Operations are the types of 
operations in which multiple methods are chained in a row.
2. Terminal operations: Ends stream and give final result


# IMportant intermediate operations

1. map(): Return a stream consisting of the results of applying the given function to the elements of stream
<R> Stream<R> map(Function<? super T, ? extends R> mapper)

2. filter(): The filter method is used to select elements as per the Predicate passed as an argument.
Stream<T> filter(Predicate<? super T> predicate)

3. sorted(): The sorted method is used to sort the stream.
Stream<T> sorted()
Stream<T> sorted(Comparator<? super T> comparator)

4. flatMap(): The flatMap operation in Java Streams is used to flatten a stream of collections into a single stream of elements.

<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)

5. distinct(): Removes duplicate elements. It returns a stream consisting of the distinct elements (according to Object.equals(Object)).
Stream<T> distinct()

6. peek(): Performs an action on each element without modifying the stream. It returns a stream consisting of the elements of this stream, 
additionally performing the provided action on each element as elements are consumed from the resulting stream.

Stream<T> peek(Consumer<? super T> action)


# Terminal operations

1. collect(): The collect method is used to return the result of the intermediate operations performed on the stream.
<R, A> R collect(Collector<? super T, A, R> collector)

2. 2. forEach(): The forEach method is used to iterate through every element of the stream.

void forEach(Consumer<? super T> action)

3. reduce(): The reduce method is used to reduce the elements of a stream to a single value. The reduce method takes a BinaryOperator as a parameter.
T reduce(T identity, BinaryOperator<T> accumulator)
Optional<T> reduce(BinaryOperator<T> accumulator)

4. count(): Returns the count of elements in the stream.
long count()

5. findFirst(): Returns the first element of the stream, if present.
Optional<T> findFirst()

6. allMatch(): Checks if all elements of the stream match a given predicate.
boolean allMatch(Predicate<? super T> predicate)

7. anyMatch(): Checks if any element of the stream matches a given predicate.
boolean anyMatch(Predicate<? super T> predicate)




































































































