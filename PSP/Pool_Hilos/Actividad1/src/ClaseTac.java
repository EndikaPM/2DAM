import java.util.concurrent.TimeUnit;

public class ClaseTac extends Thread {
    private String tac = "Tac";
    public ClaseTac(){}
    @Override
    public void run() {
        super.run();
        try {
            while (true) {
                IO.print(tac + ", ");
                TimeUnit.SECONDS.sleep(1);
            }
        }catch (InterruptedException e) {
            IO.print(e.getMessage());
            e.printStackTrace();
        }
    }
}
