import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public  class Actividad3_3_Cliente {//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

    // click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
    public static void main(String [] arg) {
        try {
            // 1. Conectamos al servidor
            Socket socket = new Socket("localhost", 6003);

            //escribioms y leemos
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);

            // 2. Pedir texto al usuario
            System.out.print("Escribe un texto en MAYÚSCULAS para el servidor: ");
            String textoEnviar = sc.nextLine();

            // 3. Enviar mensaje
            salida.println(textoEnviar.toUpperCase());

            // 4. Recibir respuesta (el texto en minúsculas)
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor (minúsculas): " + respuesta);

            // 5. Cerrar
            entrada.close();
            salida.close();
            socket.close();
            sc.close();
            System.out.println("✓ Cliente desconectado.");
        } catch (IOException e) {
            System.err.println("Error: No se pudo conectar al servidor.");
        }
    }
}
