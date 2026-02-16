import java.io.*;
import java.net.Socket;

public class Manejador implements Runnable{

    private Socket cliente;
    private int idCliente;

    public Manejador (Socket cliente, int idCliente){
        this.cliente = cliente;
        this.idCliente = idCliente;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(new OutputStreamWriter(cliente.getOutputStream()), true)
        ) {
            System.out.println("Cliente " + idCliente + " conectado.");

            String mensajeRecibido = entrada.readLine();
            System.out.println("Cliente " + idCliente + ": " + mensajeRecibido);

            String msg = "Servidor: Recibido. Eres cliente " + idCliente;
            salida.println(msg);

            Thread.sleep(2000); // Simula procesamiento

            cliente.close(); // Cerramos el socket al terminar
            System.out.println("Cliente " + idCliente + " desconectado");
        } catch (IOException | InterruptedException e) {
            System.err.println("Error en el Handler con cliente " + idCliente + ": " + e.getMessage());
        }
    }
}
