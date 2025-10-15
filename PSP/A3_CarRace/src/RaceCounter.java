import lombok.Setter;

@Setter
public class RaceCounter {
//    int curPlace = 100;
//
//    public void race(){
//        for(int i = 0; i < 100; i++){
//            curPlace--;
//            System.out.println("Coche: " + car.getName() + ", Prosición actual: " + curPlace);
//        }
//    }
    private int valor = 0;

    public synchronized void incrementar() {
        valor++;
    }

    public int getValor() {
        return valor;
    }
}