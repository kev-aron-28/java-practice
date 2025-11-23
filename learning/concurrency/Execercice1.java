
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        double newBalance = balance + amount;
        System.out.println(Thread.currentThread().getName() +
                " deposited " + amount + " | New Balance = " + newBalance);
        balance = newBalance;
    }

    public synchronized void withDraw(double amount) {
        if(balance >= amount) {
            double newBalance = balance - amount;
            System.out.println(Thread.currentThread().getName() +
                    " withdrew " + amount + " | New Balance = " + newBalance);
            balance = newBalance;
        } else {
            System.out.println(Thread.currentThread().getName() +
                    " attempted to withdraw " + amount +
                    " but insufficient funds (Balance = " + balance + ")");
        }
    }

    public synchronized double getBalance() {
        return this.balance;
    }
}

class TransactionTask implements Runnable {

    private BankAccount account;
    private double amount;
    private boolean isDeposit;

    public TransactionTask(BankAccount account, double amount, boolean isDeposit) {
        this.account = account;
        this.amount = amount;
        this.isDeposit = isDeposit;
    }

    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isDeposit() {
        return isDeposit;
    }

    public void setDeposit(boolean isDeposit) {
        this.isDeposit = isDeposit;
    }


    @Override
    public void run() {
        if(this.isDeposit) {
            this.account.deposit(amount);
        }  else {
            account.withDraw(amount);
        }
    }
    
    
}


public class Execercice1 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount(100);

        ExecutorService executor = Executors.newFixedThreadPool(4);

        for(int i = 0; i < 5; i++) {
            executor.submit(new TransactionTask(account, 50, true));
            executor.submit(new TransactionTask(account, 34, false));
        }

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("Final balance " + account.getBalance());
    }
}