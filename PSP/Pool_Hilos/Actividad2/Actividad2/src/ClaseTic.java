import java.util.concurrent.TimeUnit;

public class ClaseTic implements Runnable{
    private String tic = "Tic";
    public ClaseTic(){}
    @Override
    public void run() {
        try {
            while (true) {
                IO.print(tic + ", ");
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (InterruptedException e) {
            IO.print(e.getMessage());
        }
    }
}
