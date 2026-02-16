import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Actividad4_2_Servidor {


    private static final int MAX_CLIENTES = 3;
    private static final int PUERTO = 6202;

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado.");
            System.out.println("Máximo de clientes: " + MAX_CLIENTES);
            System.out.println("Esperando clientes...");
            System.out.println();
            for (int i = 1; i <= MAX_CLIENTES; i++){
                Socket cliente = servidor.accept();
                Thread hilo = new Thread(new Manejador(cliente, i));
                hilo.start();
            }
            Thread.sleep(4000);
            System.out.println("Máximo de clientes alcanzado");
        } catch (IOException | InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
