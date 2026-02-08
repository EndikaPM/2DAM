import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Actividad3_3_Servidor {
    public static void main(String[] arg) {
        try {
            // 1. Abrimos el servidor en el puerto 6003
            ServerSocket servidor = new ServerSocket(6003);
            System.out.println("✓ Servidor iniciado en puerto: " + servidor.getLocalPort());
            System.out.println("Esperando a  cliente...");

            // 2. Aceptamos al primer cliente
            Socket cliente = servidor.accept();
            System.out.println("✓ Cliente 1 conectado desde el puerto remoto: " + cliente.getPort());


            // 3. Flujos de lectura y escritura
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            // 4. Recibir mensaje (se supone que viene en MAYÚSCULAS)
            String mensajeRecibido = entrada.readLine();
            System.out.println("Mensaje original: " + mensajeRecibido);

            // 5. PROCESAMIENTO: Convertir a minúsculas
            String mensajeMinusculas = mensajeRecibido.toLowerCase();

            // 6. Enviar de vuelta el mensaje modificado
            salida.println(mensajeMinusculas);
            System.out.println("Mensaje enviado al cliente: " + mensajeMinusculas);

            // 7. Cerrar todo
            cliente.close();
            servidor.close();
            System.out.println("✓ Servidor cerrado.");

        } catch (IOException e) {
            System.err.println("Error en servidor: " + e.getMessage());
        }
    }
}
