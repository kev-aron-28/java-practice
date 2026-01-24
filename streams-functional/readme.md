# STreams

Compared to collections, streams provide a view of data that lets you specify computations at a higher conceptual level.
allows you to process sequences of values in a “what, not how” style.

1. A stream does not store its elements. 
2. Stream operations dont mutate their source. 
3. Stream operations are lazy when possible. 

# Stream creation

When you have an array you can use the Stream.of static method.
and this method has a varargs parameter so you can construct a stream from any number of arguments:

``` java
Stream<String> words = Stream.of({ "String", "Stream" });
```

Or you can use the Arrays.stream(array, from, to) to make a stream from a part of an array.

Also you can make a empty stream with the Stream.empty()

``` java
Stream<String> silence = Stream.empty();
```

The Stream interface has two static methods for making infinite streams. The **Generate method** takes a function with no parameters or a supplier

``` java
Stream<String> echos = Stream.generate(() -> "ECHO");

echos.forEach((v) -> System.out.println(v));
```

Finally, the Stream.ofNullable method makes a really short stream from an object he stream has length 0 if the
object is  null or length 1 otherwise, containing just the object. This is mostly useful in conjunction with 
flatMap

## The filter, map, and flatmap methods

A stream transformation produces a stream whose elements are derived from those of another stream. 
the  filter transformation that yields a new stream with those elements that match a certain
condition.

Often, you want to transform the values in a stream in some way. Use the 
map method and pass the function that carries out the transformation.

# Extracting substreams and combining streams
The call stream.limit(n) returns a new stream that ends after n elements (or when the original stream ends if it is
shorter). This method is particularly useful for cutting infinite streams down to size. 

``` java
Stream<Integer> integers = Stream.iterate(0, n -> n < 100, n -> n + 1).limit(10)
```

The stream .takeWhile( predicate ) call takes all elements from the stream while the predicate is 
true, and then stops

``` java
Stream.iterate(0, n -> n + 1).takeWhile(n -> n < 5).forEach(System.out::println);

```

The  dropWhile method does the opposite, dropping elements while a condition is 
true and yielding a stream of all elements starting with the first one for which the condition was  false

``` java
Stream.iterate(0, n -> n + 1).dropWhile(n -> n < 5).forEach(System.out::println);
```
