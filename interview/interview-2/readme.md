# Interview 2

1. Which programming paradigms does Java support?
OOP, imperative programing, procedural programming, concurrent, functional, generic

2. Why is composition preferred over inheritance?
    1. Flexibility: You can change the behavior of a class at runtime
    2. Loose coupling: Classes are less dependent on the internal details of other classes
    3. Avoids inheritance issues: prevents things like the diamond problem and tight coupling
    4. ENcapsulation: Exposes only necesary parts
    5. Code reusability
    6. Easier maintenance

3. Is java pure object oriented language?
Java is not, due to:
    - Primitive types
    - Static members
    - Procedural constructs
    - Built-in operatos for primitves, not through objct methods

4. What is method hiding in Java?
When you declare two swtic methods with the same method name and signature in both superclass and subclass then they hide each other

5. Can we override the static method in Java?
No, you cannot override a static method in Java. static methods belong to the class rather than an instance of the class

6. Can we override a private or final method in Java?
private methods cannot be overriden because they are not accessible outside the class they are defined in.
They are implicitly final, meaning they are bound to the specific class.

Final methods: cannot be overriden because final keyword prevents a method from being changed in any subclass

7. Can we override constructor in Java?
No, you cannot override a contructor in Java. Overriding is only applicable to methods that are inherited.
Each class has its own contructor and this can be overloaded

8. Can we override a non-abstract method in abstract class?
Yes, you can override a non-abstract method. Abstract classes can have: abstract and concrete methods.
and a subclass can override abstract and concrete methods

9. Can we override a synchronized method with a non-synchronized method?
Yes, however doing so can lead to concurrency issues, and would remove the the thread safety guarantees provided by the
original method

10.Can we change the return type of method subclass while overloading?
You can but only from Java 5 onward. This is known as covariant method overriding and it was introduced in JDK 5
But the method must be a subclass of the original return

12. Can we make a class abstract without an abstract method?
Yes you can make an abstract class with only concrete methods

13. Can we make a class both final and abstract?
No, they are opposite concepts, final means no other subclass can extend this superclass while
an abstract class is meant to be extended

14. Can we overload or override the main method in java?
Yes, we can overload the main method in java but java will only look for the conventional one.
 We can only overload but not override because its an static method

15. What is @Override annotation in method overriding? What would happen if you omit this annotation?
Indicates that a method is intented to override a method in a superclass, if ommitedthe code will still compile but theres a 
tisk of creating a overloaded method instead of overriding it/

16. Why cant you use return in a constructor? What would happend if you tried?
A constructor cannot return a value, as they are meant to initialize an object. If you try, the compiler will throw an error.

17. What happens if you try to call super and this in the same contructor()?
Both must be the first stament in a constructor, therefore its illegal to call both in the same constructor.

18. What happens if you call super() in a constructor of a class but there is no explicit call to super?
- an explicit call to super() will call a no-args constructor
- no explicit call: java will insert automatically a call to the no args contructor

19 What is the result of comparing two objects with == if they are not the same instance but contain the same value?
== is a reference comparision, so if they have the same value but not the same reference in memory will return true

20. What will happen if you try to invoke a static method on a null object reference?
Static methods belong to the class not to an instance, so this will not result in a NullPointerException
as they can be called even without an instance of the class.

21. How does Java garbae collector work when an object becomes unreachable but still has references?
If your program can still reach an object, it stays.
If your program cannot reach it anymore, it can be deleted.

22. can we define an interface within a class?
Yes you can define a calss within a class, known as nested interface

23. Can we define a class within an interface?
yes, you can define a class within an interface within a class, such class is implicitly static and public

24. Can an interface have constructors?
No, interfaces cannot have constructors. They are meant to define abstract behavior.

25. How do you decide if an inner class is required?
1. Encaptulation: It tightly couples to the class with its outer class
2. Grouping: The class logically belongs to and is only used by the outer class
3. Access: It needs to access private memberof the outer class
4. Convenience: It simplifies code by keeping related classes together

26. Explain System.out.println
Is amethod in java used to print messages to console,
- out is type PrintStream

27. WHy is system.out considered thread-safe?
Because PrintStream, synchronizes all its methods

28. Can we execute a program without a main? 
- In traditional java: you need a main method 
- Java 11: Single file execution: introduced a feature where you can run .java file directly

29. What happens if you run a java program compiled with JDK 14 on a JDK 8 runtime?
If you run a Java program compiled with JDK 14 with a JDK 8 you will encounter a UnsupportedClassVersionError

30. Can you run a Java 8 compiled application on a JDK 11 runtime?
Yes, java provides backward compatibility

31. Can you use the throws keyword with run or main()?
Yes both can declare a throws, however
1. run() in threads: has no practical effect as the exceptions are not propagated back to the calling thread
2. main(): allows you to propagate checked exceptions to the JVM