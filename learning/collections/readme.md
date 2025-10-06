# Collections framework

A collection is an object that represents a group of objects such as 
the classic vector class.
A collections framework is a unified architecture for representing and
manipulating collections, enabling collections to be manipulated

This consists of:
- Collection interfaces
- General purpose impl
- Legacy impl
- Special purpose implementation
- Concurrent impl
- Wrapper impl
- Convinience impl
- Abstract impl
- Algorithms
- Infra
- Array utilities

# Interfaces

java.util
	.Set
	.SortedSet
	.NavigableSet
	.Queue
	.concurrent.BlockingQueue
	.concurrent.TransferQueue
	.Deque
	.concurrent.BlockingQueue


# Collection impl


Interface	HashTable	ResizableArr	BalancedTree	LinkedList	Hastable+LinkedList
Set     	HashSet  			TreeSet				LinkedHashSet
List				ArrayList			LinkedList
Deque				ArrayDeque			LinkedList
Map		HashMap				TreeMap				LinkedHashMap
add)

# Map interface

# HashMap

java.util
Implements the map interface
Key-value pairs
keys are unique, but values can repeat


## Internals
Uses hashing to store data
Each key hashCode is calculated then mapped to an index in an internal array
called a bucket
If two keys map to the same bucket means a collition, java handles it using
a linked list or a balanced tree
Lookup, insert, delete are O(1)

Order is not guaranteed
Not synchronized
Default capacity = 16 buckets

## Basic op

- Create and insert

import java.util.*;

HashMap<String, Integer> map = new HashMap<>();
map.put("1", 10);

- Access
map.get("1");

- Update
map.put("1", 12); // Overwrites old value


- remove
map.remove("1");

map.remove("1", 50); // Only if key and value match

- Iteration

for(String key: map.keySet()){
	
}

for(String vale: map.values()) {}

for(Map.Entry<String, String> entry: map.entrySet()) {}

- Check existence

map.containsKey("apple")
map.containsValue(1)

- Size and empty
map.size();
map.isEmpty();
map.clear();

- Advanced
map.putIfAbsent("Mango", 40);
map.compute("1", (key,val) -> val == null ? 1 : val + 1);
























































