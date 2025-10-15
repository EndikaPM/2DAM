import lombok.Getter;

@Getter
public class Car extends Thread {
    String nombre;
    String track;
    boolean isSynchronized;
    RaceCounter rc;

    public Car(String name, String track, RaceCounter rc) {
        this.nombre = name;
        this.track = track;
        this.rc = rc;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            rc.incrementar();
            System.out.println("Coche " + this.nombre + " en la pista " + track + ", puesto: " + rc.getValor());
        }
    }
}