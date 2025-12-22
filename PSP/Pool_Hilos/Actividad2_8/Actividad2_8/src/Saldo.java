import java.util.concurrent.TimeUnit;

public class Saldo {

    private float saldo;

    public Saldo(float saldo){
        this.saldo = saldo;
    }

    public float getSaldo() throws InterruptedException{
        TimeUnit.SECONDS.sleep((int)(Math.random()*2) +1);
        return saldo;
    }

    public void setSaldo(float saldo) throws InterruptedException{
        TimeUnit.SECONDS.sleep((int)(Math.random()*2) +1);
        this.saldo = saldo;
    }

    public synchronized void addCantidad(String nombre,float cantidad) throws InterruptedException {
        System.out.println("El usuario "+nombre+" que tiene un Saldo de :" + this.saldo +
                "\n\tAñade " + cantidad +" € ");

         setSaldo(getSaldo() +cantidad);

        System.out.println("-> Cantidad despues de añadir queda en: " + this.saldo+"\n" +
                "______________________________________________________________________");
    }
}
