/**
 * Programa que pida nombre/ip y muestre:
 * Nombre de host
 * Dirección ip
 * ¿Es alcanzable?
 * */
void main() {

    try{

        System.out.print("Introduce nombre de host o dirección IP: ");
        String datos = new Scanner(System.in).nextLine();

        InetAddress direccion = InetAddress.getByName(datos);
        System.out.println("Nombre de host: " + direccion.getHostName());
        System.out.println("Dirección IP: " + direccion.getHostAddress());

        boolean alcanzable = direccion.isReachable(5000);
        if (alcanzable) {
            System.out.println("La dirección es alcanzable");
        } else {
            System.out.println("La dirección no es alcanzable");
        }
    }catch (UnknownHostException e){
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
