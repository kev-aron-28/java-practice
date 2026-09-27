# Mental model

- ArrayList: Dynamic Array
- LinkedList: Doubly linked list
- HashSet: Hash Table
- LInkedHashSet: Hash Table + linked ordering
- TreeSet: Balanced search tree
- HashMap: Hash table
- LinkedHashMap: hash table + linked ordering
- TreeMap: Balanced search tree
- ArrayDeque: Resizable circular array
- PriorityQueue: Binary heap

# Big-O vocabulary
For collections, you'll repeatedly encounter:

- O(1) - constant
The amount of work does not grow with n

- O(log n) - logarithmic
Every comparison eliminates roughly half the remaining search space

- O(n) - linear
Potentially inspect every element

- Amortized (1)
This one is particularly important
An operation may occasionally be expensive, but over many operations, the average cost is constant

Where ArrayList.add() is the classic example, most of the time is O(1) but when the array is full you have to allocate
a larger array and copy elements then add 

## List
A list represents an ordered collection where elements have positions / indexes
- Preserves insertion order
- duplicates allowed
- indexed access
- generally permits null
- not inherently thread-safe

List: ArrayList, LinkedList

### ArrayList

| Operation             |     Complexity |
| --------------------- | -------------: |
| `get(index)`          |           O(1) |
| `set(index)`          |           O(1) |
| `add(element)`        | Amortized O(1) |
| `add(index, element)` |           O(n) |
| `remove(index)`       |           O(n) |
| `remove(object)`      |           O(n) |
| `contains()`          |           O(n) |
| `indexOf()`           |           O(n) |


ArrayList generally has much better cache locality because its elements are stored in a contiguous array

### LinkedList
Its a doubly linked list
It can be useful when you are geneuinely working with frequent operations at the ends
| Operation             |     Complexity |
| --------------------- | -------------: |
| `get(index)`          |           O(1) |
| `set(index)`          |           O(1) |
| `add(element)`        | Amortized O(1) |
| `add(index, element)` |           O(n) |
| `remove(index)`       |           O(n) |
| `remove(object)`      |           O(n) |
| `contains()`          |           O(n) |
| `indexOf()`           |           O(n) |

## Set
A collection where duplicate elements are not allowed

The main implementations:

Set: HashSet, LInkedHashSet, TreeSet

### HashSet
Internally a HashSet is backed by a HashMap, it provides expected O(1) lookup, insertion and removal

### LinkedHashSet
This combines the HashSet behavior and linked ordering 

### TreeSet
TreeSetInternally based ona balanced tree, specifically a Red-Black tree, values are maintained in sorted order

uniqueness + sorted order + navigational operations

## Map
A map is not technically a collection, but it belongs to the collections framework

Map: HashMap, LinkedHashMap, TreeMap
and in the concurrent: ConcurrentHashMap, ConcurrentSkipListMap

### HashMap
| Operation       | Average |
| --------------- | ------: |
| `get()`         |    O(1) |
| `put()`         |    O(1) |
| `remove()`      |    O(1) |
| `containsKey()` |    O(1) |

Now different keys can have the same hash / bucket, java therefore needs a mechanism for multiple entries in one bucket
Modern Java can transform heavily-collided buckets into a tree structure
This is why modern Java HashMap severe collision behavorio can become O(log n) rather than O(n)

A hashMap has:
- capacity
- load factor
- threshold

The default load factor is commonly: 0.75 threshold: capacity * loadFactor so if the map exceeds the threshold, it resizes

### LinkedHashMap
HashMap + doubly-linked ordering structure
It can maintain insertion order or access order

### TreeMap
internally a Red-Black Tree
| Operation  | Complexity |
| ---------- | ---------: |
| `get()`    |   O(log n) |
| `put()`    |   O(log n) |
| `remove()` |   O(log n) |


But you get powerful navigation, you use this when you need ordered keys + logarithmc lookup + navigation

## Queue
A queue represents: Usually FIFO processing
Queue: ArrayDeque, LinkedList, PriorityQueue


### ArrayDeque
Internally its essentially a resizable circular array, typically O(1) amortized for insertion / removal at the ends

### PriorityQueue
This is not a FIFO, its a heap, the smallest / biggest element is at the root by default 
| Operation    | Complexity |
| ------------ | ---------: |
| `peek()`     |       O(1) |
| `poll()`     |   O(log n) |
| `offer()`    |   O(log n) |
| `contains()` |       O(n) |

## The normal collections cheat sheet
| Collection      | Structure          |   Lookup |   Insert |   Remove | Ordering         |
| --------------- | ------------------ | -------: | -------: | -------: | ---------------- |
| `ArrayList`     | Dynamic array      |    O(1)* |    O(1)* |     O(n) | Insertion        |
| `LinkedList`    | Doubly linked list |     O(n) |   O(1)** |   O(1)** | Insertion        |
| `HashSet`       | Hash table         |    O(1)* |    O(1)* |    O(1)* | None             |
| `LinkedHashSet` | Hash + linked list |    O(1)* |    O(1)* |    O(1)* | Insertion        |
| `TreeSet`       | Red-Black tree     | O(log n) | O(log n) | O(log n) | Sorted           |
| `HashMap`       | Hash table         |    O(1)* |    O(1)* |    O(1)* | None             |
| `LinkedHashMap` | Hash + linked list |    O(1)* |    O(1)* |    O(1)* | Insertion/access |
| `TreeMap`       | Red-Black tree     | O(log n) | O(log n) | O(log n) | Sorted           |
| `ArrayDeque`    | Circular array     |        — |    O(1)* |    O(1)* | Ends             |
| `PriorityQueue` | Binary heap        |     O(n) | O(log n) | O(log n) | Priority         |
