import Model.Departamento.Departamento;
import Model.Departamento.DepartamentoModel;
import Model.Empresa.Empresa;
import Model.Empresa.EmpresaModelo;
import Model.Horas.HorasTrabajadas;
import Model.Horas.HorasTrabajadasModel;
import Model.Horas.Jornada;
import Model.Horas.JornadaModel;
import Repository.Exception.DepartemantoException;
import Repository.Exception.EmpresaException;
import Repository.Exception.UsuarioExcepcion;
import Repository.Impl.*;
import Repository.Interface.*;
import Model.UserType;
import Model.Usuario.Usuario;
import Model.Usuario.UsuarioModel;
import Util.FechasUtil;

import java.sql.SQLException;


void main(String[] args) {
    try {
        // Inicialización de repositorios y modelos...
        UsuarioRespository usuarioRespository = new UsuarioRepositoriImpl();
        UsuarioModel usuarioModel = new UsuarioModel(usuarioRespository);
        EmpresaRepository empresaRepository = new EmpresaRepositoryImpl();
        EmpresaModelo empresaModelo = new EmpresaModelo(empresaRepository);
        DepartamentoRepository departamentoRepository = new DepartamentoRepositoryImpl();
        DepartamentoModel departamentoModel = new DepartamentoModel(departamentoRepository);
        HorasRepository horasRepository = new HorasRepositoryImpl();
        HorasTrabajadasModel horasTrabajadasModel = new HorasTrabajadasModel(horasRepository);
        JornadaRepository jornadaRepository = new JornadaRepositoryImpl();
        JornadaModel jornadaModel = new JornadaModel(jornadaRepository);

        Empresa inditex = new Empresa("B12345678", "Inditex", "c/ Inventada 1 2ºiz");
        empresaModelo.addEmpresa(inditex);

        Departamento logisticaInditex = new Departamento(10, "Logistica", inditex);
        Departamento ventasInditex = new Departamento(11, "ventas", inditex);
        Departamento gestionInditex = new Departamento(12, "gestion", inditex);

        departamentoModel.addDepartamento(logisticaInditex);
        departamentoModel.addDepartamento(ventasInditex);
        departamentoModel.addDepartamento(gestionInditex);

        Usuario u1 = new Usuario("52413669H", "usuario", "prueba1", "usuario.prueba1@gmail.com", "usuario1",
                FechasUtil.convertirFecha("20/12/2000"), FechasUtil.convertirFecha("18/10/2020"),
                "412365478965", UserType.Empleado, logisticaInditex);

        Usuario u2 = new Usuario("6541355L", "usuario", "prueba2", "usuario.prueba2@gmail.com", "usuario2",
                FechasUtil.convertirFecha("20/12/2000"), FechasUtil.convertirFecha("18/10/2020"),
                "412365478965", UserType.Empleado, ventasInditex);

        Usuario u3 = new Usuario("78901234A", "ana", "garcia", "ana.garcia@gmail.com", "ana_g",
                FechasUtil.convertirFecha("15/05/1995"), FechasUtil.convertirFecha("01/03/2021"),
                "987654321012", UserType.Jefe, logisticaInditex);

        Usuario u4 = new Usuario("90123456B", "luis", "fernandez", "luis.fernandez@gmail.com", "luis_f",
                FechasUtil.convertirFecha("10/11/1988"), FechasUtil.convertirFecha("20/07/2018"),
                "543210987654", UserType.Empleado, ventasInditex);

        Usuario u5 = new Usuario("12345678C", "marta", "lopez", "marta.lopez@gmail.com", "marta_l",
                FechasUtil.convertirFecha("25/08/1999"), FechasUtil.convertirFecha("10/11/2022"),
                "109876543210", UserType.Empleado, logisticaInditex);

        Usuario admin = new Usuario("00000000O", "admin", "administrador", "admin@gmail.com", "admin123",
                FechasUtil.convertirFecha("25/08/1999"), FechasUtil.convertirFecha("10/11/2022"),
                "109876543210", UserType.Administrador, gestionInditex);

        // Insertar usuarios en la BD
        usuarioModel.addUsuario(u1);
        usuarioModel.addUsuario(u2);
        usuarioModel.addUsuario(u3);
        usuarioModel.addUsuario(u4);
        usuarioModel.addUsuario(u5);
        usuarioModel.addUsuario(admin);

        u2.setNombre("¡¡¡¡¡Editado!!!!!!");
        usuarioModel.updateUsuario(u2);

        HorasTrabajadas horasTrabajadasU1 = new HorasTrabajadas(u1, 1776.0F);
        HorasTrabajadas horasTrabajadasU2 = new HorasTrabajadas(u2, 888F);
        HorasTrabajadas horasTrabajadasU3 = new HorasTrabajadas(u3, 1665F);
        HorasTrabajadas horasTrabajadasU4 = new HorasTrabajadas(u4, 1332F);
        HorasTrabajadas horasTrabajadasU5 = new HorasTrabajadas(u5, 1065.6F);

        horasTrabajadasModel.addHoras(horasTrabajadasU1);
        horasTrabajadasModel.addHoras(horasTrabajadasU2);
        horasTrabajadasModel.addHoras(horasTrabajadasU3);
        horasTrabajadasModel.addHoras(horasTrabajadasU4);
        horasTrabajadasModel.addHoras(horasTrabajadasU5);

        // Actualizar horas de contrato
        horasTrabajadasU1.setHorasContrato(400);
        horasTrabajadasModel.updateHoras(horasTrabajadasU1);

        //TODO Tenfo ue usasr el FechaUtil cuando lo haga bien
        Jornada jornada1U1 = new Jornada(u1, LocalDate.of(2025, 10, 20), LocalTime.of(7, 0));
        jornada1U1.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U1 = new Jornada(u1, LocalDate.of(2025, 10, 21), LocalTime.of(7, 0));
        jornada2U1.setHoraSalida(LocalTime.of(14, 0));

        //TODO Tenfo ue usasr el FechaUtil cuando lo haga bien
        Jornada jornada1U2 = new Jornada(u2, LocalDate.of(2025, 10, 20), LocalTime.of(10, 0));
        jornada1U2.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U2 = new Jornada(u2, LocalDate.of(2025, 10, 21), LocalTime.of(10, 0));
        jornada2U2.setHoraSalida(LocalTime.of(14, 0));

        //TODO Tenfo ue usasr el FechaUtil cuando lo haga bien
        Jornada jornada1U3 = new Jornada(u3, LocalDate.of(2025, 10, 20), LocalTime.of(7, 0));
        jornada1U3.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U3 = new Jornada(u3, LocalDate.of(2025, 10, 21), LocalTime.of(7, 0));
        jornada2U3.setHoraSalida(LocalTime.of(14, 0));

        //TODO Tenfo ue usasr el FechaUtil cuando lo haga bien
        Jornada jornada1U4 = new Jornada(u4, LocalDate.of(2025, 10, 20), LocalTime.of(8, 0));
        jornada1U4.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U4 = new Jornada(u4, LocalDate.of(2025, 10, 21), LocalTime.of(8, 0));
        jornada2U4.setHoraSalida(LocalTime.of(14, 0));

        //TODO Tenfo ue usasr el FechaUtil cuando lo haga bien
        Jornada jornada1U5 = new Jornada(u5, LocalDate.of(2025, 10, 20), LocalTime.of(9, 0));
        jornada1U5.setHoraSalida(LocalTime.of(14, 0));
        Jornada jornada2U5 = new Jornada(u5, LocalDate.of(2025, 10, 21), LocalTime.of(9, 0));
        jornada2U5.setHoraSalida(LocalTime.of(14, 0));

        // Añadir jornadas U1
        jornadaModel.addEntradaJornada(jornada1U1);
        jornadaModel.addSalidaJornada(jornada1U1);
        jornadaModel.addEntradaJornada(jornada2U1);
        jornadaModel.addSalidaJornada(jornada2U1);

        // Añadir jornadas U2
        jornadaModel.addEntradaJornada(jornada1U2);
        jornadaModel.addSalidaJornada(jornada1U2);
        jornadaModel.addEntradaJornada(jornada2U2);
        jornadaModel.addSalidaJornada(jornada2U2);

        // Añadir jornadas U3
        jornadaModel.addEntradaJornada(jornada1U3);
        jornadaModel.addSalidaJornada(jornada1U3);
        jornadaModel.addEntradaJornada(jornada2U3);
        jornadaModel.addSalidaJornada(jornada2U3);

        // Añadir jornadas U4
        jornadaModel.addEntradaJornada(jornada1U4);
        jornadaModel.addSalidaJornada(jornada1U4);
        jornadaModel.addEntradaJornada(jornada2U4);
        jornadaModel.addSalidaJornada(jornada2U4);

        // Añadir jornadas U5
        jornadaModel.addEntradaJornada(jornada1U5);
        jornadaModel.addSalidaJornada(jornada1U5);
        jornadaModel.addEntradaJornada(jornada2U5);
        jornadaModel.addSalidaJornada(jornada2U5);

        System.out.println("\n=== LISTA DE USUARIOS ===");
        List<Usuario> usuarioList = usuarioModel.getListUsuarios();
        for (Usuario u : usuarioList) {
            System.out.println(u);
        }

        System.out.println("\n=== HORAS TRABAJADAS ===");
        List<HorasTrabajadas> horasTrabajadasList = horasTrabajadasModel.obtenerListHorasTrabajadas();
        for (HorasTrabajadas ht : horasTrabajadasList) {
            System.out.println(ht);
        }

    } catch (SQLException e) {
        System.out.println("Error SQL: " + e.getMessage());
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        System.out.println("Error ClassNotFound: " + e.getMessage());
    } catch (UsuarioExcepcion e) {
        System.out.println("Error Usuario: " + e.imprimirMensaje());
    } catch (DepartemantoException e) {
        System.out.println("Error Departamento: " + e.getMessage());
    } catch (EmpresaException e) {
        System.out.println("Error Empresa: " + e.getMessage());
    }
}
