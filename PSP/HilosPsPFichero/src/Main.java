//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    try {
        FileDownloader tarea1 = new FileDownloader();
        FileDownloader tarea2 = new FileDownloader();

        Thread hilo1 = new Thread(new Runnable() {
            @Override
            public void run() {
                tarea1.contadorLinea();
            }
        });

        Thread hilo2 = new Thread(new Runnable() {
            @Override
            public void run() {
                tarea2.readFile();
            }
        });

        hilo1.start();
        hilo2.start();

    } catch (Exception e) {
        e.printStackTrace();
    }
}
