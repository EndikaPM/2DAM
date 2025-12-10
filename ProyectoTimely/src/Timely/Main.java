import Model.Departamento.Departamento;
import Model.Departamento.DepartamentoModel;
import Model.Empresa.Empresa;
import Model.Empresa.EmpresaModelo;
import Model.Horas.HorasTrabajadas;
import Model.Horas.HorasTrabajadasModel;
import Model.Horas.Jornada;
import Model.Horas.JornadaModel;
import Model.Repository.Exception.DepartemantoException;
import Model.Repository.Exception.EmpresaException;
import Model.Repository.Exception.UsuarioExcepcion;
import Model.Repository.Impl.*;
import Model.Repository.Interface.*;
import Model.UserType;
import Model.Usuario.Usuario;
import Model.Usuario.UsuarioModel;
import Util.FechasUtil;

import java.sql.SQLException;


void main(String[] args) {

    try {
        UsuarioRespository usuarioRespository = new UsuarioRepositoriImpl();
        UsuarioModel usuarioModel = new UsuarioModel(usuarioRespository);
        EmpresaRepository empresaRepository = new EmpresaRepositoryImpl();
        EmpresaModelo empresaModelo =  new EmpresaModelo(empresaRepository);
        DepartamentoRepository departamentoRepository = new DepartamentoRepositoryImpl();
        DepartamentoModel departamentoModel = new DepartamentoModel(departamentoRepository);
        HorasRepository horasRepository = new HorasRepositoryImpl();
        HorasTrabajadasModel horasTrabajadasModel = new HorasTrabajadasModel(horasRepository);
        JornadaRepository jornadaRepository = new JornadaRepositoryImpl();
        JornadaModel jornadaModel = new JornadaModel(jornadaRepository);

//Cremaos la Empresa desde de todos los usuario
        Empresa inditex = new Empresa("B12345678", "Inditex","c/ Inventada 1 2ºiz");

//Le añadimos los departamentos a los que pertenece el usuario
        Departamento logisticaInditex = new Departamento(10,"Logistica",inditex);
        Departamento ventasInditex = new Departamento(11,"ventas",inditex);
        Departamento gestionInditex = new Departamento(12,"ventas",inditex);

//Creamos todos los usuario de la empresa y el departamento al que pertenecen
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
        Usuario admin = new Usuario("00000000O", "marta", "lopez", "marta.lopez@gmail.com", "marta_l", FechasUtil.convertirFecha("25/08/1999"),
                FechasUtil.convertirFecha("10/11/2022"), "109876543210", UserType.Administrador, gestionInditex);

        //empresaModelo.addEmpresa(inditex);

        //departamentoModel.addDepartamento(logisticaInditex);
        //departamentoModel.addDepartamento(ventasInditex);
        //departamentoModel.addDepartamento(gestionInditex);

        //usuarioModel.addUsuario(u1);// añadimos
        //usuarioModel.addUsuario(u2);
        //usuarioModel.addUsuario(u3);
        //usuarioModel.addUsuario(u4);
        //usuarioModel.addUsuario(u5);
        //usuarioModel.addUsuario(admin);
        //admin.setDepartamento(gestionInditex);
        //usuarioModel.updateUsuario(admin);
        //u2.setNombre("¡¡¡¡¡Editado!!!!!!");
        //usuarioModel.updateUsuario(u2);
        //usuarioModel.deleteUsuario(u1.getDni());

        //Añadimos Contratos a los usuarios
        HorasTrabajadas horasTrabajadasU1 = new HorasTrabajadas(u1,37.5F);
        //HorasTrabajadas horasTrabajadasU2 = new HorasTrabajadas(u2,20F);
        //HorasTrabajadas horasTrabajadasU3 = new HorasTrabajadas(u3,37.5F);
        //HorasTrabajadas horasTrabajadasU4 = new HorasTrabajadas(u4,30F);
        //HorasTrabajadas horasTrabajadasU5 = new HorasTrabajadas(u5,24F);

        //Añadir Jornadas

        Jornada jornada1U1 = new Jornada(u1, LocalDate.of(2025, 10, 20), LocalTime.of(7, 0));
        jornada1U1.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U1 = new Jornada(u1, LocalDate.of(2025, 10, 21), LocalTime.of(7, 0));
        jornada2U1.setHoraSalida(LocalTime.of(14, 0));

        Jornada jornada1U2 = new Jornada(u2, LocalDate.of(2025, 10, 20), LocalTime.of(10, 0));
        jornada1U2.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U2 = new Jornada(u2, LocalDate.of(2025, 10, 21), LocalTime.of(10, 0));
        jornada2U2.setHoraSalida(LocalTime.of(14, 0));

        Jornada jornada1U3 = new Jornada(u3, LocalDate.of(2025, 10, 20), LocalTime.of(7, 0));
        jornada1U3.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U3 = new Jornada(u3, LocalDate.of(2025, 10, 21), LocalTime.of(7, 0));
        jornada2U3.setHoraSalida(LocalTime.of(14, 0));

        Jornada jornada1U4 = new Jornada(u4, LocalDate.of(2025, 10, 20), LocalTime.of(8, 0));
        jornada1U4.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U4 = new Jornada(u4, LocalDate.of(2025, 10, 21), LocalTime.of(8, 0));
        jornada2U4.setHoraSalida(LocalTime.of(14, 0));

        Jornada jornada1U5 = new Jornada(u5, LocalDate.of(2025, 10, 20), LocalTime.of(9, 0));
        jornada1U5.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U5 = new Jornada(u5, LocalDate.of(2025, 10, 21), LocalTime.of(9, 0));
        jornada2U5.setHoraSalida(LocalTime.of(14, 0));

        // añadimos los horas a la base de datos
        //horasTrabajadasModel.addHoras(horasTrabajadasU1);
        //horasTrabajadasModel.addHoras(horasTrabajadasU2);
        //horasTrabajadasModel.addHoras(horasTrabajadasU3);
        //horasTrabajadasModel.addHoras(horasTrabajadasU4);
        //horasTrabajadasModel.addHoras(horasTrabajadasU5);
        //actualizamos las horas de la db
        //horasTrabajadasU1.setHorasContrato(400);
        //horasTrabajadasModel.updateHoras(horasTrabajadasU1);
        //borramos contratos de la db TODO

        //Añadimos jornadas
        // Añadimos jornadas U1
        /*jornadaModel.addEntradaJornada(jornada1U1);
        jornadaModel.addSalidaJornada(jornada1U1);
        jornadaModel.addEntradaJornada(jornada2U1);
        jornadaModel.addSalidaJornada(jornada2U1);

        // Añadimos jornadas U2
        jornadaModel.addEntradaJornada(jornada1U2);
        jornadaModel.addSalidaJornada(jornada1U2);
        jornadaModel.addEntradaJornada(jornada2U2);
        jornadaModel.addSalidaJornada(jornada2U2);

        // Añadimos jornadas U3
        jornadaModel.addEntradaJornada(jornada1U3);
        jornadaModel.addSalidaJornada(jornada1U3);
        jornadaModel.addEntradaJornada(jornada2U3);
        jornadaModel.addSalidaJornada(jornada2U3);

        // Añadimos jornadas U4
        jornadaModel.addEntradaJornada(jornada1U4);
        jornadaModel.addSalidaJornada(jornada1U4);
        jornadaModel.addEntradaJornada(jornada2U4);
        jornadaModel.addSalidaJornada(jornada2U4);

        // Añadimos jornadas U5
        jornadaModel.addEntradaJornada(jornada1U5);
        jornadaModel.addSalidaJornada(jornada1U5);
        jornadaModel.addEntradaJornada(jornada2U5);
        jornadaModel.addSalidaJornada(jornada2U5);*/
        //Actualizamos una jornada para qeu me salga Modificada true
        jornada1U1.setHoraSalida(LocalTime.now());
        //Eliminar una jornada
        //jornadaModel.deleteJornada(u1, jornada1U5.getId(), u5.getDni());//esto va a fallar porque no se el id luedo lo soluciono
        //jornadaModel.deleteJornada(admin, jornada1U5.getId(), u5.getDni());



        List<Usuario>usuarioList;
        List<Departamento>departamentoList;
        List<Empresa>empresaList;
        List<HorasTrabajadas>horasTrabajadasList;

        usuarioList = usuarioModel.getListUsuarios();
        departamentoList = departamentoModel.getListDepartamentos();
        empresaList = empresaModelo.getListEmpresa();

        horasTrabajadasList = horasTrabajadasModel.obtenerListHorasTrabajadas();

        /*for(Departamento d: departamentoList){
            System.out.println(d);
        }

        for(Empresa e : empresaList){
            System.out.println(e);
        }

        for(Usuario u : usuarioList){
            System.out.println(u);
        }*/
        //TODO PROBAR HORAS TRABAJADAS Y JORNADA

    } catch (SQLException e) {
        System.out.println(e.getMessage());
    } catch (ClassNotFoundException e) {
        System.out.println(e.getMessage());
    } catch (UsuarioExcepcion e) {
        System.out.println(e.imprimirMensaje());
    } catch (DepartemantoException e) {
        throw new RuntimeException(e);
    }catch (EmpresaException e) {
        throw new RuntimeException(e);
    }

}
