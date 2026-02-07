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

The call stream.skip(n) does the exact opposite. It discards the first n elements.

``` java
Stream<String> words =
Stream.of(contents.split("\\PL+")).skip(1);
```

You can concatenate two streams with the static  concat method of the  Stream class:

``` java
Stream.concat()
```

# Other streams transformations
The distinct method returns a stream that yields elements from the original stream, in the same order, except that
duplicates are suppressed. The duplicates need not be adjacent.

``` java
Stream.of().distinct()
```

For sorting a stream there are several variations of the sorted method. 

```
Stream.sorted();
```

# SImple reductions
Other simple reductions are max and min that return the largest or smallest value. There is a twist, these methods return an Optional<T>
value that either wraps the answer or indicates that there is none. 

The findFirst returns the first vlaue in a nonEmpty collection. and if you are okay with any match then you can use the findAny

There are methods like 
- allMatch
- noneMatch
That return true if all or no elements match a predicate

# The optional type
An  Optional<T> bject is a wrapper for either an object of type  T or no object. In the former case, we say that the
value is present. The  Optional<T> type is intended as a safer alternative for a reference of type T that either refers to an
object or is null.

The key to using the Optional effectively is to use a method that either produces an alterantive if the value is not present
or consumes the value only if its present

Ofthen there is a default that you want to use when there was no match, perhaps the empty string

``` java
String result = optionalString.orElse();
```

Or you can also invoke code to compute the default

```
String result = optionalString.orElseGet(() -> System.getProperty("myapp.default"));
```

Also you can throw an exception if theres is no value

``` java
String result = optionalString.orElseThrow(IllegalStateException::new);
```

## Consuming an Optional value
The ifPresent method access a function. If the optional value exists, it is passed to that function. Otherwise nothing happens.

``` java
optionalValue.ifPresent(v -> *Process v*);
```

Now, if you want to take an action if the value is present and another if the value is not, the you use ifPresentOrElse