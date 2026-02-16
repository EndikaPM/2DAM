import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Actividad4_1_Servidor {
    private static final int PUERTO = 6101;

    public static void main(String[] args) {
        try {
            System.out.println("=== Actividad 4.1 -SERVIDOR");
            //Iniciamos el Server el mismo puerto porque va a conectar el cliente
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor esperando a que el cliente envie un objeto persona...");
            //Permitimos al servidor aceptar peticiones
            Socket cliente = servidor.accept();
            //Utilizamos la entrada de objetos para recibir la persona
            ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
            //Leemos la persona completa e imprimimos sus datos
            Persona p = (Persona) entrada.readObject();//Importante castear el Objeto
            System.out.println("__Persona recibida__");
            System.out.println("Nombre: " + p.getNombre());
            System.out.println("Edad: " + p.getEdad());

            entrada.close();
            cliente.close();
            servidor.close();
        } catch (IOException e) {// LA Not Serilizable esta dentro de IOExeption
            System.err.println("Error E/S: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Clase no encontrada: " + e.getMessage());
        }
    }
}
