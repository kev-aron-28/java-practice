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


# “Heap is shared, stack is not” → WHY
This is not a rule, it’s a design necessity.
Multiple threads must:
- Work independently (local variables, method calls)
- Share objects (state, data structures)

So the JVM separates execution state from shared data.

### Stack means execution context (thread-local by design)

Each thread has:
- ITs own call stack
- own stack frames
- own local variables

1. Method calls must be isolated
2. No synchronization needed

### Heap → Shared object memory
Objects exist to be shared:

1. Reason 1: Objects must be accessible across threads
2. Reason 2: Object lifetime outlives method calls


## Common jvm flags

-Xms<size>	Initial heap size
-Xmx<size>	Maximum heap size
-XX:NewRatio=<ratio>	Ratio between young and old generation
-XX:SurvivorRatio=<ratio>	Size ratio of the survivor spaces to Eden
-XX:+UseG1GC	Use G1 Garbage Collector
-XX:+PrintGCDetails	Prints detailed GC logs
-XX:+HeapDumpOnOutOfMemoryError	Dumps heap when OOM error occurs


``` shell
java -XX:+UseG1GC -XX:MaxGCPauseMillis=100 -XX:InitiatingHeapOccupancyPercent=45 -jar myapp.jar
```

MaxGCPauseMillis: Target pause time for GC.
InitiatingHeapOccupancyPercent: Percentage that triggers a GC cycle.


### Monitoring with Jvisualvm and Jconsole
To visualize memory usage:

JVisualVM: Perfect for monitoring heap size, GC activity, and thread states.
JConsole: Lightweight, great for quick peeks at memory and thread status.

# Practical scenarios

## 1. High Latency Spikes
- Symptoms: Latency spikes during peak traffic.
- Solution: Use G1GC with -XX:MaxGCPauseMillis tuned to a reasonable target (e.g., 200 ms).

## 2. Out of Memory (OOM) Errors

