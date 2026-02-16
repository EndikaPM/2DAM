import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Actividad4_1_Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 6101;

    public static void main(String[] args) {
        try {
            System.out.println("=== Actividad 4.1 -Cliente");
            // Añadimos un host y un Puerto (en este caso el 6101)
            Socket servidor = new Socket(HOST, PUERTO);
            //Solicitamos nombre y Edad de la persona
            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce un nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Introduce una edad: ");
            int edad = sc.nextInt();
            //Alimentamos los datos de la persona
            Persona persona = new Persona(nombre, edad);
            //Usuamos el ObjectImputStrem para enviar los datos
            ObjectOutputStream salida = new ObjectOutputStream(servidor.getOutputStream());
            // Alimentamos los datos para enviarlos al servidor
            salida.writeObject(persona);

            System.out.println("Objeto persona enviado");

            salida.close();
            servidor.close();
            sc.close();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
