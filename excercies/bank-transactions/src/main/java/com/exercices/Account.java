package com.exercices;

import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final long id;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public long getId() {
        return id;
    }

    public boolean hasEnoughFundsToWithdraw(double amount) {
        if(this.balance - amount < 0) return false;

        return true;
    }

    public void lockAccount() {
        this.lock.lock();
    }

    public void unlockAccount() {
        this.lock.unlock();
    }

    public Account(long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public void deposit(double amount) {
        lock.lock();
        try {
            balance += amount;
            System.out.println("DEPOSIT " + Thread.currentThread().getName());
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }

    public void withDrawInteral(double amount) {
        this.balance -= amount;
    }

    public void depositInternal(double amount) {
        this.balance += amount;
    }

    public void withDraw(double amount) {
        lock.lock();

        try {
            if(this.balance - amount >= 0) {
                this.balance -= amount;
                System.out.println("WITHDRAWAL ACCOUNT: " + this.id + " " + "AMOUNT: " + amount);
            } else {
                System.out.println("WITHDRAWAL FAILED IN ACCOUNT: " + this.id + " " + "INSUFFICIENT FUNDS");
            }

            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            lock.unlock();
        }
    }
}
