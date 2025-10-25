# Collectors

A Collector in Java is a reduction strategy used by the Stream.collect() method.

“How do I gather or accumulate elements from a stream into a single result (like a List, Map, average, sum, etc.)?

# Syntax
```
Stream<T> stream = ...
R result = stream.collect(Collector<T, A, R> collector);
```

Where:
T = input type (stream element)
A = intermediate accumulation type (mutable container)
R = final result type

You almost never implement your own Collector.
You use the built-in ones from **java.util.stream.Collectors**.


# Common built in collectors
```
List<String> names = stream.collect(Collectors.toList());
Set<String> unique = stream.collect(Collectors.toSet());
Map<Integer, String> map = stream.collect(Collectors.toMap(
    String::length,    // key mapper
    Function.identity() // value mapper
));
```

Tip: toList() and toSet() don’t guarantee mutability or order;
if you need specifics, use Collectors.toCollection() with a constructor reference:

```
List<String> list = stream.collect(Collectors.toCollection(ArrayList::new));
```

# Joining Strings

String joined = stream.collect(Collectors.joining(", "));
Collectors.joining(delimiter, prefix, suffix)
Collectors.joining(", ", "[", "]");


# Counting and summarazing
long count = stream.collect(Collectors.counting());
int sum = stream.collect(Collectors.summingInt(String::length));
double avg = stream.collect(Collectors.averagingDouble(String::length));


# Grouping
- Basic
Map<Integer, List<String>> bylen = stream.collect(Collectors.groupingBy(String::length));

- Grouping + downstream collector
Map<Integer, Long> countByLength =
    stream.collect(Collectors.groupingBy(
        String::length,
        Collectors.counting()
    ));

- Nested grouping
Map<Integer, Map<Character, List<String>>> nested =
    stream.collect(Collectors.groupingBy(
        String::length,
        Collectors.groupingBy(s -> s.charAt(0))
    ));


# Partitioning
Map<Boolean, List<Integer>> partitioned =
    numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));


# MApping during collection
Map<Integer, Set<Character>> firstLettersByLength =
    stream.collect(Collectors.groupingBy(
        String::length,
        Collectors.mapping(s -> s.charAt(0), Collectors.toSet())
    ));
