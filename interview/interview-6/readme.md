103. If a method throws NullPointerException in the super class, can we override it with a method that throws RuntimeException?
Yes, RuntimeException is a superclass of NullPointerException

105. What is the output?

```java
try {
    return 1;
} finally {
    return 2;
}
```
The output is 2;
Even though try block has a return statement, the finally block will override it. 

106. What is the difference between NoClassDefFoundError and ClassNotFoundException?
- NoClassDefFoundError: Thrown if a class was present at compile time but is missing at runtime

- ClassNotFoundException: Thrown when attempting to load a class dynamically using Class.forNmae()

107. Logs say OutOfMemory how would you investigate?
1. Heap Dump analysis: with tools like MAT or VisualVM
2. Memory usage patterns
3. Code review
4. Increase Heap size

109. How do you create a custom exception in java?

To create a custom exception extend either 'Exception' (for checked) or 'RuntimeException' (unchecked)

110. What are Suppresed Exceptions?
Suppressed exceptions in Java are additional exceptions that occur while another exception is already being thrown, and instead of replacing the original one, 
they are attached (“suppressed”) to it.
This concept is tightly related to try-with-resources

113. What is an enum in Java and how do you declare an Enum in Java?
An enum in java is a special data type that enables for a variable to be set of predefined constants

``` java
public enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}
```

114. How does Java handle enum constansts internally?
Java handles each enum constant as a singleton instance of the enum class
When an enum is compiled, Java creates a class that extends java.lang.Enum. Each constant such as Color.RED is a static 
final instance of this class

115. What are some use cases where using enum is preferable over static final constants?
1. Type safety
2. Grouping
3. Custom Fields an methods
4. Utility methods
6. Serialization safety