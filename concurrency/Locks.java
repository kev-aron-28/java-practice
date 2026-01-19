
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


class BankAccountWithLock {
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition sufficientFunds = lock.newCondition();

    public BankAccountWithLock(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("DEPOSITED: " + amount );

            sufficientFunds.signalAll();
        } finally {
            lock.unlock();
        } 
    }

    public void withdraw(double amount) throws InterruptedException {
        lock.lock();

        try {
            while(this.balance < amount) {
                System.out.println("WAITING FOR FOUNDS...");
                sufficientFunds.wait();
            } 

            this.balance -= amount;
            System.out.println("WITHDREW: " + amount);
        } finally {
            lock.unlock();
        }
    }

    public double getBalance() {
        return this.balance;
    }
}

class BankAccountWithSync {
    private double balance;

    public BankAccountWithSync(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        this.balance += amount;

        notifyAll();
    }

    public synchronized void withDraw(double amount) throws InterruptedException {
        while(this.balance < amount) {
            wait();
        }

        this.balance =- amount;
    }

}

public class Locks {
    public static void main(String[] args) throws InterruptedException {
        BankAccountWithLock account = new BankAccountWithLock(100);

        Runnable withDraw = () -> {
            try {
                account.withdraw(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Runnable deposit = () -> {
            try {
                Thread.sleep(2000);
                account.withdraw(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        ExecutorService pool = Executors.newFixedThreadPool(3);

        pool.submit(withDraw);
        pool.submit(deposit);

        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("BALANCE IS: " + account.getBalance());
    }
}