import lombok.Getter;

@Getter
public class Car implements Runnable {
    private String name;
    private String track;
    RaceCounter raceCounter;
    public Car(String name, String track, RaceCounter raceCounter) {
        this.name = name;
        this.track = track;
        this.raceCounter = raceCounter;
    }

    @Override
    public void run() {

        while (raceCounter.getCurrentPosicion() != 100) {
            raceCounter.incrementTurn();
            String text = "";
            if (raceCounter.getUsed() == true) {
                text = name + " esta en la posicion | " +
                        raceCounter.getCurrentPosicion() + " | en la pista " + track
                        + "el Hilo esta synchronized";
            }else {
                text = name + " esta en la posicion | " +
                        raceCounter.getCurrentPosicion() + " | en la pista " + track
                        + "el Hilo NO esta synchronized";
            }
            System.out.println(text);
        }
    }
}
