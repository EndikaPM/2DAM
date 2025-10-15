
public class RaceCounter  {
    private int currentPosicion = 0;
    private Car car;
    private boolean usedSyncronized;

    public RaceCounter(){}


    public synchronized void incrementTurn(){
        usedSyncronized = Thread.holdsLock(this);
        currentPosicion++;
    }

    public boolean getUsed(){
        return usedSyncronized;
    }

    public int getCurrentPosicion(){
        return currentPosicion;
    }
}
