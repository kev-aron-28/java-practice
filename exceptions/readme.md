Exceptions are Java's mechanism for handling abnormal situations during program execution.
An exception is an object that represents an error or unexpected condition.

# Hierarchy

```
Object
 └── Throwable
      ├── Error
      └── Exception
           └── RuntimeException
```

# Error
Represents serious JVM problems

```
OutOfMemoryError
StackOverflowError
InternalError
VirtualMachineError
```

Generally, you do not catch Errors because the application may no longer be in a safe state.

# Exception
Represents conditions that applications may reasonably handle.

```
IOException
SQLException
FileNotFoundException
```

# Checked excpetions
Any exception that extends Exception but not RuntimeException.
The compiler forces you to deal with them.

# Runtime exceptions
Any exception that extends RuntimeException.

```
NullPointerException
IllegalArgumentException
IllegalStateException
IndexOutOfBoundsException
NumberFormatException
```
The compiler does not force you to handle them.

# Checked vs Runtime

## Checked
Used for situations that can reasonably happen even when your code is correct.

## Runtime exceptions
Usually indicate programming mistakes.