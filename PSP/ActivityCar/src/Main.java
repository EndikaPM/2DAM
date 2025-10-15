
void main() {
    RaceCounter race = new RaceCounter();
    Runnable mario = new Car("Mario", "Senda Arcoiris", race);
    Runnable luigi = new Car("Luigi", "Senda Arcoiris",race);
    Runnable toad = new Car("Toad", "Senda Arcoiris",race);

    Thread threadMario = new Thread(mario);
    Thread threadLuigi = new Thread(luigi);
    Thread threadToad = new Thread(toad);

    threadMario.start();
    threadLuigi.start();
    threadToad.start();
}
