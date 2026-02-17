# Java collections framework

The initial release of java supplied only a small set of classes for the most useful data structures: Vector, Stack, HashTable, BitSet
and the Enumeration interface.

## Separating Collection interfaces and implementation

With this approach, if you change your mind, you can easily use a different implementation. 
You only need to change your program in one place—in the constructor call.

When you study the API documentation, you will find another set of classes whose name begins with Abstract, such as AbstractQueue. These classes are
intended for library implementors. 

## The collection interface 
The fundamental interface for collection classes in the Java library is the Collection interface. The interface has two fundamental methods:

``` java
public interface Collection<E> 
{ 
   boolean add(E element); 
   Iterator<E> iterator(); 
 
   . . . 
}
```

## Iterators
The Iterator interface has four methods:

``` java
public interface Iterator<E> 
{ 
   E next(); 
   boolean hasNext(); 
   void remove(); 
   default void forEachRemaining(Consumer<? super E> action); 
}
```

By repeatedly calling the next method, you can visit the elements from the collection one by one. However, if you reach the end of the collection, the
next method throws a NoSuchElementException. Therefore, you need to call the hasNext method before calling next. 

If you want to inspect all elements in a collection, request an iterator and then keep calling the next method while hasNext returns true.

``` java
Collection<String> c = . . .; 
Iterator<String> iter = c.iterator(); 
while (iter.hasNext()) 
{ 
    String element = iter.next(); 
    do something with element 
}
```

OR you can use the for each loop:

``` java
for (String element : c) 
{ 
    do something with element 
}
```

There are two fundamental interfaces for collections: Collection and Map. As you already saw, you insert elements into a collection with a method

However, maps hold key/value pairs, and you use the put method to insert
them:

``` java
V put(K key, V value)
```

A List is an ordered collection. Elements are added into a particular position in the container. An element can be accessed in two ways: by an iterator or
by an integer index. The latter is called random access because elements can be visited in any order. In contrast, when using an iterator, one must visit them sequentially.

In practice, there are two kinds of ordered collections, with very different performance tradeoffs. An ordered collection that is backed by an array has
fast random access, and it makes sense to use the List methods with an integer index. In contrast, a linked list, while also ordered, has slow random
access, and it is best traversed with an iterator. It would have been an easy matter to provide two interfaces


The Set interface is identical to the Collection interface, but the behavior of the methods is more tightly defined. 

# Concrete collections

1. List
    1. ArrayList
    2. LinkedList
2. Set
    1. HashSet
    2. LinkedHashSet: Keep insertion order
    3. TreeSet: Natural Order o use a comparator, ceiling(), floor(), higher(), lower()
3. Map
    1. HashMap
    2. LinkedHashMap: Keep insertion order
    3. TreeMap: Order by key
4. Queue / Dequeue
    1. ArrayDeque: For stack or queue
    2. PriorityQueue: Access the min or the max with comparator


Traversing a Map

``` java
Map<String, Integer> map = new HashMap<>();
map.put("A", 1);
map.put("B", 2);
map.put("C", 3);

for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}

for (String key : map.keySet()) {
    System.out.println(key);
}

for (Integer value : map.values()) {
    System.out.println(value);
}

Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();

while (it.hasNext()) {
    Map.Entry<String, Integer> entry = it.next();
    System.out.println(entry.getKey() + " -> " + entry.getValue());
}

//java 8+
map.forEach((key, value) -> {
    System.out.println(key + " -> " + value);
});



```