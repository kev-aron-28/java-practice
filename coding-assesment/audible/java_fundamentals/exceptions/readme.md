# Exceptions
An exception represents an abnormal condition during program execution that dispruts the normal flow of instructions

An exception is an object, not merely an error message

## Hierarchy
```
Throwable
├── Error
│   ├── OutOfMemoryError
│   ├── StackOverflowError
│   ├── NoClassDefFoundError
│   └── ...
│
└── Exception
    ├── RuntimeException
    │   ├── NullPointerException
    │   ├── IllegalArgumentException
    │   ├── IllegalStateException
    │   ├── IndexOutOfBoundsException
    │   └── ...
    │
    ├── IOException
    ├── SQLException
    ├── InterruptedException
    └── ...
```

Everything starts with Throwable, as this is the root type of everything that can be thrown with "throw" and supports exception chaining 

## Error vs Exception
This is one of the first interview questions
Error: generally represents serious problems associated with the JVM or runtime environment, you generally dont recover from these

Exception: represents conditions that an application may reasonably handle

- Checked vs unchecked exceptions
The distinction is based on whether the exception is a subclass of RuntimeException

: RuntimeException unchecked
: other exceptions: checked

Checked exception are special because the compiler forces you to deal with them, you must either catch it or declare it

## Exception propagation

``` java
public void controller() {
    service();
}

public void service() {
    repository();
}

public void repository() {
    throw new RuntimeException("Database failure");
}
```

It propagates upward, the runtime searches the call stack for a matching handler and if nobody catches the thread terminates

## Suppresed exceptions
Java keeps the exception from the body as the primary exception, and the exception from close() becomes a suppressed exception.

## What happens when there is no handler?
Eventually the exception reaches the thread's top-level execution boundary, if nothing handles it, the JVM uncaught-exception mechanism handles it

