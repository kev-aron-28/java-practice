package interview.interview-4;

import java.util.HashMap;

public class LRU {
    class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map;

    private Node head;
    private Node tail;

    public LRU(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(0,0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    public void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void insert(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.
    }
}
