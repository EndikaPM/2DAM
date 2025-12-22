import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class MyHilo extends Thread{
    private int contador = 0;
    private boolean parar = false;
    private SolicitarSustender sustender = new SolicitarSustender();

    public void Supender(){sustender.set(true);}

    public void Reanudar(){sustender.set(false);}

    public void run(){
        try {
            while (!parar){
                contador ++;
                System.out.println("el contador esta en " + contador);
                TimeUnit.SECONDS.sleep(1);
                sustender.esperandoParaReanudar();
            }
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
        }
    }

    public void pararHilo(){
        this.parar = true;
        Reanudar();

        System.out.println("El contador se queda en: " + contador);
    }
}
