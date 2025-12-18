public class SolicitarSustender {
    private boolean suspender;
    public synchronized void set (boolean b) {
        suspender= b; //cambio de estado sobre el objeto
        notifyAll () ;
    }
    public synchronized void esperandoParaReanudar()
            throws InterruptedException {
        while (suspender)
            wait(); //SUSPENDER HILO HASTA RECIBIR notify () o notifiAll ()
    }
}
