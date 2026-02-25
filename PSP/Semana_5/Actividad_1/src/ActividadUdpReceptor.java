import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ActividadUdpReceptor {
    public static void main(String[] args) {
        final int PUERTO = 9100;

        try {
            // 1. Crear socket en puerto 9100
            DatagramSocket socket = new DatagramSocket(PUERTO);
            System.out.println("Esperando mensajes...");
            // 2. Preparar buffer para recibir
            byte[] buffer = new byte[1024];
            DatagramPacket paquete = new DatagramPacket(buffer,
                    buffer.length);
            // 3. Recibir (SE BLOQUEA AQUÍ)
            socket.receive(paquete);
            // 4. Extraer mensaje
            String mensaje = new String(paquete.getData(), 0,
                    paquete.getLength());
            System.out.println("Contenido: " + mensaje);
            System.out.println("Desde: " + paquete.getAddress());
            System.out.println("Puerto: " + paquete.getPort());
            System.out.println("Tamaño: " + paquete.getLength() + " bytes.");
            // 5. Cerrar
            socket.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
