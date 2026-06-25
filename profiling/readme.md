# Profiling

Profiling means measuring what your application is actually doing while it runs.

You typically profile:

- CPU usage
- Memory usage
- Object allocations
- Garbage Collection
- Threads
- Locks and contention
- I/O activity

# Basic JVM monitoring

## jps
List running Java procceses
``` bash
jps -l
```

## jstat
GC statistics

``` bash
jstat -gc 12345
```

Shows:
- Eden usage
- Survivor usage
- Old Gen usage
- GC counts
- GC time


## jinfo
Displays JVM flags

``` bash
jinfo 12345
```

## jstack
capture thread dump

``` bash
jstack 12345 > dump.txt
```

Shows every thread

# Memory analysis
Memory issues are often harder than CPU issues

## Heap dump
``` bash
jcmd 12345 GC.heap_dump heap.hprof

jmap -dump:live,file=heap.hprof 12345
```