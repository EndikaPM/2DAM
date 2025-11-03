import lombok.Getter;


public class Car implements Runnable {
    private String carName;
    private String track;
    RaceCounter raceCounter;
    public Car(String name, String track, RaceCounter raceCounter) {
        this.carName = name;
        this.track = track;
        this.raceCounter = raceCounter;
    }

    public Car(String name) {
        this.carName = name;
    }

    public String getCarName(){
        return carName;
    }

    @Override
    public void run() {

        while (raceCounter.getCurrentPosicion() != 300) {
            raceCounter.incrementTurn();
            String text = "";
            if (raceCounter.getUsed() == true) {
                text = carName + " esta en la posicion | " +
                        raceCounter.getCurrentPosicion() + " | en la pista " + track
                        + "el Hilo esta synchronized";
            }else {
                text = carName + " esta en la posicion | " +
                        raceCounter.getCurrentPosicion() + " | en la pista " + track
                        + "el Hilo NO esta synchronized";
            }
            System.out.println(text);
        }
    }
}
