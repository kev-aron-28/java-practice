1. What happens if an exception is thrown during the serialization process?
If an exception is thrown during serialization, the process will fail and the partially serialized object may be left in a inconsistent
state

2. What happens if the serializable class contains a member which is not serializable?
Java will throw a java.io.NotSerializableException at runtime. To fix:
1. Make field serializable
2. Mark the field as transient
3. Use custom serialization

3. You have a parent class and a child class, both with satic fields. What happens to the static field?
Statci fields are not serialized, as they belong to the class not the instance. When a class is deserialized the static field will
hold the value it had at the time of deserialization, not the value that the object had during serialization

## Generics
129. What does generics mean?
Generics in java allow you to write classes, interfaces and methods  that can work with different  data types while providing typesafety and
reusability. With generics, type checks happen at compile timed'

130. What is the difference between List<?>, List<Object>, and List<? extends Object>

- List<?> a list that can hold elements of any type, but you cannot add elements to it except null
- List<Objet> a list that can hold elements of any type but expects elements to be type of Object or a subclass
- List<? exteds Object>: can hold elements of a type that is subclass of Object, indicates Upper bound

131. What is the difference between covariance and contravariance in Java?
- Covariance (? extends T): allows a generic type to be a subtype of a specific type (read data)
- Contravariance (? super T): allows a generic type to be a supertype of a specific type (Write data)

132. Can you pass List<String> toa method which accepts List<Object>
No, in java you cannot directly pass a List<String> 

133. What is PECS principe in Generics?
The PECS principe is a classic Java Generics question,
PECS = Producer extends, Consumer Super

? extends T, producer
- Use when the collection is a producer of data
- You can safely read items as type T
- But you cannot add new elements

? super T, consumer
- Use when the collection is a consumer of data
- You can safely add objects of type T or its upperclasses
- But when reading you only get Object

134. What is TypeErasue?
Type erasure is the process by which the Java compiler removes all the information related to type parameters
and type arguments within generic type declaration, during compilations

135. What is a generic tpye inference?
Generic type inference allows the compiler to automatically determine the type arguments for a generic method
or constructor based on the context in whichit is used

136. Suppose you need to overload a method to handle both a List<Ineger> and a List<Double>, can you overloadmethods with 
these types?
In Java, method overloading with generic types is restrictued due to type erasue, which removes generic type information
at runtime

Both are erased to List<Object> during compilation leading to conflict


137. Why cant you create an array of generic types?
Creating an array of generic types is prohibited because of type erasure

138. I have a generic method called merge that merges two collections into one?

``` java
public static <T> Collection<T> merge(Collection<T> a, Collection<T> b) {}

List<Number> numbers = merge(new ArrayList<Integer>(), new ArrayList<Double>());
```

This does not work, to fix this

``` java
public static <T extends Number> Collection<T> merge(Collection<? extends T> a,)
```

# Java memory managment

140. How are Strings represented in Memory?
In Java, strings are represented as objects of the String class. Each string is stored in the heap memory and internally
is backed by a char[] which holds the actual string data. Java strings are immutable, meaning once created, their content cannot be changed

The immutability is achieved using a special memory area called string pool. The string pool allows for memory optimization by storing string literals only once
When a new string is created, the JVM checks if the string already exists in the pool:
- If it does it reuses the reference to the existing string

This approach not only saves memory but also helps with performance, as it avoids unnecessary string duplications

141. Is it possible to resurrect an Object that became eligible for garbage collection?
Yes it is possible, using the finalize() method

142. What are the default garbage collectors in different Java versions?
Java uses several garbage collectors, with defaults that has evolved across versions:
Java 7 and earlier:  Parallel GC
Java 8: Parallel GC
Java 9-17: G1 GC
Java 18+: G1 GC

143. What are Strong, Weak, Soft and Phanthom References and their Role in GC?
Strong: Is the default type of reference in Java. Any object with a strong reference cannot be garbage collected as long
as it reference exists

Weak: A weak refernece does not prevent an object from being garbage collected. 

Soft: Similar to weak references but with one key diff: objects with only soft references are not immediatly GCd when they become
unreachable, only when the JVM is running low on memory

Phantom references: They are the weakest typeof reference and are used to determine when an object has been definitively removed from memory


145. What arethe different types of Garbage Collectors in Java?
- Serial Garbage Collector: A simple, single-threaded GC that pauses all application threads during garbage collection.
- Parallel Garbage Collector: Uses multiple threads to speed up garbage collection.
- Concurrent mark sweep: Reduces garbage collection pauses by doing most of the work concurrently

146. What perfomance optimizations have you done in your Java project?
1. Profiled and analyzed performance
2. Optimized database queries
2. Implement caching
3. Tuned garbage collection
4. Improved concurrency
5. enhaced code efficiency 
6. load testing and scaling
7. asynchrounous processing
8. Optimized network commncation
9. Used profiling and monitoring

147. What coding standars do you follow as a Java developer?
1. Consistent style
2. Design patterns
3. Modular architecture
4. Exception handling
5. Resource managment
6. Testing
7. Performance
8. Documentation
9. Code reviews
10. CI/CD

148. What are different areas in Java memory?
- Heap area: 
    - Stores: Objects and class instances
    - Managed by: Garbage collector
    - Subdivided into:
        - Young generation
        - Old generation
    - Most memory related issues
    - OutOfMemoryError occur here
- Stack area
    - Stores: Method call frames, local variables, references
    - One stack per thread
    - Memory is allocated deallocated in LIFO order
    - Error: StackOverFlowerror if recursion
- Method area:
    - Stores: Class metadata, static variables, method info
    - In java 8+ moved to native memory
    - Grows dinamically
- Program count
    - Stores: Address of the current JVM instruction for eac thread
    - One per thread
    - Used to resume execution correctly after a method call or jump
- Native method stack
    - Supports: Native method execution
    - Uses native libraries
    - Separate from stacks

149. How is memory allocated in this areas?
    - Heap memory: Managed by the JVM and allocated dynamically during runtime for objects
    - Stack memory: Allocated for each thread when methods invoked
    - Method area: Pre-allocated during JVM initliazation and grows as more classes are loaded

150. What are memory leaks in java and how to prevent them?
A memory leak in Java occurs when objects are no longer needed by the application but are still referenced,
preventing the garbage collector from reclamiming their memory

- Ensure that objects are deferenced
- Use appropiate collection classes that allow objects to be garbage collected 
- Be cautions with listeners, callbacks, and static fields

151. What is metaspace in Java and how does it differ from PermGen?
Metaspace is the memory area where calss metadat is stored in Java 8 and later versions
Unlike PermGen, which had a fixed maximum size, Metaspace dynamically resizes based on application needs=

152. How can we monitor Garbage colelction activities in Java?
1. Using JVM options
2. JVisualVM
3. Jconsole
4. Garbage collection logs + GCViewer
5. FR (Java Flight recorder) - Java 11 +
6. 