# Review

## Locks

What is a lock?
At the most fundamental level, a lock provides mutual exclusion:

At most one thread may enter a protected critical seciton at a time, the provide also for
memory visbility and ordering guarantees

Lock:
- Mutual exclusion
- Memory ordering / visibility

## syncrhonized is java's built-in locking mechanism
Java has intrinsic locking through monitors

``` java
synchronized (lock) {

}
```

the object used in 

``` java
synchronized (lock)
```
determines which monitor is beign acquired

## WHy synchronized (this) can be dangerous
You can write:

``` java
synchronized(this) {
    
}
```

A common pattern is:

``` java
private final Object lock = new Object();
```

## ReentrantLock

Java's explicit locking API is:

``` java
java.util.concurrent.locks.Lock
```

The most common implementation is ReentrantLock

``` java
private final Lock lock = new ReentrantLock();

public void increment() {
    lock.lock();

    try {
        counter++;
    } finally {
        lock.unlock();
    }
}
```

Its called Reentrant because it can acquire that same lock again

## Why do we need ReentrantLock if synchronized exists?
For basic mutual exclusion:

``` java
synchronized
```

is ofthen enough, ReentrantLock exists because it provides additional capabilities

important ones includes:

```
tryLock()
lockInterruptibly()
fairness
multiple Conditions
explicit lock management
```

## Fair vs non-fair locks
ReentrantLock can be created as:

``` java
new ReentrantLock(true);
```

or 

``` java
new ReentrantLock(false)
```

The first requests fairness, a fair lock attempts to grant access in a more orderly fashion based on waiting threads
Non-fair locks allows more opportunistic acquisition

## Condition

``` java
Condition condition = new lock.newCondition();
```

``` java
lock.lock();

try {
    while (!available) {
        condition.await();
    }

    useResource();

} finally {
    lock.unlock();
}
```

One advantage of condition is thata single lock can have multiple condition queues

``` java
Condition notEmpty = lock.newCondition();
Condition notFull = lock.newCondition();
```

## ReadWriteLock
now suppose your application has 10,000 reads and 10 writes, with a ReentrantLock, is unnecessarly restrictive if
reads, ReadWriteLock separates: read lock and write lock

``` java
private final ReadWriteLock rwLock =
        new ReentrantReadWriteLock();
rwLock.readLock().lock();

try {
    return data;
} finally {
    rwLock.readLock().unlock();
}

rwLock.writeLock().lock();

try {
    data = newData;
} finally {
    rwLock.writeLock().unlock();
}
```

## StampedLock
Java also provides StampedLock it offers: read lock, write lock, optimistic read

# Data structure

- ConcurrentHashMap
Allows concurrent access without blocking the name

- ConcurrentLinkedQueue, Concurrent queue non blocking

- ConcurrentLinkedDequeue, 
- BlockingQueue
- ArrayBlockingQueue
- LinkedBlockingQueue
- PriorityBlockingQueue
- DelayQueue
- SynchronousQueue
- CopyOnWriteArrayList
- CopyOnWriteArraySet
- AtomicInteger
- AtomicLong
- AtomicBoolean
- AtomicReference
- LongAdder
- LongAccumulator
- Semaphore
- CountDownLatch
- CyclicBarrier
- Exchanger<T>
- ThreadLocal<T>
- ReentrantLock
- ReentrantReadWriteLock
- StampedLock
- ExecutorService
- ForkJoinPool
- CompletableFuture