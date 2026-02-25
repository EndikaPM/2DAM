import java.net.DatagramPacket;
import java.net.DatagramSocket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Actividad5_2_Servidor {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(9200);
            System.out.println("Servidor:\n" +
                    "=== ACTIVIDAD 5.2 - SERVIDOR ECO ===\n" +
                    "Servidor iniciado en puerto 9200");
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Recibido: " + mensaje);

                // 1. Preparamos la respuesta ANTES de comprobar si es el final
                String respuesta = "ECO: " + mensaje;
                byte[] datosRespuesta = respuesta.getBytes();
                DatagramPacket paqueteRespuesta = new DatagramPacket(
                        datosRespuesta, datosRespuesta.length,
                        paqueteRecibido.getAddress(), paqueteRecibido.getPort()
                );

                // 2. Enviamos el ECO siempre (incluso si es el último)
                socket.send(paqueteRespuesta);
                System.out.println("Enviado: " + respuesta);

                // 3. Ahora sí, si era "FIN", salimos del bucle con seguridad
                if (mensaje.equalsIgnoreCase("FIN")) {
                    break;
                }
            }
            socket.close();
            System.out.println("Servidor finalizando");
            System.out.println("Servidor cerrado");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}