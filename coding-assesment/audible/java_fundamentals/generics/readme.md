# Generics

Generics allow you to parameterize a class, interface, or method with a type

Without generics:

``` java
List users = new ArrayList();

users.add("Kevin");
users.add(123);

String name = (String) users.get(0);
```

Problems:
- no compile-time type safety
- explicit casts
- possible ClassCastException

Now with generics:

``` java
List<String> users = new ArrayList<>();

users.add("Kevin");
// users.add(123); // compile error

String name = users.get(0);
```

The compiler now knows that only a string, so generics primarly provide compile-time type safety and reusable type
abstractions

## Generic class

``` java
class Box<T> {

    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
```

T is a type parameter and String is a type argument

## Generic methods
Generic dont have to belong to a class

``` java
public static <T> T identity(T value) {
    return value;
}
```

Why is the <T> before the return type? Because java needs to distinguish the typeparamter declaration from the return type so <T> T identity (T value)

means Delcare T, return T accept T without the first <T> Java would interpret T as some already-existing type

## Multiple type paramters

``` java
class Pair<K, V> {

    private K key;
    private V value;
}
```

Common conventions
T: type
E: Element
K: Key
V: Value
N: Number
R: Result/ Return

## Generics and inheritance

Suppose:

``` java
class Animal {
}

class Dog extends Animal {
}
```

with this one might think as Dog extends Animal then List<Dog> extends List<Animal> but that is not true,
java generics are invariant
So:

``` java
List<Dog> dogs = new ArrayList<>();

// List<Animal> animals = dogs; // compile error
```

``` java
List<Dog> dogs = new ArrayList<>();

List<Animal> animals = dogs;

animals.add(new Cat());
```

but now you violated the original type guarantee, this is why Java needs wildcards

## Wildcards
A wildcard is ?, List<?> list, and means a List of some unknown type


### List<?>
``` java
void print(List<?> list) {
    for (Object value : list) {
        System.out.println(value);
    }
}
```

Why can we read values as Object? because every Java reference type ultimately extends Object, but you cannot add
becuase the list could be interger and adding a string would be invalid, the only safe value you can add is null

## Upper bounded wildcard

``` java
List<? extends Animal>
```

This means a list of some unknown type that extends Animal: Dog, Cat, Animal but not Object

You cannot add to ? extends, you dont know the actual type Could be any Dog, Cat, Animal, therefore Java prevents

``` java
animals.add(new Dog()); // compile error
```

But you can read from, thas save, whatever the actual type is, 
therefore ? extends Animal
- read Animal
- write: effectively no values

## Lower bounded wildcard

``` java
List<? super Dog>
```

A list of some unknown type that is Doc or superclass of Dog, potentially of List<Dog>, List<Animal>, List<Object>

You can safely add a Dog

``` java
void addDogs(List<? super Dog> dogs) {
    dogs.add(new Dog());
}
```

Therefore ? super Dog
- write : Dog
- read : object

## The famous PECS rule

PECS = Producer Extends, Consumer Super