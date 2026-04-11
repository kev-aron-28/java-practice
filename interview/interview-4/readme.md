- How can you make ArrayList immutable?
1. Collections.unmodifiableList() wraps and existing ArrayList in an unmodifiable view
2. List.of() creates an immutable list (java 9+)
3. Collections.toUnmodifiableList() converts a stream into an inmutable list
4. Custom immutable class

- Can final ArrayList be changed?
Yes, a final ArrayList can be changed in term of its contents, you cannot reassign the final variable 

- How can you make a ArrayList synhronized?
You can use the Collections.synchronizedList method

``` java
List<String> list = new ArrayList<>();

List<String> syncList = Collections.synchronizedList(list);
```

Synchrozation can impact performance especially,  each operation locks the entire list

- How does the LinkedList class implement the List, Deque, and queue interfaces simultaneously?
It uses a doubly-linked list

- How can we implement LRU cache (least recently used) using a linked list?
An LRU cache can be efficiently implemented using a combination of a doubly-linked list and a hash map.
The list maintains access order, while the hash map provides access and updates

- In what scenarios might a LinkedHashSet outperform a TreeSet and vice versa?
LinkedHashSet: A LinkedHashSet maintains insertion order and has faster performance for insertion and interation due to its linked list implementation

TreeSet maintains sorted order and is better suited for requiring sorted data

LinkedHashSet is better:
- You need to maintain insertion order
- Operations like add, remove and contains are frequent
- iteration order is important

TreeSet:
- Elements needs to be kept in order
- You need efficient range queries or nearest element searches
- Sorting is more crucial than insertion order

- You need to use a hashmap where the keys are complex objects, such as Person class, with attributes like name, age, and address, how to design this class?
1. Override the hashCode method to generate a consisten hash code based on significant fields, such as name and age 
2. Override the equals() method to compare the significant fields and ensure that two person objets are consider equal if they have same age, age and address
3. ensure these fields are immutable 

- What are the potential isues using mutable objects as keys?
If the objects state changes, it might no longer be found in the map because the hash code used to store it is no longer the same

- What would happen if you override only the equals() method and not the hashCode() in a custom key class used in HashMap?
It can lead to inconsisten behavior

Two keys that are consider equal by equals() might not have the same hash code
causing them to be placed in different buckets

- How would you implement a thread-safe HashMap without using
ConcurrentHashMap?

1. Using the collections.synchronizedMap which wraps the HashMap with synchronized methods

2. Manually synchronizing access to the HashMap

- What were the changes made to HashMap implementation in Java 8?
HashMap was optimized to improve performance when there are many hash collitions, Java introduced a balanced tree (red-black tree)

- Can you store null keys or values in a TreeMap?
No, you cannot, because it relies on Comparable or Comparator to sort the keys,

- What is the difference between HashMap and IdentityHashMap in terms of how they handle keys?
HashMap uses equals and hashCode method to determine the bucket location
IdentityHashMap uses the == operator, meaning that keys are considered equal only if they are the same instance

- What is Map.Entry?
Is a static nested interface within the Map interface in java, it represents a key-value pair or an entry in a map

``` java
Map<Integer, Integer> map = new HashMap<>();

for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
    entry.getKey() + entry.getValue()
}
```

- How does Collections.sort() work internally?
Collections.sort() method uses internally a modified version of Merge sort known as Timsort