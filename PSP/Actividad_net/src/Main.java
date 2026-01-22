//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    try{
        InetAddress local = InetAddress.getLocalHost();
        System.out.println("Mi Ip: " + local.getHostAddress());
        System.out.println("Mi nombr: " + local.getHostAddress());

        boolean alcanzable = InetAddress.getLocalHost().isReachable(500);
    }catch (UnknownHostException e){
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}
