import lombok.Getter;

import java.util.Scanner;

@Getter
public class Account{
    private double balance;

    public void deposit(double amount) {
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (this.balance >= amount) {
            this.balance -= amount;
        }
    }
}