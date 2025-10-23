import java.util.Scanner;

public class Bank  implements Runnable{
    Account [] accounts;

    public Bank(Account[] accounts) {
        this.accounts = accounts;
    }

    // Transfiere dinero de una cuenta a otra
    public void transfer(int fromAccount, int toAccount, double amount) {
        if (accounts[fromAccount].getBalance() >= amount) {
            accounts[fromAccount].withdraw(amount);

            // Se la suma a la cuenta destino el total
            accounts[toAccount].deposit(amount);
        }
    }

    public void applyInterest(double rate, int accountNumber) {
        double addCo = (accounts[accountNumber].getBalance() * rate) /100;
        accounts[accountNumber].deposit(addCo);
    }

    @Override
    public void run() {
        for (int accountNumber = 0; accountNumber < 10; accountNumber++) {
            int id = (int) Math.random() * (accounts.length - 1);
            int id_Receptor = id; // ???

            // Mientras el id de quien recibe la transferencia no sea el que lo envía
            while (id != id_Receptor){
                id = (int) Math.random() * (accounts.length - 1);
            }

            transfer(id, id_Receptor, 100);
        }
    }
}