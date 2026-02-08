import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class EndikaPerezCliene {
    public static void main(String[] args) {
        try {
            // 1. Conectamos al servidor
            Socket socket = new Socket("localhost", 6004);


            //escribioms y leemos
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner sc = new Scanner(System.in);

            // 2. Pedir numero al usuario
            boolean isNumero = false;
            String numeroEnviar = "";
            while (!isNumero) {
                System.out.print("Escribe un numero: ");
                 numeroEnviar= sc.nextLine();
                 try{
                     Integer.parseInt(numeroEnviar);
                     isNumero = true;
                 }catch (NumberFormatException e){
                     System.err.print("Error Intenta poner un numero: ");
                 }

            }

            // 3. Enviar mensaje
            salida.println(numeroEnviar);

            // 4. Recibir respuesta
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor : " + respuesta);

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