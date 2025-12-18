import java.util.concurrent.TimeUnit;

public class MyHilo extends Thread{
    private int contador = 0;
    private boolean parar = false;
    private SolicitarSustender sustender = new SolicitarSustender();

    public void Supender(){sustender.set(true);}

    public void Reanudar(){sustender.set(false);}

    public void run(){
        try {
            while (parar){
                System.out.println("el contador esta en " + contador);
                TimeUnit.SECONDS.sleep(1);
            }
        }catch(InterruptedException ie){
            System.out.println(ie.getMessage());
        }
    }
}
