public class Usuario extends Thread {
    private String nombre;
    private Saldo saldo;

    public Usuario(String nombre, Saldo saldo){
        this.nombre = nombre;
        this.saldo = saldo;
    }

    @Override
    public void run() {
        try {
            saldo.addCantidad(this.nombre,10.5F);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
