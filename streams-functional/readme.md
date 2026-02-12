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

``` java

optionalValue.ifPresentOrElse(
    v -> do something,
    () -> do something when not present
);
```


# Pipelining optional values

You can transform the value inside an optional by using the map method:

```
Optional<Path> transformed = optionalString.map(Path.of);
```

This is analog of the map method in a Stream interface. Similarly you can use the filter method to only consider
Optional values that fulfill a certain property before or after transfroming it.

``` java
Optional<Path> transformed = optionalString.
    filter(s -> s.endsWith(".txt"))
    .map(Path::of);
```

## Creating optional values
- Optional.of()
- Optional.empty()
- Optional.ofNullable(obj)

## Composing optional value with flatMap
Suppose you have a method f yielding an Optional<T> and the target type T has a method g yielding an Optional<U>

If s.f() is present then g is applied to it. This works to chain methods.

``` java
Optional<Double> result = inverse(arg).flatMap(x -> squareRoot(x));
```

If either the inverse method or the  squareRoot returns Optional.empty(), the result is empty.

## Turning an Optional into a Stream

The stream method turns an Optional<T> into a Stream<T> with zero or more elements. This is useful when methods return an Optional
result. Suppose you have a stream of user IDs and a method

``` java
Optional<User> lookup(String id);
```

Of course you can filter out those invalid IDs and then apply get to the remaining ones:

``` java
Stream<String> ids = . . .;
Stream<User> users = ids.map(Users::lookup)
.filter(Optional::isPresent)
.map(Optional::get);
```

And each call to stream method returns a stream with zero or one element and the flatMap method combines them all. That means that 
the nonexistent users are simply dropped.
 

# Collecting result

When you are done with a stream, you will often want to look at the results. 

- You can call the iterator method which yields an old-fashioned iterator
- call the forEach
- forEachOrdered

But more ofthen thant not, you will want to collect the result in a data structure.
- toList()
- toArray()

Now since its not possible to create a generic array at runtime, the expression stream.toArray returns an Object[].
If you want the correct type, pass in the array constructor

``` java
String result[] = stream.toArray(String[]::new);
```

## Collectors

For collecting stream elements to another target, there is a convenient ** collect ** method, that takes an instance of the Collector interface. 

A collector is an object that accumulates elements and produces a result.
Before toList was added on Java 16, you had to use the collector produced by Collectors.toList()

``` java
List<String> result = stream.collect(Collectors.toList());
```

Also you can collect stream elements into a set

``` java
Set<String> result = stream.collect(Collectors.toSet());
```

IF you want to control which kind of collection you get, use the following call instead:

```
TreeSet<String> result = stream.collect(Collectors.toCollection(TreeSet::new));
```

If you want to collect all strings in a stream by concatenating them. you call

``` java
String result = stream.collect(Collectors.joining());
```

---
Now when you want something like
- sum
- count
- average
- maximum
- minimum

Use one of the summarizing(Int|Long|Double) methods

``` java
Stream<Integer> ints = Stream.of(1,2,3,5,6,34,2,4,32,1);
IntSummaryStatistics summary = ints.collect(Collectors.summarizingInt(i -> i));
```

### Collecting into maps
Collectors.toMap() with to functions that produce the maps keys and values

``` java
Map<Integer, Person> idToPerson = people.collect(
    Collectors.toMap(Person::id, Function.identity())
); 
```

IF theres more than one element with the same key, there is a conflic, and the collector will throw.
You can supply a third function that resolves around the conflict and determines the value for the key

```
Map<String, String> languageNames =
Locale.availableLocales().collect(
    Collectors.toMap(
    Locale::getDisplayLanguage, 
    loc -> loc.getDisplayLanguage(loc),
    (existingValue, newValue) -> existingValue
        )
    );
```

### Grouping and partitioning
Forming groups of values with the same characteristic is very common, so the groupingBy method
supports it directly

``` java
Map<String, List<Locale>> countryToLocales =
Locale.availableLocales().collect(
Collectors.groupingBy(Locale::getCountry));
```

WHen the classifier function is a predicate function (returns a boolean) are partitioned into two lists:
Those where the function returns true and the complement. In this case it is more efficient to use partitioningBy

``` java
Map<Boolean, List<Locale>> englishAndOtherLocales =
Locale.availableLocales().collect(
Collectors.partitioningBy(l ->
l.getLanguage().equals("en")));
List<Locale> englishLocales =
englishAndOtherLocales.get(true);
```

### Downstream collectors

The groupingBy method yields a map whose values are lists. If you want to process those lists in some way
supply a downstream collector

``` java
Map<String, Set<Locale>> countryToLocaleSet =
    Locale.availableLocales().collect(
        groupingBy(Locale::getCountry, toSet())
    );
```

Several collectors are provided for reducing collected elements to numbers


- Counting
``` java
Map<String, Long> countryToLocaleCounts =
    Locale.availableLocales().collect(
        groupingBy(Locale::getCountry, 
        counting())
    );
```
- summing[Int|Long|Double]() and averaging[Int|Long|Double] apply a provided function to the downstream elements
and produce the sum or average of the function's results 