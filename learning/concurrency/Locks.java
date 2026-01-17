
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition testCondition = lock.newCondition();
        lock.lock();

        try {
        } finally {
            lock.unlock();
        }
    }
}