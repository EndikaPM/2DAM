import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


@Getter @AllArgsConstructor
public class Parking implements Runnable {
    private Car car;
    private Semaphore semaphore;


    @Override
    public void run() {
        try {
            //Parking justo
            System.out.println(car.getCarName() + " Intenta entrar en el parking. ");
            System.out.println("con el " +car.getCarName()+ " Hay " + semaphore.availablePermits() + " plazas libres. ");
            semaphore.acquire();
            System.out.println(car.getCarName() + " aparca. ");
            System.out.println("con el " +car.getCarName()+ " Hay " + semaphore.availablePermits() + " plazas libres. ");
            TimeUnit.SECONDS.sleep(5);
            System.out.println(car.getCarName() + " libera la plaza. ");
            //Parquin injusto
            /*if(semaphore.tryAcquire(2, TimeUnit.SECONDS)) {
                System.out.println(car.getName() + " aparca. ");
                System.out.println("con el " +car.getName()+ " Hay " + semaphore.availablePermits() + " plazas libres. ");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(car.getName() + " libera la plaza. ");
            }*/

        }catch (InterruptedException e){
            e.getStackTrace();
        }finally {
            semaphore.release();
            System.out.println("con el " +car.getCarName() + " Hay " + semaphore.availablePermits() + " plazas libres. ");
        }
    }
}
