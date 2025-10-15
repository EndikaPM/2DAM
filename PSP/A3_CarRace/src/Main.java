public class Main {
    public static void main(String[] args) {
        RaceCounter race = new RaceCounter();
        Car c1 = new Car("Peach", "Mercado Cocotero", race);
        Car c2 = new Car("Daisy", "Mercado Cocotero", race);
        Car c3 = new Car("Stella", "Mercado Cocotero", race);

        c1.start();
        c2.start();
        c3.start();
    }
}
