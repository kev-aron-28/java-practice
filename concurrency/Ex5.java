
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class Printer implements  Runnable {
    private int counter;
    private final ReentrantLock lock;

    public Printer(ReentrantLock lock, int counter) {
        this.lock = lock;
        this.counter = counter;
    }

    @Override
    public void run() {
        while(true) {
            lock.lock();
            try {
                if(counter > 100) return;

                System.out.println("[" + Thread.currentThread().getName() + "] " + counter);
                counter++;

            } finally {
                lock.unlock();
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

}

public class Ex5 {    
    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        ReentrantLock lock = new ReentrantLock();
        int counter = 0;
        Printer print = new Printer(lock, counter);

        pool.submit(print);
        pool.submit(print);

        pool.shutdown();


        pool.awaitTermination(10, TimeUnit.SECONDS);

    }
}