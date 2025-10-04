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

