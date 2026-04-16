# What is ClassNotSupportedException?
CloneNotSupportedException is a checked exception that is thrown when an objects clone method is called but the object does not implement 
the Clonable interface

# What happens when the main function is run?
1. Class loading
2. Bytecode verification
3. Memory allocation
4. Execution
5. Execution engine

# What really java works under the hood?
1. You write your code
2. Compile to bytecode 
3. JVM loads and verifies the code
4. JVM starts executing the code 
    1. Interpreter
    2. JIT compiler

# What is JIT compiler?
Just in Time, so the JVM watches which parts of your code run a lot and 
compiles them to machine code
Types:
- C1 (client): fast startup, fewer optimization
- C2 (server): slower compiler, highly optimization
- Graal: Newer, Java-based smarter and supports native images

Modern java uses tiered compilation:
- Starts with interpreter
- Then C1
- Then C2 OR Graal

5. Native code executes on the CPU 

# You have two classes, ClassA and ClassB each dependent on the other. Both classes' constructors require the other class as a parameter. How would you resolve this circular dependency?
# This can be solved by using dependency injection, or by refactoring like using setter injection


# How to resolve the diamond problem?
