//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try {
            // 1. Abrimos el servidor en el puerto 6002
            ServerSocket servidor = new ServerSocket(6002);
            System.out.println("✓ Servidor iniciado en puerto: " + servidor.getLocalPort());
            System.out.println("Esperando a que se conecten 2 clientes...");

            // 2. Aceptamos al primer cliente
            Socket cliente1 = servidor.accept();
            System.out.println("✓ Cliente 1 conectado desde el puerto remoto: " + cliente1.getPort());

            // 3. Aceptamos al segundo cliente
            Socket cliente2 = servidor.accept();
            System.out.println("✓ Cliente 2 conectado desde el puerto remoto: " + cliente2.getPort());

            // 4. Creamos los canales de lectura y escritura para ambos
            BufferedReader entrada1 = new BufferedReader(new InputStreamReader(cliente1.getInputStream()));
            PrintWriter salida1 = new PrintWriter(cliente1.getOutputStream(), true);

            BufferedReader entrada2 = new BufferedReader(new InputStreamReader(cliente2.getInputStream()));
            PrintWriter salida2 = new PrintWriter(cliente2.getOutputStream(), true);

            // 5. Atendemos al Cliente 1
            String msg1 = entrada1.readLine();
            System.out.println("Cliente 1 dice: " + msg1);
            salida1.println("Hola Cliente 1, recibí tu mensaje: " + msg1.toUpperCase());


            // 6. Atendemos al Cliente 2
            String msg2 = entrada2.readLine();
            System.out.println("Cliente 2 dice: " + msg2);
            salida2.println("Hola Cliente 2, recibí tu mensaje: " + msg2.toUpperCase());

            // 7. Cerramos todo
            servidor.close();
            System.out.println("✓ Servidor finalizado.");

    }
    catch (IOException e) {
        System.err.println("Error en servidor: " + e.getMessage());
    }
}
}
