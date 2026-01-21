# Thread states
- new
- runnable
- blocked
- waiting
- timed waiting
- terminated

## New 
When you create a thread with the new operator—for example, new
Thread(r)—the thread is not yet running. This means that it is in the new
state. 

## Runnable
Once you invoke the start method, the thread is in the runnable state. A
runnable thread may or may not actually be running. It is up to the operating
system to give the thread time to run. 

static void yield()
causes the currently executing thread to yield to another thread. Note
that this is a static method.

## Blocked and waiting threads
When a thread is blocked or waiting, it is temporarily inactive. It doesn’t
execute any code and consumes minimal resources. It is up to the thread
scheduler to reactivate it. 

- When the thread tries to acquire an intrinsic object lock (but not a Lock in
the java.util.concurrent library) that is currently held by another thread,
it becomes blocked. The thread becomes unblocked when all other threads have relinquished the lock and the thread
scheduler has allowed this thread to hold it.

## Terminated
A thread is terminated for one of two reasons:
- It dies a natural death because the run method exits normally
- It dies abruptly because an uncaught exception terminates the run method.


# Interrupting threads
A thread terminates when its run method returns, by executing a return statement or if an exception occurs that is
not caught in the method. IN the initial release of java there also was a stop method, but now deprecated

Other than with the deprecated stop method, there is no way to force a thread to terminate. However, the
**interrupt** method can be used to request termination of a thread.

If you want to find out if a thead is interrupted

``` java
while (!Thread.currentThread().isInterrupted() && more work to do)  {}
```

But if a threadis blocked, it cannot check the interrupted status. THis is where the InterruptedExeption
comes in. 

The isInterrupted check is neither necessary nor useful if you call the sleep
method (or another interruptible method) after every work iteration.

Therefore, if your loop calls sleep, don’t check the interrupted status.
Instead, catch the InterruptedException


``` java
Runnable r = () -> 
   { 
      try 
      { 
         . . . 
         while (more work to do) 
         { 
            do more work 
            Thread.sleep(delay); 
         } 
      } 
      catch(InterruptedException e) 
      { 
         // thread was interrupted during sleep 
      } 
      finally 
      { 
        cleanup, if required 
      } 
      // exiting the run method terminates the thread 
   };
```

You’ll find lots of published code in which the InterruptedException is
squelched at a low level, like this:

``` java
void mySubTask() 
{ 
   . . . 
   try 
   { 
      sleep(delay); 
   } 
   catch (InterruptedException e) 
   { 
   } 
   // don't ignore! 
   . . . 
}
```

Don’t do that! If you cant think of anything to do in the catch

- in the catch, call Thread.currentThread().interrupt() to set the interrupted
status
- or even better tag your method with throws InterruptedException and drop 
the try block

- void interrupt(): sends an interrupt request to a thread. The interrupted status of the thread is set to true
- static boolean interrupted(): test whether the current thread has been interrupted but also set to false the interrupted status
- boolean isInterrupted(): test whether a thread has been interrupted. This does not change the interrupted status of the thread
- static Thread currentThread(): returns the current executing thread

# Deamon threads
You can turn a thread into a daemon thread by calling

``` java
t.setDaemon(true);
```

# Thread names
By default a thread has a java assigned name like THread-2 or something like that but you can change that 

``` java
var t = new Thread(runnable);
t.setName("web crawler");
```
# Handlers for Uncaught Exceptions

The run method of a thread cannot throw any checked exceptions, but it can
be terminated by an unchecked exception. In that case, the thread dies

The run method of a thread cannot throw any checked exceptions, but it can
be terminated by an unchecked exception. In that case, the thread dies

### What is an Uncaught Exception?
An exception is uncaught when:
- It is thrown inside a thread
- And not handled by a try-catch inside run()

``` java
public class PerThreadHandlerExample {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            throw new RuntimeException("Something went wrong");
        });

        t.setUncaughtExceptionHandler((thread, exception) -> {
            System.out.println("Thread: " + thread.getName());
            System.out.println("Error: " + exception.getMessage());
        });

        t.start();
    }
}
```

``` java
public class GlobalHandlerExample {

    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler((thread, exception) -> {
            System.err.println(
                "Global handler caught exception in " +
                thread.getName() + ": " +
                exception.getMessage()
            );
        });

        new Thread(() -> {
            throw new RuntimeException("Failure #1");
        }).start();

        new Thread(() -> {
            throw new IllegalStateException("Failure #2");
        }).start();
    }
}
```

## Hanlder via ThreadFactory
When you are using Executors threads are created internally
You must use ThreadFactory

``` java
import java.util.concurrent.*;

public class ThreadFactoryExample {

    public static void main(String[] args) {

        ThreadFactory factory = r -> {
            Thread t = new Thread(r);
            t.setUncaughtExceptionHandler((thread, ex) -> {
                System.err.println("Error in " + thread.getName());
                ex.printStackTrace();
            });
            return t;
        };

        ExecutorService executor =
                Executors.newFixedThreadPool(2, factory);

        executor.submit(() -> {
            throw new RuntimeException("Executor task failed");
        });

        executor.shutdown();
    }
}
```

## Thread priorities
In java every thread has a priority, by default inherits the priority of the thread that constructed it.
you can increase or decrease with setPriority
MIN_PRIORITY=1
MAX_PRIORITY=10


# Synchorization

There are two mechanism for protecting code block from concurrent access.
- synchronized 
- ReentrantLock


## Lock interface
``` java
import java.util.concurrent.locks.Lock;

public interface Lock {
    void lock();
    void unlock();
    boolean tryLock();
    boolean tryLock(long time, TimeUnit unit);
    void lockInterruptibly();
    Condition newCondition();
}
```

The basic outline for protecting a code block with a ReentrantLock is:
``` java
myLock.lock();
try {

} finally {
    lock.unlock();
}

```

This construct guarantees that only one thread at a time can enter the critical
section. As soon as one thread locks the lock object, no other thread can get
past the lock statement. 

When other threads call lock, they are deactivated
until the first thread unlocks the lock object.

************************* 

When you use locks, you cannot use the try-with-resources statement. First
off, the unlock method isn’t called close. But even if it was renamed, the
try-with-resources statement wouldn’t work. Its header expects the
declaration of a new variable. 

************************************************************************

The lock is called reentrant because a thread can repeatedly acquire a lock
that it already owns. The lock has a hold count that keeps track of the nested
calls to the lock method. The thread has to call unlock for every call to lock
in order to relinquish the lock. Because of this feature, code protected by a
lock can call another method that uses the same locks


## Condition objects
Often, a thread enters a critical section only to discover that it can’t proceed
until a condition is fulfilled. Use a condition object to manage threads that
have acquired a lock but cannot do useful work

### Time-of-check vs Time-of-use (TOCTOU)
- You check a condition
- Time passes
- You use the result
- The world has changed
- This is one of the most common concurrency bugs.

You can solve this by protecting both the test and the transfer action with a lock:

``` java
public void transfer(int from, int to, int amount) 
{ 
   bankLock.lock(); 
   try 
   { 
      while (accounts[from] < amount) 
      { 
         // wait 
         . . . 
      } 
      // transfer funds 
      . . . 
   } 
   finally 
   { 
      bankLock.unlock(); 
   } 
}

```

Now, what do we do when there is not enough money in the account? We
wait until some other thread has added funds. But this thread has just gained
exclusive access to the bankLock, so no other thread has a chance to make a
deposit. This is where condition objects come in.

A lock object can have one or more associated condition objects. You obtain
a condition object with the newCondition method. It is customary to give
each condition object a name that evokes the condition that it represents.

``` java
class Bank 
{ 
   private Condition sufficientFunds; 
   . . . 
   public Bank() 
   { 
      . . . 
      sufficientFunds = bankLock.newCondition(); 
   } 
}
```

In general a call to await should be inside a loop of the form

``` java
while (!(OK to proceed)) 
   condition.await();
```

WHen should you call signalAll? 
The rule of thumb is to call signalAll
whenever the state of an object changes in a way that might be advantageous
to waiting threads.


Note that the call to signalAll does not immediately activate a waiting
thread. It only unblocks the waiting threads so that they can compete for
entry into the object after the current thread has relinquished the lock.

Another method, signal, unblocks only a single thread from the wait set,
chosen at random. That is more efficient than unblocking all threads, but
there is a danger. If the randomly chosen thread finds that it still cannot
proceed, it becomes blocked again. If no other thread calls signal again, the
system deadlocks

----------------------------
A thread can only call await, signalAll, or signal on a condition if it owns
the lock of the condition

---------------------------

# The **syncrhonized keyword** 
A summary of the key points:
- A lock protects a critical sections of code allowing only one thread to execute the code at a time
- A lock manages threa ds that area trying to enter a protected code segment
- A lock can have one or more associeted condition objects
- Each condition object manages threads that have entered critical section but cannont proceed


Ever since version 1.0, every object in Java has an intrinsic lock. If a method is declared with the synchronized keyword, the object’s lock protects the entire
method. So to call the method a thread must acquire the intrinsic object lock

So: 

``` java 
public synchronized void method() 
{ 
method body 
}
```

is the equivalent of: 

``` java
public void method() 
{ 
   this.intrinsicLock.lock(); 
   try 
   { 
method body 
   } 
   finally 
   { 
      this.intrinsicLock.unlock(); 
   } 
}
```

The intrinsic object has a single associated condition. The wait method adds a thread to the wait set
and notifyAll/notify methods unblock waiting threads. Equivalent of await and signalAll

It is also legal to declare static methods as synchronized. If such a method is
called, it acquires the intrinsic lock of the associated class object. For example, if the Bank class has a static synchronized method, then the lock of the Bank.class object is locked when it is called. As a result, no other thread can call this or any other synchronized static method of the same class

Intrinsic locks have some limitations:
- You cannot interrupt a thread that is trying to acquire a lock
- You cannot specify a timeout when trying to acquire a lock
- having a single condition per lock can be inefficient 

## Syncrhonized blocks

``` java
synchronized (obj) // this is the syntax for a synchronized 
{ 
}
```

then it acquires the lock for obj.

YOu can find Ad hock locks such as:

``` java
public class Bank 
{ 
   private double[] accounts; 
   private Lock lock = new Object();   . . . 
   public void transfer(int from, int to, int amount) 
   { 
synchronized (lock) // an ad-hoc lock 
      { 
         accounts[from] -= amount; 
         accounts[to] += amount; 
      } 
      System.out.println(. . .); 
   } 
}
```

-  stay away from using primitive type wrappers as locks
- dont use strings also
- if you need to modify a static field, lock on the specific class not the value of getClass()

Sometimes, programmers use the lock of an object to implement additional atomic operations—a practice known as client-side locking.


The Java virtual machine has built-in support for synchronized methods. However, synchronized blocks are compiled into a lengthy sequence of bytecodes to manage the intrinsic lock

# The monitor concept

In java a monitor has the following properties:

- class with only private fields
- each object of that class has an associated locks
- All methods are locked by that lock. 
- It can have any number of associated conditions

Java object differs from a monitor in three important ways,
compromising thread safety:

- Fields are not required to be private.
- Methods are not required to be synchronized
- The intrinsic lock is available to clients.

## Volatile
The volatile keyword offers a lock-free mechanism for synchronizing
access to an instance field. If you declare a field as volatile, then the
compiler and the virtual machine take into account that the field may be
concurrently updated by another thread

suppose an object has a boolean flag done that is set by one
thread and queried by another thread. As we already discussed, you can use
a lock

``` java
private boolean done; 
public synchronized boolean isDone() { return done; } 
public synchronized void setDone() { done = true; }
```

The isDone and
setDone methods can block if another thread has locked the object.

If that is
a concern, one can use a separate lock just for this variable. But this is
getting to be a lot of trouble.

In this case, it is reasonable to declare the field as volatile:

``` java
private volatile boolean done; 
public boolean isDone() { return done; } 
public void setDone() { done = true; }
```

The compiler will insert the appropriate code to ensure that a change to the
done variable in one thread is visible from any other thread that reads the
variable
Volatile variables do not provide any atomicity.

## Final variables
you cannot safely read a field from multiple threads unless you use locks or the volatile modifier

There is one other situation in which it is safe to access a shared field—when
it is declared final

``` java
final var accounts = new  HashMap<String, Double>();
```
Without using final, there would be no guarantee that other threads would
see the updated value of accounts

# Atomics
CAS (Compare-And-Swap).

There are a number of classes in the java.util.concurrent.atomic package that use machine level instructions
to guarantee atomicity of other operations without using locks. 

``` java
import java.util.concurrent.atomic;
```

For instance, AtomicInteger class has method incrementAndGet and decrementAndGet that atomically increment
or decrement an integer   It is guaranteed that the correct value is computed and returned, even if multiple
threads access the same instance concurrently.

- CAS contention under high concurrency
- Throughput collapses with many writers

When you have a very large number of threads accessing the same atomic values, performance suffers because the optimistic updates require too many retries. The LongAdder and LongAccumulator classes solve this problem. 

A LongAdder is composed of multiple variables whose collective sum is the current value.

LongAccumulator:
- min/max/sum/custom ops
- High concurrency
- Staticts aggregation

LongAdder:
- High contention
- Mostly writes
- REads are occasional
- Sligh inaccuracy is acceptable

Atomic*:
- Low contention
- You need exact immediate value
- Logic depends on the value


# Deadlocks
Locks and conditions cannot solve all problems that might arise in multithreading. Consider the following situation:
- Accout 1: 200
- Account 2: 300
- Thread 1: Account 1 (300) -> Account 2
- Thread 2: Account 2 (400) -> Account 1

Neither can proceed because the balances in Accounts 1 and 2 are insufficiens It is possible that all threads get blocked because each is waiting for more
money. Such a situation is called a deadlock

## Thread local variables
A ThreadLocal variable gives each thread its own independent copy of a variable
It is risky sharing variables between threads. Sometimes, you can avoid sharing by giving each thread its own instance usint the ThreadLocal helper class

``` java
public static final ThreadLocal<SimpleDateFormat> dateFormat 
   = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
String dateStamp = dateFormat.get().format(new Date());
```

Another common problem is genereting random numbers in multiple threads. While the java.util.random is thread-safe you could use the ThreadLocal helper to give each 
thread a separate generator. but java 7 provides a convenienve class for you. 

```
int random = ThreadLocalRandom.current().nextInt(upperBound);
```

