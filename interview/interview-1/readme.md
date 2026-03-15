# Interview

1. Explain the difference between ==, equals() and the importance of hashCode?
==
- For primitives, compares actual val;ues
- For objects, compares memory references (if both point to the same object in memory)

.equals()
- Used for logical equality
- default implementation behaves like == reference comparison
- Classes like STring or integer override to compare content

hashCode()
Returns an integer hash representation of the object, and its used manly in collections like HashMap, HashSet, HashTable

Why must you override hashCode when overriding equals?
Because of hash contract used in collections like HashMap

2. Difference between abstract class?
An abstract class can contian abstract methods, concrete methods and can have instance variables(state), constructors and access modifiers
and its used when you want to share common state and behavior among related classes
An Interface defines a contract of behavior:
- Methods are public by default, variables are public static final, cannot have instance, cannot have constructors a
- cannot have a state
- cannot have constructors
- after java 8 default methods, static methods

What advantage does an abstract class still have?
Even though interfaces have default methods, abstract classes still provide:
- State
- Contructors
- Access control
- Template pattern

A class:
- Can implement multiple interfaces
- Can extend only one class

INTERFACE: When defining capability / role
ABSTRACT CLASS: when modeling a base type with shared implementation

3. What is the difference between overloading overriding?
Overriding happens when a subclass provides a new implementation of a method defined in a superclass or a interface and must be:
- same method name
- same parameter types
- same return type or covariant return type
- cannot reduce visibility
It is used to achieve runtime polymorphism

Overloading happens when multiple methods in the same class have
- same name
- different parameter list
- and its resolved at compile time

``` java
class Parent {
    static void hello() {
        System.out.println("Parent");
    }

    void speak() {
        System.out.println("Parent speak");
    }
}

class Child extends Parent {
    static void hello() {
        System.out.println("Child");
    }

    void speak() {
        System.out.println("Child speak");
    }
}

Parent p = new Child();
p.hello();
p.speak();

```

This prints 
Parent
Child speak

Static methods are resolved at compile time and are hidden, not overridden. Instance methods participate in dynamic dispatch and are resolved at runtime based on the actual object type

``` java
class A {
    void method() {
        System.out.println("A");
    }

    A get() {
        return this;
    }
}

class B extends A {
    @Override
    void method() {
        System.out.println("B");
    }

    @Override
    B get() {
        return this;
    }
}
```
Why is this B get() valid?
Because Java allows covariant return types in method overriding.

4. Collections internals
- How does HashMap work?
A HashMap is backed by an array of buckets.

- What happents when two keys have the same hash?
That’s a collision. When collision happens:
Java checks .equals() to see if the key already exists
If yes → value is replaced
If no → it adds a new node in that bucket

- What changed in HashMap after Java 8?
Before Java 8:
- Collisions were handled using a linked list
- Worst case lookup: O(n)

After java 8:
there is a Red-Black tree  so look up becomes O(log n) instead of n

- what is the time complexity of get() and when does it degrade?
Average case: O(1)
Worst case (pre Java 8): O(n) (all elements in one bucket)
Worst case (Java 8+): O(log n) due to treeification

5. Concurrency
- Difference between synchronized and volatile
syncrhonized:  Provides mutual exclusion (only one thread executes the block at a time), and visibility guarantees under the hood it acquires a lock
and solves rece conditions
volatile provides visibility guarantee only, but does not provide mutual exclusion so when a variable is volatile:
writes go directly to main memory
reads always come from main memory
threads dont cache it locally

- What problem does volatile solves?
It solves the visibility problem
Without volatile one thread may:
    -Cache the variable in CPU cache
    -Never see updates from another threads
volatile ensures changes are visible across threads.

``` java
class Example {
    private volatile boolean initialized = false;

    public void init() {
        if (!initialized) {
            synchronized (this) {
                if (!initialized) {
                    // do initialization
                    initialized = true;
                }
            }
        }
    }
}
```

1- First check (no lock) → fast path
2- Only if not initialized → enter synchronized block
3️- Second check → ensures only one thread initializes

Why volatie is required?
1. Visibility without volatile one thread may set: initialized true and other threads might still see false
due to CPU caching, so they might unnecessarily enter the synchronized block

double-checked locking safe

6. JVM Memory
    - Stack memory: Is used for method execution, each thread has its own stack, Thread specific
    - heap memory is where object live, it is shared accross all threads managed by garbage collector

7. 