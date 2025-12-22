public class ContadorSyncro extends Thread{

    private static int contador = 0;

    public static synchronized void incrementarContador(){
        contador ++;
    }
    public static int getContadorSync(){
        return contador;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5000; i++) {
            incrementarContador();
        }

    }
}
