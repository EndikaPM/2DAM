//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
  /*Servidor acepta 2 clientes
  Muestra informacion de puertos
   */

    try {
        ServerSocket servidor = new ServerSocket(6000);
      // el 6000 es el puerto por el que escucha

      Socket cliente1 = servidor.accept();
      Socket cliente2 = servidor.accept();
    } catch (IOException e) {
      System.err.println("Error en cliente: " + e.getMessage());
    }


}
