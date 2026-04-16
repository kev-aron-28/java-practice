# LRU Cache

An LRU Cache stores key-value pairs with a fixed capacity

Rules:
- When you get(key), return value if exists, and mark it as recently used
- when you put(key, value):
    - if exists, update value + mar as recently used
    - if key does not exists: 
        - if full, remove the least recently used
        - insert a new key as most recently used

The item that has not been accessed for the longest time
gets evicted first

Interviews expect:
- O(1) for get
- O(1) for put

# The optimal design

We combine
1. HashMap 
2. Doubly linked list
    - Head: MRU
    - Tail: LRU

Why doubly?

Need O(1) removal of arbitray nodes

# How it works
get(key):
1. Check map
2. If not exists, return -1
3. if exist:
    - move node to head
    - return value
    
put(key, value):
1. if key exists:
    - update value
    - move node to head
2. if does not exists
    - if full:
        - remove tail 
        - delete from map
    - insert new node at head
    - add to map