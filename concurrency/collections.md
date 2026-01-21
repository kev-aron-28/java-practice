# THread-safe collections

If multiple threads modify a data structure such as a hash table, it is easy to
damage  that data structure.

You can protect a shared data structure by supplying a lock, but it is usually easier to 
choose a thread-safe implementation instead.

## Blocking queues
Many threading problems can be formulated elegantly and safely by using one or more queues. Producer threads insert items into the queue, and consumer threads retrieve them. The queue lets you safely hand over datafrom one thread to another. 

A blocking queue causes a thread to block when you try to add an element when the queue is currently full or to remove an element when the queue is empty. 

It falls into three categories that differ by the action they perform when the queue is full or empty. If you use the queue as a thread managment tool use take and put.
the queue might become full or empty at any time, so you will instead want to use the offer, poll, and peek methods.

- The LinkedBlockingQueue has no upper bound on its capacity, but a maximum capacity can be optionally specified. 
- The LinkedBlockingDeque is a double-ended version
- The ArrayBlockingQueue is constructed with a given capacity and an optional parameter to require fairness
- The PriorityBlockingQueue is a priority queue, not a first-in/first-out queue. Elements are removed in order of their priority. The queue has unbounded capacity, but retrieval will block if the queue is empty. 
- A delayed queue lets you schedule work for the future instead of running it as soon as it’s enqueued.
- TransferQueue, Java 7 adds TransferQueue interface that allows a producer thread to wait until a consumer is ready to take on an item: The linkedTransferQueue implements this

## Efficient maps, sets and queues

The java.util.concurrent package supplies efficient implementations for maps, sorted sets, and queues: 
ConcurrentHashMap, ConcurrentSkipListMap,ConcurrentSkipListSet, and ConcurrentLinkedQueue

Unlike most collections, the size method of these classes does not necessarily operate in constant time. Determining the current size of one of
these collections usually requires traversal


# Tasks and thread pools
Constructing a new thread is somewhat expensive because it involves interaction with the operating system
If your program creates a large number of short-lived threads, you should not map each task to a separate thread, but use a thread pool instead.
A thread pool contains a number of threads that are ready to run. You give a runnable to the pool, and one of the threads calls the run method. 

## Callables and Futures
A Callable is similar to a Runnable, but it returns a value. The Callable interface is a parameterized type, with a single method call.

``` java
public interface Callable<V> 
{ 
   V call() throws Exception; 
}
```

A Future holds the result of an asynchronous computation. You start a computation, give someone the Future object, and forget about it.
The owner of the Future object can obtain the result when it is ready

# Executors
It has a number of static methods for constructing thread pools
- newCachedThreadPool, New threads are created as needed, idle threads are kept for 60 seconds
- newFixedThreadPool, the pool contains a fixed set of threads; idle threads are kept indefinitely
- newWorkStealingPool, A pool suitable for "fork-join" tasks in which complex tasks are broken up into simpler tasks and idle threads steal simpler tasks
- newSingleThreadExecutor, a pool with a single thread that executes tasks sequentially
- newSingleThreadScheduledExecutor, a single thread pool for scheduled execution

You can submit a Runnable or a Callabe to an ExecutorService with one of the following methods

Future<T> submit(Callable<T> task) 
Future<?> submit(Runnable task) 
Future<T> submit(Runnable task, T result)

## Futures
In Java, when you run work in another thread (via an ExecutorService), you often want:
- run asynchronusly the task
- get the result later
- check if finished
- cancel it 
- handle failure

A Future represents the result of a computation that may finish in the future.

When you have a Future object, you need to call get to obtain the value,
blocking until the value is available. 

So heres the basic idea of a future: You submit the tasks and it returns immediatly the Future<T>
meanwhile it is running in another thread and you can leter wait for the result, poll its status or cancel it

You dont create a future directly you get it by submitting a task  to an ExecutorService

### Checking status (non-blocking) 
``` java
future.isDone();
future.isCancelled();
```



# Controlling a group of tasks

You have seen how to use an executor service as a thread pool to increase the efficiency of task execution. Sometimes, an executor is used for a more
tactical reason—simply to control a group of related tasks. For example, you can cancel all tasks in an executor with the shutdownNow method

The invokeAny method submits all objects in a collection of Callable objects and returns the result of a completed task.
- invokeAll

# The fork join framework
is a concurrency framework designed to efficiently run recursive, divide-and-conquer tasks on multi-core CPUs.
Each worker thread has its own deque (double-ended queue).
The worker:
- Pushes tasks to the bottom
- Pops from the bottom

Idle workers:
- Steal from top of others workers deques


Split a big task into smaller subtasks (fork), execute them in parallel, then combine the results (join).

it is perfect for 
- recursive algorithms
- CPU bound workloads
- Large data processing

And it is not used for:
- Blocking I/O
- Not for long waits / locks

### Fork join pool
A specialized thread pool optimized for small tasks.

``` java
ForkJoinPool pool = new ForkJoinPool();
```

| Class              | When to use     |
| ------------------ | --------------- |
| `RecursiveTask<V>` | Returns a value |
| `RecursiveAction`  | No return value |

# Example

``` java
class SumTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 1_000;
    private final int[] arr;
    private final int start, end;

    protected Long compute() {
        if (end - start <= THRESHOLD) {
            long sum = 0;
            for (int i = start; i < end; i++) sum += arr[i];
            return sum;
        }

        int mid = (start + end) / 2;

        SumTask left = new SumTask(arr, start, mid);
        SumTask right = new SumTask(arr, mid, end);

        left.fork();              // async
        long rightResult = right.compute(); // sync
        long leftResult = left.join();

        return leftResult + rightResult;
    }
}
```


