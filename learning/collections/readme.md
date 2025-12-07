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
Set     	HashSet  			         TreeSet				    LinkedHashSet
List				    ArrayList	     LinkedList
Deque				    ArrayDeque	     LinkedList
Map		HashMap				 TreeMap		       LinkedHashMap


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


# Review

## List
Colección ordenada, permite elementos duplicados
| Clase          | Orden                  | Duplicados | Velocidad                             |
| -------------- | ---------------------- | ---------- | ------------------------------------- |
| **ArrayList**  | mantiene orden         | sí         | acceso rápido, insertar lento (medio) |
| **LinkedList** | mantiene orden         | sí         | inserciones/eliminaciones rápidas     |
| **Vector**     | antiguo y sincronizado | sí         | lento                                 |


## Set
Colección SIN duplicados.
| Clase             | Orden                       | Característica           |
| ----------------- | --------------------------- | ------------------------ |
| **HashSet**       | no garantiza orden          | más rápido               |
| **LinkedHashSet** | mantiene orden de inserción | ligeramente más lento    |
| **TreeSet**       | orden natural o Comparator  | utiliza árbol rojo-negro |

## Map
Estructura clave valor, llaves unicas
| Clase                 | Orden                  | Observaciones       |
| --------------------- | ---------------------- | ------------------- |
| **HashMap**           | no garantiza orden     | más usado           |
| **LinkedHashMap**     | orden de inserción     | útil para caches    |
| **TreeMap**           | ordenado por llave     | basado en árbol     |
| **Hashtable**         | sincronizado, obsoleto | evitar              |
| **ConcurrentHashMap** | seguro para hilos      | uso en concurrencia |

## Queue
FIFO
| Clase             | Tipo                  | Uso              |
| ----------------- | --------------------- | ---------------- |
| **LinkedList**    | FIFO                  | implementa Queue |
| **PriorityQueue** | orden según prioridad | heap             |
| **ArrayDeque**    | extremadamente rápida | stack/queue      |

# Deque
Se puede usar como pila (stack) o cola.
ArrayDeque (mejor que Stack)

# 







 



