//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    MyHilo mihilo = new MyHilo();


    String opcion = "";
    System.out.println("Introduce 'S' para suspender , 'R' para reanudar o '*' para salir");
    opcion = new Scanner(System.in).nextLine();

    mihilo.start();
    while(!opcion.equalsIgnoreCase("*")){


        if (opcion.equalsIgnoreCase("S")) {
            System.out.println("Hilo suspendido");
            mihilo.Supender();
        } else if (opcion.equalsIgnoreCase("R")) {
            System.out.println("Hiilo reanudado");
            mihilo.Reanudar();
        }

        System.out.println("Introduce 'S' para suspender , 'R' para reanudar o '*' para salir");
        opcion = new Scanner(System.in).nextLine();

    }
    mihilo.pararHilo();


}
