# INterview book

What is the role of the BufferedReader class, Why is it prefered over FileReader?
BufferedReader is a class that provides buffering capabilites for reading text from character input
streams. Enhances:
- Performance by reading larger blocks of data at once into a buffer
- Reducing the number of IO operations
- Usuful for reading large files

- What is NIO and how does it differ from the standard I/O java?
NIO (new input/output) allows threads to continue processing while waiting for I/O operations to complete, using selectors and channels.

Buffers: NIO uses buffers to hold data improving data manipulation and reducing the number of I/O operations.

File channels: NIO introduces file channels that support memory-mapped files, allowing for more efficient file access and manipulation

Provides:
- Scalability
- Performance

- When and why to use static keyword?
Static means "It belongs to the class and not to the object" So instead of each object creating its own copy, a static member is shared 
across all objects

1. Static variables
2. Static methods
3. Static blocks
4. Static classes

Why is it useful?
1. Saves memory (shared copy)
2. Utility/helper methods
3. Configurations or constants
4. One-time initialization

- How does the static keyword impact garbage collection in Java?
Static varibles are stored in the method area (part of the Heap) and they have the same lifespan as the class itself,
so they exists until the class is unloaded by the JVM

In long-running applications, static variables can lead to memory leaks if they hold references to objects that are no longer needed but cannot be 
garbage collected.

- Can static method be abstract?
No, static method cannot be abstract

- Can classes be static?
yes, inner classes can be static.
In java, only nested classes can be static, as they do not require an instance of the inner class to be created

- When not to use static keyworkd in Java and what be its disadvantages?
1. Memory usage: Static varibles reimain in memory until the class is unloaded and this can lead to higher memory usage

2. Tight coupling

3. cannot access instance members

4. Thread safety issues

- What is the output of the following code?

``` java

class Demo {
    static {
        sout("Static block")
    }

    {
        sout("Instance block");
    }

    Demo() {
        sout("Constructor");
    }
}


class Main {
    public static void main(String args[]) {
        sout("MAIN");
        new Demo();

        new Demo();
    }
}

First:

- Static block
- MAIN
- Instance block
- Constructor
- Instance block
- Constructor
```

- What would the output of the next?

``` java
class Parent {
    static void display() {
        sout("PARENT STATIC DISPLAY");
    }
}

class Child extends Parent {
    static void display() {
        sout("CHILD STATIC DISPLAY");
    }
}

public class Test {
    public static void main(String args[]) {
        Parent obj = new CHild();
        
        obj.display();
    }
}

PARENT STATIC DISPLAY
```

Static methods are not overriden; they are hidden. The method calls depends on the reference type.

---

- What are the differences between ArrayList and LinkedList in Java?
ArrayList:
Access time: O(1) for get and set operations
Insertion/Deletion: O(n) for add and remove
Better suited for frequent access and infrequent modifications