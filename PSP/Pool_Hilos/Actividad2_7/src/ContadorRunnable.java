public class ContadorRunnable implements Runnable{
    private static int contador = 0;

    public static synchronized void incrementarContador(){
        contador ++;
    }
    public static int getContadorSyncRunnable(){
        return contador;
    }

    @Override
    public void run() {

        for (int i = 0; i < 5000; i++) {
            incrementarContador();
        }

    }
}
