# Generics

Them allow you define classes, interface, and methods with type parameters providing:
- Compile time type safety
- no need for explicit casting
- code reusability
- clearer and stronger APIDs

# Example 
```java
class Box<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}

Box<String> box = new Box<>();
box.set("Java");

Box<Integer> box2 = new Box<>();
box2.set(42);
```


## Common type naming

| Letter | Meaning |
| ------ | ------- |
| T      | Type    |
| E      | Element |
| K      | Key     |
| V      | Value   |
| N      | Number  |

```java
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
}

Pair<Integer, String> pair = new Pair<>(1, "Kevin");
```

## Generics methods
```
class Utils {

    public static <T> void print(T value) {
        System.out.println(value);
    }
}
Utils.print("Hello");
Utils.print(100);
```
<T> T print(T value)
This line has three different parts involving T, and each one has a different role.

## What is <T> before the return type?
This declares a generic type parameter for the method.

```java
public static <T> T identity(T value) {
    return value;
}

```


## Bounded type parameters

### Upperbound types
```java
class Calculator<T extends Number> {

    public double doubleValue(T number) {
        return number.doubleValue() * 2;
    }
}

Calculator<Integer> c = new Calculator<>();
Calculator<Double> c2 = new Calculator<>();
// Calculator<String> would cause an error
```

### Unbounded wildcard
```
List<?>
```
A list of some unknown type, You don’t know what the type is and you don’t care.

Example:
```java
void print(List<?> list) {
    for (Object o : list) {
        System.out.println(o);
    }
}
```

Rules: 
- You can READ elements as Object
- You can NOT ADD anything (except null)


```
double sum(List<? extends Number> list) {
    double total = 0;
    for (Number n : list) {
        total += n.doubleValue();
    }
    return total;
}

sum(List.of(1, 2, 3));
sum(List.of(1.5, 2.5));
```

### LowerBounded wildcard

```java
List<? super Integer>
```
A list of Integer or any superclass of Integer

This includes:
- List<Integer>
- List<Number>
- List<Object>

Rules:
- You can add an interger
- You can only read as objet

| Wildcard        | Can Read | Can Write | Use When                  |
| --------------- | -------- | --------- | ------------------------- |
| `<?>`           | `Object` | no        | You don’t care about type |
| `<? extends T>` | `T`      | no        | You read values           |
| `<? super T>`   | `Object` | `T`       | You write values          |


# WHy not just use <T> everywhere?

Because <T> means “one specific type”, while wildcards mean “many possible types”.
Using <T> everywhere would make APIs too strict and less usable.

# What <T> really means?
<T> void process(List<T> list)
There is ONE concrete type T, and the entire method works with that exact type

T is locked to a single type per method call.

Example:

```java
<T> void print(List<T> list) { }
print(List.of("A", "B"));      // T = String
print(List.of(1, 2, 3));      // T = Integer
print(List.of("A", 1)); // mixed types not allowed anyway
```

## The problem with APis

I want a method that accepts any list of numbers for instance
Using is <T> is to strict

<T extends Number> void sum(List<T> list) { }

So if you try this:

```
List<Integer> ints = List.of(1, 2, 3);
List<Double> doubles = List.of(1.5, 2.5);

sum(ints);     // good
sum(doubles);  // good
// But when you try this

void process(List<Number> numbers) {
    sum(numbers); // COMPILATION ERROR
}
```

- sum wants a specific T
- List<Number> does not guarantee that T is preserved

Same with a wildcard;

```
void sum(List<? extends Number> list) { }
sum(List.of(1, 2, 3));
sum(List.of(1.5, 2.5));
sum(new ArrayList<Number>());
```

## Key concept
| Feature    | `<T>`             | `?`                 |
| ---------- | ----------------- | ------------------- |
| Represents | One exact type    | Any compatible type |
| Scope      | Locked per call   | Flexible            |
| Best for   | Logic consistency | API boundaries      |

# Writing vs Reading

Using wildcard:
void add(List<? extends Number> list) {
    list.add(1); // ❌
}

Using <T>:
<T extends Number> void add(List<T> list, T value) {
    list.add(value);
}


## Why generics aalone are not enough?

``` java
public static double sum(List<Number> numbers) {
    double sum = 0;
    for (Number n : numbers) {
        sum += n.doubleValue();
    }
    return sum;
}

List<Integer> ints = List.of(1, 2, 3);
sum(ints); // OES NOT COMPILE
```

** List<Integer> ≠ List<Number> **


## Real world fix ? extends (READ ONLY use case)
You don’t care which Number subtype it is.
You only want to read numbers.

``` java
public static double sum(List<? extends Number> numbers) {
    double sum = 0;
    for (Number n : numbers) {
        sum += n.doubleValue();
    }
    return sum;
}

sum(List.of(1, 2, 3));        // Integer
sum(List.of(1.2, 2.5));      // Double
sum(List.of(1L, 2L));        // Long

```

** WHy you cant add? **
Because:
Could be List<Double>
Adding an Integer would break it


## Real-world fix: ? super (WRITE-ONLY use case)
Now opposite scenario.
You want to put values into a collection.

```
public static void addIntegers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
}

List<Integer> a = new ArrayList<>();
List<Number> b = new ArrayList<>();
List<Object> c = new ArrayList<>();

addIntegers(a);
addIntegers(b);
addIntegers(c);
```

Why super?
Because all of these can hold Integer safely.


So essentially when you use:
- ? extends, you are saying ? value must also extend that type and will accept other classes that also extend it (upper-bounded)
- ? super, you are saying ? value will only accept the classes that are up the class you are writting in there, like ? super Integer, Only object and Number will be valid as they're up in the heirarchy


## Wildcards and subtyping

```
class A { /* ... */ }
class B extends A { /* ... */ }

B b = new B();
A a = b;
```
This does not work with generic types

```
List<B> lb = new ArrayList<>();
List<A> la = lb;   // compile-time error
```

Although Integer is a subtype of Number, List<Integer> is not a subtype of List<Number> and, in fact, these two types are not related. The common parent of List<Number> and List<Integer> is List<?>.

In order to create a relationship between these classes so that the code can access Number's methods through List<Integer>'s elements, use an upper bounded wildcard:

```
List<? extends Integer> intList = new ArrayList<>();
// This is OK because List<? extends Integer> is a subtype of List<? extends Number>
List<? extends Number>  numList = intList;  
```

## Wildcard capture and helper methods
In some cases, the compiler infers the type of a wildcard. For example, a list may be defined as List<?> but, when evaluating an expression, the compiler infers a particular type from the code. This scenario is known as wildcard capture.

## Guide lines
An "in" variable is defined with an upper bounded wildcard, using the extends keyword.
An "out" variable is defined with a lower bounded wildcard, using the super keyword.
