package Model.Repository.Impl;

import Model.Departamento.Departamento;
import Model.Empresa.Empresa;
import Model.Horas.Jornada;
import Model.Repository.Exception.JornadaExeception;
import Model.Repository.Interface.JornadaRepository;
import Model.UserType;
import Model.Usuario.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class JornadaRepositoryImpl implements JornadaRepository {
    Connection con;
    private String sentenciaSQL;
    private ArrayList<Jornada> listaHorasJornadas;
    private Jornada jornada;

    public JornadaRepositoryImpl() throws SQLException, ClassNotFoundException {
        con = Connexion.conectar();
    }

    @Override
    public ArrayList<Jornada> obtenerListJornadas() throws JornadaExeception {
        listaHorasJornadas = new ArrayList<>();
        this.sentenciaSQL = "SELECT j.*, u.*, d.*, e.* " +
                "FROM jornada j" +
                "INNER JOIN usuario u ON u.dni = j.id_trabajador" +
                "INNER JOIN departamento d ON d.id = u.department" +
                "INNER JOIN empresa e ON e.nif = d.id_empresa";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            ResultSet rs = preStat.executeQuery();
            while(rs.next()){
                //obtenemos usuario
                String dni = rs.getString("dni");
                String nombre = rs.getString("firstName");
                String apellido = rs.getString("lastName");
                String email = rs.getString("email");
                String passwd = rs.getString("password");
                LocalDate bithdate= rs.getDate("birthday").toLocalDate();
                LocalDate dateContract = rs.getDate("contract_date").toLocalDate();
                String numSS = rs.getString("social_security");
                UserType tipoUser = UserType.valueOf(rs.getString("user_type"));
                //obtenemos Departamento
                int idDepartamento = rs.getInt("department");
                String nombreDepartamento = rs.getString("nombre_depar");
                //obtenemos Empresa
                String nifEmpresa = rs.getString("nif");
                String nombreEmpresa = rs.getString("nombre_empre");
                String direccion = rs.getString("direccion");

                Empresa empresa = new Empresa(nifEmpresa, nombreEmpresa, direccion);
                Departamento departamentoTem = new Departamento(idDepartamento,nombreDepartamento,empresa);

                Usuario u = new Usuario(dni, nombre, apellido, email, passwd, bithdate,dateContract, numSS,tipoUser, departamentoTem);

                int id = rs.getInt("id");
                LocalDate fechaActual = rs.getDate("fecha_actual").toLocalDate();
                LocalTime horaEntrada = rs.getTime("hora_entrada").toLocalTime();
                LocalTime horaSalida = rs.getTime("hora_salida").toLocalTime();
                boolean modificado = rs.getBoolean("modificado");

                Jornada jornada = new Jornada(u,fechaActual,horaEntrada,horaSalida);
                jornada.setId(id);
                jornada.setModificado(modificado);

                listaHorasJornadas.add(jornada);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return listaHorasJornadas;
    }

    @Override
    public boolean addJornada(Jornada jornada) throws JornadaExeception {
        int filas = 0;
        this.sentenciaSQL = "INSERT INTO jornada VALUES (?,?,?,?,?)";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1,jornada.getIdTrabajador().getDni());
            preStat.setDate(2, Date.valueOf(jornada.getFechaActual()));
            preStat.setTime(3, Time.valueOf(jornada.getHoraEntrada()));
            preStat.setTime(4, Time.valueOf(jornada.getHoraSalida()));
            preStat.setBoolean(5, jornada.isModificado());
            filas = preStat.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return filas > 0;
    }

    @Override
    public boolean addEntradaJornada(Jornada jornada) throws JornadaExeception {
        int filas = 0;
        // Insertar solo la entrada, salida en NULL
        this.sentenciaSQL = "INSERT INTO jornada(id_trabajador,fecha_actual , hora_entrada, hora_salida, modificado) VALUES (?, ?, ?, NULL, false)";

        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL,
                Statement.RETURN_GENERATED_KEYS)) {
            preStat.setString(1, jornada.getIdTrabajador().getDni());
            preStat.setDate(2, Date.valueOf(jornada.getFechaActual()));
            preStat.setTime(3, Time.valueOf(jornada.getHoraEntrada()));

            filas = preStat.executeUpdate();

            // Opcional: Obtener el ID generado
            if(filas > 0) {
                ResultSet rs = preStat.getGeneratedKeys();
                if(rs.next()) {
                    int idGenerado = rs.getInt(1);
                    System.out.println("Entrada registrada con ID: " + idGenerado);
                }
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new JornadaExeception("Error al registrar entrada: " + e.getMessage());
        }

        return filas > 0;
    }

    @Override
    public boolean addSalidaJornada(Jornada jornada) throws JornadaExeception {
        int filas = 0;
        // Actualizar el último registro sin salida de ese día
        this.sentenciaSQL = "UPDATE jornada SET hora_salida = ?, modificado = false " +
                "WHERE id_trabajador = ? AND fecha_actual = ? " +
                "AND hora_salida IS NULL ORDER BY hora_entrada DESC " +
                "LIMIT 1";

        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)) {
            preStat.setTime(1, Time.valueOf(jornada.getHoraSalida()));
            preStat.setString(2, jornada.getIdTrabajador().getDni());
            preStat.setDate(3, Date.valueOf(jornada.getFechaActual()));

            filas = preStat.executeUpdate();

            if(filas == 0) {
                System.out.println("No hay ninguna entrada registrada");
            }
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new JornadaExeception("Error al registrar salida: " + e.getMessage());
        }

        return filas > 0;
    }

    @Override
    public boolean updateJornada(Jornada jornada) throws JornadaExeception {
        int filas = 0;
        this.sentenciaSQL = "UPDATE jornada SET fecha_actual=?, hora_entrada=?, hora_salida=?, modificado=? " +
                "WHERE id=? AND id_trabajador=?";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setDate(1, Date.valueOf(jornada.getFechaActual()));
            preStat.setTime(2, Time.valueOf(jornada.getHoraEntrada()));
            preStat.setTime(3, Time.valueOf(jornada.getHoraSalida()));
            preStat.setBoolean(4, true);
            preStat.setInt(5, jornada.getId());
            preStat.setString(6, jornada.getIdTrabajador().getDni());
            filas = preStat.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return filas > 0;
    }

    @Override
    public boolean deleteJornada(int id, String dniUsuario) throws JornadaExeception {
        int filas = 0;
        this.sentenciaSQL = "DELETE FROM jornada WHERE id=? AND id_trabajador=?";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setInt(1, id);
            preStat.setString(2, dniUsuario);
            filas = preStat.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return filas > 0;
    }

    @Override
    public int lastId() throws JornadaExeception {
        int id = 0;
        this.sentenciaSQL = "SELECT MAX(id) as max_id FROM jornadas";

        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            ResultSet rs = preStat.executeQuery();
            while(rs.next()){
                id = rs.getInt("max_id");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return id;
    }
}
