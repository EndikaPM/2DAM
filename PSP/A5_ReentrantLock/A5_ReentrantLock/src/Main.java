import java.util.ArrayList;

public class Main  {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== BANCO ===\n");

        // Crear 5 cuentas con balances iniciales
        Account cuenta1 = new Account(1000.0, "Cliente 1");
        Account cuenta2 = new Account(1500.0, "Cliente 2");
        Account cuenta3 = new Account(2000.0,  "Cliente 3");
        Account cuenta4 = new Account(500.0,  "Cliente 4");
        Account cuenta5 = new Account(1200.0,   "Cliente 5");

        // Agregar todas a un ArrayList
        ArrayList<Account> listaCuentas = new ArrayList<>();
        listaCuentas.add(cuenta1);
        listaCuentas.add(cuenta2);
        listaCuentas.add(cuenta3);
        listaCuentas.add(cuenta4);
        listaCuentas.add(cuenta5);

        // Crear el banco
        Bank banco = new Bank(listaCuentas);

        // Mostrar balances iniciales
        System.out.println("--- BALANCES INICIALES ---");
        System.out.println("Cuenta 1: " + cuenta1.getBalance() + "€");
        System.out.println("Cuenta 2: " + cuenta2.getBalance() + "€");
        System.out.println("Cuenta 3: " + cuenta3.getBalance() + "€");
        System.out.println("Cuenta 4: " + cuenta4.getBalance() + "€");
        System.out.println("Cuenta 5: " + cuenta5.getBalance() + "€");
        System.out.println("TOTAL: " + banco.getTotalBalance() + "€\n");

        System.out.println("--- INICIANDO OPERACIONES ---\n");

        // Crear hilos de transferencias (uno por uno, con nombres claros)
        Thread hilo1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " inicia");
            banco.transfer((int)(Math.random()*listaCuentas.size()), (int)(Math.random()*listaCuentas.size()), 200.0);
        },"Transferencia 1");

        Thread hilo2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " inicia");
            banco.transfer((int)(Math.random()*listaCuentas.size()), (int)(Math.random()*listaCuentas.size()), 500.0);
        }, "Transferencia 2");

        Thread hilo3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " inicia");
            banco.transfer((int)(Math.random()*listaCuentas.size()),(int)(Math.random()*listaCuentas.size()), 300.0);
        }, "Transferencia 3");

        // Crear hilos de intereses (uno por uno)
        Thread hiloInteres1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " inicia");
            cuenta5.applyInterest(0.05);
        }, "Cuenta 5");

        Thread hiloInteres2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " inicia");
            cuenta2.applyInterest(0.03);
        }, "Cuenta 2");

        // Iniciar todos los hilos uno por uno
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hiloInteres1.start();
        hiloInteres2.start();
        System.out.println("===========================================================================================================================================");
        // Esperar a que todos terminen
        hilo1.join();
        hilo2.join();
        hilo3.join();
        hiloInteres1.join();
        hiloInteres2.join();

        System.out.println("\n--- OPERACIONES FINALIZADAS ---\n");

        // Mostrar balances finales
        System.out.println("--- BALANCES FINALES ---");
        System.out.println("Cuenta 1: " + cuenta1.getBalance() + "€");
        System.out.println("Cuenta 2: " + cuenta2.getBalance() + "€");
        System.out.println("Cuenta 3: " + cuenta3.getBalance() + "€");
        System.out.println("Cuenta 4: " + cuenta4.getBalance() + "€");
        System.out.println("Cuenta 5: " + cuenta5.getBalance() + "€");
        System.out.println("TOTAL: " + banco.getTotalBalance() + "€");

        System.out.println("\n✓ El dinero total despues de intereses");
    }
}