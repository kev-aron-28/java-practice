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

This does not work