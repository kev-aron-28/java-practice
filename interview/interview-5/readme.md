90. What would happen if you try to sort a list containing null elements using Collections.sort()?
NullPointerException will be thrown unless a custom Comparator is provided that can handle null variables

91. Can you sort a list of custom objects using Collections.sort() without providing a Comparator?
Yes, you can sort a list of custom objects without a comparator if the class implements the Comparable 

The comparable interface requires the class to implement the compareTo method, which defines the natural ordering of the objects

92. What is the difference between using Collections.sort() and Stream.sorted() in Java 8+?
Collections.sort() sorts the list in place, modifying the original list, Stream.sorted() is more suitable for use cases where yo want to keep
the original list unmodified

93. Explain Exception Hierarchy in Java?

```
Throwable
 ├── Error
 │    ├── OutOfMemoryError
 │    └── StackOverflowError
 │
 └── Exception
      ├── RuntimeException (Unchecked)
      │     ├── NullPointerException
      │     ├── IllegalArgumentException
      │     └── IndexOutOfBoundsException
      │
      └── Checked Exceptions
            ├── IOException
            ├── SQLException
            └── FileNotFoundException
```

Throwable: Everythin that can be thrown in Java extends and it has two main branches: Error and Exception

## Error
This are serious problems, you should not handle, this indicate JVM or system failure like: OutOfMemoryError, StackOverflowError, VirtualMachineError

## Exception
This is what we deal with and these are split into types: Checked and Unchecked

### Checked Exception
Must be handled at compile time like: IOException, SQLException, FileNotFoundException

### Unchecked Exceptions (runtime)
The root is RuntimeExcepetion, these are not checked at compile time, usually programing errors
Like: NullPointerException, ArrayIndexOutOfBoundsException, IllegalArgumentException this are our fault

94. Can you have a try block without a catch block?
Yes, but in that case you must have a finally block or a try with resoruces statement

95. What happens if you do not handle a checked exception? 
Checked exceptions must be either:
    - Caught using a try-catch block
    - Declared in the method signature using throws

if you do neither, the code will not compile. if you declare it but dont catch it, the exception will propagate to the caller
if the exception reaches the main method and is still unhandled, the program will terminate and print stack trace

96. Can a constructor throw an exception?
Yes, a constructor are just special methods used to create objects. 

97. Can we have multiple catch blocks for a single try block?
Yes, and the order of the blocks matters, the more specific exception should come first as they are evaluate from top to bottom

98. How to avoid NullPointerException?
Is an unchecked exception, that occurs when an application attempts to use null in a case where an object is required.
- Null checks
- Optional class
- Default values
- Avoiding null asignments

100. Can a finally block be skipped in any case?
No the finally block cannot be skipped under normal circumstances.

However, there are some escenarios where the finally block might not execute:
1. The JVM crashes
2. The thread executing the finally block is interrupted or killed
3. If the code enteres an infinite loop or an uncaught exception occurs in previous block

101. Can an error be caught in java? Should it be caught?
Yes, an Error can be caught as is a subclass of Throwable but is not recommended to catch an Error as this represent serious problems that the application should not catch

102. How does the try-with-resources work in java?
This was introduced in Java 7, is a form of the try statement that automatically closes resorces when the try block exists. Any object that implements java.lang.AutoClosable and Closable, can be used in a try-with-resources statement