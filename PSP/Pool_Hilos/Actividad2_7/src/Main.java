//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


public class Main{
    public static void main(String[] args) {
        System.out.println("------------------Este es el metodo no sincronizado-------------");
        ContadorThread h1 = new ContadorThread();
        ContadorThread h2 = new ContadorThread();
        ContadorThread h3 = new ContadorThread();
        ContadorThread h4 = new ContadorThread();
        ContadorThread h5 = new ContadorThread();

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();
        try{
            h1.join();
            h2.join();
            h3.join();
            h4.join();
            h5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El contador esta en " + ContadorThread.getContador());

        ContadorSyncro h1S = new ContadorSyncro();
        ContadorSyncro h2S = new ContadorSyncro();
        ContadorSyncro h3S = new ContadorSyncro();
        ContadorSyncro h4S = new ContadorSyncro();
        ContadorSyncro h5S = new ContadorSyncro();
        System.out.println("------------------Este es el metodo sincronizado-------------");
        h1S.start();
        h2S.start();
        h3S.start();
        h4S.start();
        h5S.start();

        try{
            h1S.join();
            h2S.join();
            h3S.join();
            h4S.join();
            h5S.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("El contador esta en " + ContadorSyncro.getContadorSync());

        System.out.println("------------------Este es el metodo sincronizado-------------");
        ContadorRunnable h1R = new ContadorRunnable();
        ContadorRunnable h2R = new ContadorRunnable();
        ContadorRunnable h3R = new ContadorRunnable();
        ContadorRunnable h4R = new ContadorRunnable();
        ContadorRunnable h5R = new ContadorRunnable();

        Thread thr1= new Thread(h1R);
        Thread thr2= new Thread(h2R);
        Thread thr3= new Thread(h3R);
        Thread thr4= new Thread(h4R);
        Thread thr5= new Thread(h5R);

        thr1.start();
        thr2.start();
        thr3.start();
        thr4.start();
        thr5.start();

        try{
            thr1.join();
            thr2.join();
            thr3.join();
            thr4.join();
            thr5.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(" el contador Runable queda en " + ContadorRunnable.getContadorSyncRunnable());


    }
}
