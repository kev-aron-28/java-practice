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

