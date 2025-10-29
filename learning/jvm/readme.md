# JVM architecture
The JVM is the runtime environment that runs your compiled .class files (bytecode)

- class loading
- bytecode verification
- execution
- memory managment

+------------------------------------------+
|              JVM                         |
|------------------------------------------|
| Class Loader Subsystem                   |
| Runtime Data Areas                       |
|   - Method Area                          |
|   - Heap                                 |
|   - Stack                                |
|   - PC Register                          |
|   - Native Method Stack                  |
| Execution Engine                         |
|   - Interpreter                          |
|   - JIT Compiler                         |
|   - Garbage Collector                    |
| Native Interface (JNI)                   |
| Native Libraries                         |
+------------------------------------------+


# Classloader subsystem
Is responsible for loading bytecode into memory

There are 3 main built-in classloaders:
- Bootstrap classlaoder: loads core java libraries
- Extension classloader: Loads classes from Java_home/lib/ext
- Application clasloader: loads classes from classpath



# JVM memory areas 

- method area: stores class metadata, static variables, method code
- heap: stores objects and their instances variables
- stack: stores method frames
- program counter: holds address of next instruction
- native method stack: for native c\c++ method calls


# Heap vs memory stack

Stack stores local variables, references, call frames
Heap stores: objects and arrays

Stack size: small
Heap size: large

Access stack: LIFO
access heap: slower

Stack: managed by thread
Heap: managed by jvm shared by all threads


Stack lifetime: Exists till method returns
Heap lifetime: Exists un GC removes it 


# Garbage collector

Java automatically frees memory for unused objects via GC
phases:
1. Mark: Find all reachable objects
2. Sweep: Remove unreferenced objects
3. Compact: Move live objects together ro reduce fragmentation


# Generational heap layout
+-----------------------------------+
| Young Generation                  |
|   +----+----+----+                |
|   |Eden|S0  |S1  |                |
+-----------------------------------+
| Old Generation                    |
+-----------------------------------+
| Metaspace (JDK 8+)                |
+-----------------------------------+


Eden space – new objects are created here.
Survivor spaces (S0, S1) – objects that survive GC are moved here.
Old Generation (Tenured) – long-lived objects reside here.
Metaspace – stores class metadata (replaced PermGen since Java 8).


# garbage collectors
| GC                                     | Description                    | Best for                    |
| -------------------------------------- | ------------------------------ | --------------------------- |
| **Serial GC** (`-XX:+UseSerialGC`)     | Single-threaded, simple        | Small apps, client machines |
| **Parallel GC** (`-XX:+UseParallelGC`) | Multi-threaded                 | Throughput focus            |
| **CMS (Concurrent Mark Sweep)**        | Concurrent GC, low pause       | Deprecated, replaced by G1  |
| **G1 GC** (`-XX:+UseG1GC`)             | Region-based, balanced         | Default since Java 9        |
| **ZGC** (`-XX:+UseZGC`)                | Pause times <10ms              | Large heaps (multi-GB)      |
| **Shenandoah GC**                      | Similar to ZGC, RedHat version | Ultra-low latency           |





















































