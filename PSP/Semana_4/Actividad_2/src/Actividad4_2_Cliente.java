import java.io.*;
import java.net.Socket;

public class Actividad4_2_Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 6202;

    public static void main(String[] args) {
        try{
            Socket servidor = new Socket(HOST, PUERTO);

            BufferedReader entrada = new BufferedReader(new InputStreamReader(servidor.getInputStream()));
            PrintWriter salida = new PrintWriter(new OutputStreamWriter(servidor.getOutputStream()), true);

            salida.println("Hola, soy un cliente.");
            System.out.println("Enviando: Hola, soy un cliente");
            System.out.println("Respuesta: " + entrada.readLine());

            entrada.close();
            salida.close();
            servidor.close();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
