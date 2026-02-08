import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] arg) {
        try {
            // 1. Conectamos al servidor (localhost es tu propia máquina)
            Socket socket = new Socket("localhost", 6002);
            // Información de puertos para la actividad
            System.out.println("✓ Conectado al servidor.");
            System.out.println("Mi puerto local (cliente) es: " + socket.getLocalPort());
            System.out.println("El puerto del servidor es: " + socket.getPort());
            System.out.println("- IP remota: " + socket.getInetAddress().getHostAddress());

            // 2. Canales de comunicación
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);

            // 3. Enviar y recibir
            System.out.print("Escribe un mensaje para el servidor: ");
            String mensaje = sc.nextLine();
            salida.println(mensaje);

            String respuesta = entrada.readLine();
            System.out.println("Servidor responde: " + respuesta);

            // 4. Cerrar
            socket.close();
        } catch (IOException e) {
            System.err.println("Error: No se pudo conectar al servidor.");
        }
    }
}
