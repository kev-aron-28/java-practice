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