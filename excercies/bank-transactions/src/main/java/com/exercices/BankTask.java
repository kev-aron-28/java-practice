package com.exercices;

import com.exercices.errors.InsufficientFundsException;

public class BankTask implements Runnable{
    private final Account account1;
    private Account account2;
    private final TaskType operation;
    private final double amount;
    private final BankService bankService;

    public BankTask(Account account1, TaskType operation, double amount, BankService bankService1) {
        this.operation = operation;
        this.account1 = account1;
        this.amount = amount;
        this.bankService = bankService1;
    }

    public BankTask(Account account1, Account account2, TaskType operation, double amount, BankService bankService) {
        this.account1 = account1;
        this.account2 = account2;
        this.operation = operation;
        this.amount = amount;
        this.bankService = bankService;
    }

    public void withdraw() {
        this.account1.withDraw(this.amount);
    }

    public void deposit() {
        this.account1.deposit(this.amount);
    }

    @Override
    public void run() {
        try {
            switch (this.operation) {
                case WITHDRAW -> this.withdraw();
                case DEPOSIT -> this.deposit();
                case TRANSFER -> this.bankService.transfer(account1, account2, amount);
                default -> throw new AssertionError();
            }
        } catch (InsufficientFundsException e) {
            System.out.println("INSUFFICIENT FUNDS ON ACCOUNT: " +  account1.getId() + " " + Thread.currentThread().getName());
        }
    }
}
