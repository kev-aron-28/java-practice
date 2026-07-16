package com.exercices;

import com.exercices.errors.InsufficientFundsException;

public class BankService {
    public void transfer(Account from, Account to, double amount) {
        Account a = from.getId() > to.getId() ? to : from;
        Account b = from.getId() < to.getId() ? from : to;

        a.lockAccount();
        
        try {
            b.lockAccount(); 

            try {
                if(!from.hasEnoughFundsToWithdraw(amount)) {
                    throw new InsufficientFundsException();
                }
    
                from.withDrawInteral(amount);
                to.depositInternal(amount);
    
                Thread.sleep(1000);
            } finally {
                b.unlockAccount();
            }
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        } finally {
            a.unlockAccount();
        }
    }
}
