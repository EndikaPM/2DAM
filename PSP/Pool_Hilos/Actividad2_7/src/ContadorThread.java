public class ContadorThread extends Thread{

    private static int contador = 0;

    public static void incrementarContador(){
        contador ++;
    }
    public static int getContador(){
        return contador;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5000; i++) {
            incrementarContador();
        }

    }
}
