import java.util.concurrent.TimeUnit;

public class ClaseTic extends Thread{
    private String tic = "Tic";
    public ClaseTic(){}

    @Override
    public void run() {
        while (true) {
            try {
                IO.print(tic + ", ");
                TimeUnit.SECONDS.sleep(1);

            } catch (InterruptedException e) {
                IO.print(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
