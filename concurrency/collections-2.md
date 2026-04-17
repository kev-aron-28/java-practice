# ConcurrentHashMap

Thread safe map using:
    - CAS (compare and swap)
    - bucket level locking only when needed
    - No global lock


Key operations:

1. get(key)
    - Non-blocking
    - reads volatile fields
    - You may see slightly stale data but never corrupted data

2. put(key, value)
    - If empty, CAS insert
    - if not:
        - lock only that bucket
        - update or append node

3. compute/merge
    - Entire operation is atomic
    - Locks only relevant bucket

``` java
map.put(k, map.get(k) + 1);

map.merge(k, 1, Integer::sum);
```

    - Best for high read/write concurrency
    - Shared mutable state


# CopyOnWriteArrayList
Every write copies the entire array when reading no locking at all

Key operations:
- get(index)
Reads from immutable snapshot
No lock, no contention

- add(element)
This:
1. Lock briefly
2. Copy entire array
3. Add new element
4. Replace reference

This shines when 95% reads and 5% are writes

# ConcurrentLinkedQueue
Lock-free queue using CAS on head/tail pointers

- offer(element)
- poll()
- peek()

This is non-blocking and trhread-safe

# BlockingQueue
Coordinates threads using locks and conditions

- put(element): blocks if full
- take(): blocks if empty
- offer(element, timeout)

Treade-off is thread waiting and less throughput

# ConcurrentSkipListMap
Sorted map using a skip list, lock-free reads, fine-grained updates

Operations:

- put(key, value)
    - Inserts in sorted order
    - Uses CAS to link nodes across levels
- get(key)
    - Traverses levels top-down
    - No locking 

This works for ordered concurrent data, leaderboads, time-series

# DelayQueue
Elements only become available after delay expires

Operations:

- put(delayed):
    - inserts into priority queue

- take()
    - Blocks until head element dealy == 0


# SynchronousQueue

No storage at all

- put()
    - Blocks until another tread calls take
    
- take()
    - Blocks until another thread calls put()


# Concurrency primitives

## AtomicInteger

At its core:

``` java
public class AtomicInteger {
    private volatile int value;
}
```

- It has volatile so it has visibility across threads
- CAS: atomic updates

Operations

- get()

Reads the volatile value, so it always sees the latests visible value

- set(value)
Writes to volatile value, so guarantees inmeedialty visible to all threads

- incrementAndGet()
- compareAndSet(expected, update)
- getAndIncrement()
- updateAndGet()

Where this breaks down? When contention is high

The problem is CAS contention:
- Many threads retry
- CPU wasted spinning

Some symptoms:
- High CPU usage
- Poor scaling

## Why LongAdder exists
Because of this problem

AtomicInteger: single memory location is a bottleneck
LongAdder: multiple cells, less contention