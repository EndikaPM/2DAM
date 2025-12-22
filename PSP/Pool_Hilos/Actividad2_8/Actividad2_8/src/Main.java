import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Saldo saldo = new Saldo(150.00F);

    Usuario u1 = new Usuario("Maria", saldo);
    Usuario u2 = new Usuario("Jose", saldo);
    Usuario u3 = new Usuario("Jesus", saldo);
    Usuario u4 = new Usuario("Burra", saldo);
    Usuario u5 = new Usuario("Buey", saldo);

    try {
        System.out.println("------El saldo incial es de :" + saldo.getSaldo()+"----------");
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

    u1.start();
    u2.start();
    u3.start();
    u4.start();
    u5.start();


    try {
        u1.join();
        u2.join();
        u3.join();
        u4.join();
        u5.join();

        System.out.println("==========El saldo final es de :" + saldo.getSaldo()+"===========");
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

}
