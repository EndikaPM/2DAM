import java.util.concurrent.Semaphore;

void main() {
   /* RaceCounter race = new RaceCounter();
    Thread threadMario = new Thread(new Car("Mario", "Senda Arcoiris", race));
    Thread threadLuigi = new Thread(new Car("Luigi", "Senda Arcoiris",race));
    Thread threadToad = new Thread(new Car("Toad", "Senda Arcoiris",race));

    threadMario.start();
    threadLuigi.start();
    threadToad.start();
*/
    System.out.println("-------------------Plazas parking-------------------");

    Car hilo1 = new Car("Hilo1");
    Car hilo2 = new Car("Hilo2");
    Car hilo3 = new Car("Hilo3");

    Semaphore se = new Semaphore(2);
    Parking parkin1 = new Parking(hilo1, se);
    Parking parkin2 = new Parking(hilo2, se);
    Parking parking3 = new Parking(hilo3, se);

    Thread threadParking = new Thread(parkin1);
    Thread threadParking2 = new Thread(parkin2);
    Thread threadParking3 = new Thread(parking3);

    threadParking.start();
    threadParking2.start();
    threadParking3.start();
    
    /*Semaphore semaphore = new Semaphore(3);

    
    for (int i = 0; i < 6; i++) {
       Parking p = new Parking(new Car("Coche " + i), semaphore);
        Thread hilo = new Thread(p);
        hilo.start();
    }*/


}
