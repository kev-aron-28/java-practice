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

