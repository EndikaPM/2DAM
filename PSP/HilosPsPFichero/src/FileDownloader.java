import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.concurrent.TimeUnit;

public class FileDownloader {
    URI link;
    URL url;

    public FileDownloader() throws MalformedURLException {
        this.link = URI.create("https://snap.stanford.edu/data/twitter.tar.gz");
        this.url = link.toURL(); // .toURL() -> obliga a lanzar una excepción
    }

    // ---- METODO QUE VA A IR LEYENDO EL URL ----
    public void readFile() {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {

            int contador = 0;
            String line="";
            while((line = in.readLine()) != null) {
                System.out.println("Descargando linea " + (++contador) + "fichero");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // ---- METODO QUE DESCARGA LA URL EN UN FICHERO .TXT ----
    public void downloadFile() throws IOException {
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream("src/urlDescargada.txt");
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();

        System.out.println("File downloaded!");
    }

    public void contadorLinea(){
            try {
                for (int i = 0; i <= 1000; i++) {
                    System.out.println(i);
                   // TimeUnit.SECONDS.sleep(1);
                    Thread.sleep(5);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }
}
