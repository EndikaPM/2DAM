import Model.Departamento.Departamento;
import Model.Empresa.Empresa;
import Model.Repository.Impl.UsuarioRepositoriImpl;
import Model.Repository.UsuarioExcepcion;
import Model.Repository.UsuarioRespository;
import Model.UserType;
import Model.Usuario.Usuario;
import Model.Usuario.UsuarioModel;
import Util.FechasUtil;

import java.sql.SQLException;


void main() {


    Empresa inditex = new Empresa("B12345678", "Inditex","c/ Inventada 1 2ºiz");

    Departamento logisticaInditex = new Departamento(10,"Logistica",inditex);
    Departamento ventasInditex = new Departamento(10,"ventas",inditex);

    Usuario u1 = new Usuario("52413669H", "usuario", "prueba1", "usuario.prueba1@gmail.com", "usuario1",FechasUtil.convertirFecha("20/12/2000"),
            FechasUtil.convertirFecha("18/10/2020"), "412365478965", UserType.Empleado,logisticaInditex);
    Usuario u2 = new Usuario("6541355L", "usuario", "prueba2", "usuario.prueba2@gmail.com", "usuario2",FechasUtil.convertirFecha("20/12/2000"),
            FechasUtil.convertirFecha("18/10/2020"), "412365478965", UserType.Empleado,ventasInditex);
    Usuario u3 = new Usuario("78901234A", "ana", "garcia", "ana.garcia@gmail.com", "ana_g", FechasUtil.convertirFecha("15/05/1995"),
            FechasUtil.convertirFecha("01/03/2021"), "987654321012", UserType.Jefe, logisticaInditex);
    Usuario u4 = new Usuario("90123456B", "luis", "fernandez", "luis.fernandez@gmail.com", "luis_f", FechasUtil.convertirFecha("10/11/1988"),
            FechasUtil.convertirFecha("20/07/2018"), "543210987654", UserType.Empleado, ventasInditex);
    Usuario u5 = new Usuario("12345678C", "marta", "lopez", "marta.lopez@gmail.com", "marta_l", FechasUtil.convertirFecha("25/08/1999"),
            FechasUtil.convertirFecha("10/11/2022"), "109876543210", UserType.Empleado, logisticaInditex);

    try {
        UsuarioRespository usuarioRespository = new UsuarioRepositoriImpl();
        UsuarioModel usuarioModel = new UsuarioModel(usuarioRespository);
        /*usuarioModel.addUsuario(u1);// añadimos
        usuarioModel.addUsuario(u2);
        usuarioModel.addUsuario(u3);
        usuarioModel.addUsuario(u4);
        usuarioModel.addUsuario(u5);*/
        u2.setNombre("¡¡¡¡¡Editado!!!!!!");
        usuarioModel.updateUsuario(u2);// implementar DepartamentoRepository y EmpresaReposistory
        usuarioModel.deleteUsuario(u1.getDni());
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println(e.getMessage());
    } catch (UsuarioExcepcion e) {
        System.out.println(e.imprimirMensaje());
    }

}
