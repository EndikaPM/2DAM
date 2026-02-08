import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EndikaPerezServidor {
    public static void main(String[] args) {
        try {
            // 1. Abrimos el servidor en el puerto 6004
            ServerSocket servidor = new ServerSocket(6004);
            System.out.println("✓ Servidor iniciado en puerto: " + servidor.getLocalPort());
            System.out.println("Esperando a  cliente...");

            // 2. Aceptamos al cliente
            Socket cliente = servidor.accept();
            System.out.println("✓ Cliente 1 conectado desde el puerto remoto: " + cliente.getPort());


            // 3. Flujos de lectura y escritura
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            // 4. Recibir mensaje
            String mensajeRecibido = entrada.readLine();
            System.out.println("Mensaje original: " + mensajeRecibido);
            // tranformamos a int
            Integer numero = Integer.parseInt(mensajeRecibido);
            Integer cuadrado = numero * numero;


            String numeroReenviar = String.valueOf(cuadrado);

            // 6. Enviar de vuelta el mensaje modificado
            salida.println(numeroReenviar);
            System.out.println("Mensaje enviado al cliente: " + numeroReenviar);

            // 7. Cerrar todo
            cliente.close();
            servidor.close();
            System.out.println("✓ Servidor cerrado.");

        } catch (IOException e) {
            System.err.println("Error en servidor: " + e.getMessage());
        }
    }
}