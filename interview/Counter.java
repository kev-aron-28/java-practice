package interview;

public class Counter {
    private int count = 0;

    public void increment() {
        count++;
    }

    public int get() {
        return count;
    }
}

// This looks innocent.
// It is NOT thread-safe.

// Problem 1: count++ is NOT atomic
// count++ looks like one operation, but it’s actually three CPU-level steps:
// Read count from memory
// Add 1
// Write the new value back

// Problem 2: Memory visibility (Java Memory Model)
// Each thread may:
// Cache count in a CPU register
// Cache count in L1/L2 cache
// So one thread may update count, but:
// Another thread may not see the updated value
// This is not a bug — it’s how CPUs and JVM are designed for performance.