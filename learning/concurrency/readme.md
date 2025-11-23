# Concurrency 
Means dealing with multipple tasks at once

In java means:
- Running multiple threads that share resources
- Efficiently utilizing CPU cores
- Managing async operations


# The thread model
You can create a threa in java in several ways:

1. Extend thread

class MyThread extends Thread {
    public void run() {
        System.out.println("This is a thread")
    }
}

new MyThread().start();

2. Implement runnable
Runnable task = () -> {};

Thread t = new Thread(task);
t.start();

3. Callable
Callable<Integer> task = () -> 32;


# Thread lifecycle

New -> Runnable -> Running -> Blocked/Waiting -> Terminated

# Synchonization and shared memory

Example of race condition:
class Counter {
    int count = 0;

    void increment() {
        count++;
    }
}

Counter counter = new Counter();

Runnable task = () -> {
    for (int i = 0; i < 1000; i++) counter.increment();
};

Thread t1 = new Thread(task);
Thread t2 = new Thread(task);

t1.start();
t2.start();
t1.join();
t2.join();

System.out.println(counter.count); // Might be < 2000!

Why? count++ is not atomic — it’s actually:
Read count
Add 1
Write back
Two threads can read the same value before writing → lost updates.

# Synchonization
Synchronization ensures that only one thread at a time executes a piece of code that accesses shared memory.
synchronized keyword

Can synchronize methods:

class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}

Or blocks:

void increment() {
    synchronized(this) {
        count++;
    }
}

volatile keyword

Ensures visibility across threads: when one thread updates a variable, others see it immediately.
Does not guarantee atomicity.

volatile boolean running = true;

while (running) {
    // do something
}

running = false;


# Locks

More flexible than synchronized

Lock lock = new ReentrantLock();

lock.lock();
try {
    count++;
} finally {
    lock.unlock();
}

Advantages over synchronized:
Can attempt tryLock() without blocking
Can implement fairness
Can have multiple conditions (lock.newCondition())

# All the ways to define threads in java

1. Extend thread class
class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("Running in a thread");
    }
}

public class Main {
    public static void main(String[] args) {
        new MyThread().start();
    }
}


2. Implement Runnable
class MyTask implements Runnable {
    @Override
    public void run() {
        System.out.println("Task running");
    }
}

public class Main {
    public static void main(String[] args) {
        Thread t = new Thread(new MyTask());
        t.start();
    }
}

3. Implement Callable
import java.util.concurrent.Callable;

class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() {
        return 42;
    }
}


4. With lambda
Thread t = new Thread(() -> System.out.println("Hello from lambda"));
t.start();


# ExecutorService
ExecutorService is a thread pool manager in Java
Instead of creating threads manually, you submit tasks to it and it handles when, how, and on which thread they run

# Why use it?

Problems with manual threads:
Creating too many threads = high memory usage
Expensive to start/stop threads
Hard to manage in real-world apps
No lifecycle control
Hard to detect failures

ExecutorService solves everything:
Reuses threads (thread pool)
Manages a task queue
Handles exceptions
Can shut down gracefully
Better scalability and performance

# Important methods

submit(): runs a task and returns a future

execute(): simpler version

shutdown(): stops accepting new tasks but lets current ones finish.

shutdownNow(): attempts to interrupt running tasks

awaitTermination: Waits for the executor to fully shutdown

invokeAll(): Runs a list of tasks and waits for all results.

invokeAny(): Runs several tasks and returns the first completed result
