import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Actividad5_2_Cliente {
    public static void main(String[] args) {
        // 1. Crear el Scanner FUERA para evitar fugas de memoria
        try (Scanner sc = new Scanner(System.in);
             DatagramSocket socket = new DatagramSocket()) {

            InetAddress direccion = InetAddress.getByName("localhost");
            System.out.println("=== CLIENTE ECO INICIADO ===");

            while(true) {
                System.out.print("Mensaje: ");
                String mensajeEnviar = sc.nextLine();

                // Convertir y enviar SIEMPRE (para que el servidor también se entere del FIN)
                byte[] datos = mensajeEnviar.getBytes();
                DatagramPacket paqueteEnviar = new DatagramPacket(datos, datos.length, direccion, 9200);
                socket.send(paqueteEnviar);

                if (mensajeEnviar.equalsIgnoreCase("FIN")) break;

                // Recibir respuesta
                byte[] buffer = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Servidor: " + respuesta);
            }
            System.out.println("Cliente cerrado.");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
