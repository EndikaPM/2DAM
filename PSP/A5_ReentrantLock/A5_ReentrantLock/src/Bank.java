import java.util.ArrayList;

public class Bank {
    private ArrayList<Account> accounts;


    public Bank(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    // Transfiere dinero de una cuenta a otra
    public void transfer(int fromAccount, int toAccount, double amount) {
        if (accounts.get(fromAccount).getBalance() >= amount) {
            Account from = accounts.get(fromAccount);
            Account to = accounts.get(toAccount);

            from.getLock().lock();
            to.getLock().lock();

            try {
                // Guardamos los saldos ANTES de la transferencia
                double balanceFromAntes = from.getBalance();
                double balanceToAntes = to.getBalance();

                // Realizamos la transferencia
                accounts.get(fromAccount).withdraw(amount);
                accounts.get(toAccount).deposit(amount);

                // Obtenemos los saldos DESPUÉS de la transferencia
                double balanceFromDespues = accounts.get(fromAccount).getBalance();
                double balanceToDespues = accounts.get(toAccount).getBalance();

                System.out.println("El hilo " + Thread.currentThread().getName() + " del " + from.getName() +
                        "\n\t Cuenta origen nº" + fromAccount + " " + from.getName() + ":" +
                        "\n\t\t Saldo ANTES: " + balanceFromAntes + "€" +
                        "\n\t\t Saldo DESPUÉS: " + balanceFromDespues + "€" +
                        "\n\t Transfiere " + amount + "€ a la cuenta " + toAccount + " (" + to.getName() + ")" +
                        "\n\t Cuenta destino nº" + toAccount + " (" + to.getName() + "):" +
                        "\n\t\t Saldo ANTES: " + balanceToAntes + "€" +
                        "\n\t\t Saldo DESPUÉS: " + balanceToDespues + "€");
                System.out.println("===========================================================================================================================================");
            } finally {
                from.getLock().unlock();
                to.getLock().unlock();
            }
        } else {
            System.out.println("[" + Thread.currentThread().getName() + "] Crédito Insuficiente en " +
                    accounts.get(fromAccount).getName());
        }
    }
    public double getTotalBalance() {
        double total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }

    public int getNumAccounts() {
        return accounts.size();
    }
}