import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ActividadUdpEmisor {
    public static void main(String[] args) {
        final String HOST = "localhost";
        final int PUERTO = 9100;

        try {
            // 1. Crear socket UDP
            DatagramSocket socket = new DatagramSocket();
            System.out.println("=== EMISOR UDP ===");

            // 2. Preparar mensaje
            System.out.print("Escribe el mensaje que deseas enviar: ");
            String mensaje = new Scanner(System.in).nextLine();
            byte[] datos = mensaje.getBytes();

            System.out.println("Enviando: " + mensaje);

            // 3. Crear paquete con datos + dirección + puerto
            InetAddress direccion = InetAddress.getByName(HOST);
            DatagramPacket paquete = new DatagramPacket(
                    datos,
                    datos.length,
                    direccion,
                    PUERTO
            );

            // 4. Enviar paquete
            socket.send(paquete);
            System.out.println("Mesaje enviado.");

            // 5. Cerrar socket
            socket.close();
            System.out.println("Emisor cerrado");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}