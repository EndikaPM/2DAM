import lombok.Getter;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;


public class Account{
    private double balance;
    String name;
    private final ReentrantLock block = new ReentrantLock();

    public Account(double balance, String name){
        this.balance = balance;
        this.name = name;
    }

    public  void deposit(double amount) {
        block.lock();
        try {
            balance += amount;
        } finally {
            block.unlock();
        }
    }

    public void withdraw(double amount) {
        block.lock();
        try {
            if (balance >= amount) {
                balance -= amount;
            }
        } finally {
            block.unlock();
        }
    }

    public void applyInterest(double rate) {
        block.lock();
        try {
            double oldBalance = balance;
            balance += balance * rate;
            System.out.printf("[%s] Interés aplicado: %.2f -> %.2f (tasa: %.2f%%)%n",
                    Thread.currentThread().getName(), oldBalance, balance, rate * 100);
        } finally {
            block.unlock();
        }
    }

    public double getBalance() {
        block.lock();
        try {
            return balance;
        } finally {
            block.unlock();
        }
    }

    public ReentrantLock getLock() {
        return block;
    }

    public String getName() {
        return name;
    }
}