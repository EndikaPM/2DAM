package com.example.gestionhoteles.Model.Repositorio.Implem;

import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;
import com.example.gestionhoteles.Model.Reserva.ReservaVO;
import com.example.gestionhoteles.Model.Usuario.UsuarioVO;
import com.example.gestionhoteles.Util.DateUtil;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
/**
 * Implementación del repositorio de reservas para la gestión de hoteles.
 * Esta clase maneja todas las operaciones CRUD relacionadas con las reservas
 * en la base de datos, así como estadísticas y reportes de ocupación.
 *
 * @author Endika Perez
 * @version 1.0
 */
public class ReservaRepoitoryImple implements ReservaRepository {
    /** Conexión a la base de datos */
    private final ConexionJDBC conexion = new ConexionJDBC();
    /** Statement para consultas SQL */
    private Statement statm;
    /** Sentencia SQL a ejecutar */
    private String sentencia;
    /** Statement preparado para consultas parametrizadas */
    PreparedStatement ps;
    /** Lista de reservas obtenidas de la base de datos */
    private ArrayList<ReservaVO> listaReservas;
    /** Reserva individual */
    private ReservaVO reserva;

    /**
     * Obtiene la lista completa de reservas desde la base de datos.
     *
     * @return ArrayList con todas las reservas registradas
     * @throws ExeptionReserva si ocurre un error al acceder a la base de datos
     */

    @Override
    public ArrayList<ReservaVO> ObtenerListaReservas() throws ExeptionReserva {
        try{
            Connection conex = this.conexion.conectarBD();
            this.listaReservas = new ArrayList<>();
            this.statm = conex.createStatement();
            this.sentencia = "SELECT * FROM reserva";
            ResultSet rs = this.statm.executeQuery(this.sentencia);

            while (rs.next()){
                String diaLlegada = DateUtil.format(rs.getDate("dia_llegada").toLocalDate());
                String diaSalia = DateUtil.format(rs.getDate("dia_salida").toLocalDate());
                int numHabit = rs.getInt("num_habitaciones");
                String temTipoHabi = rs.getString("tipo_habitacion");
                //TipoHabitaciones tipoHabit = TipoHabitaciones.valueOf(temTipoHabi.toUpperCase());
                boolean isFum = rs.getBoolean("is_fumador");
                String temResHabit = rs.getString("Regimen_alojamiento");
                //RegimenAlogamiento regHabit = RegimenAlogamiento.valueOf(temResHabit);
                String idUser = rs.getString("id_usuario");
                int id = rs.getInt("id");

                this.reserva = new ReservaVO(idUser, isFum, temResHabit, temTipoHabi,numHabit,diaSalia,diaLlegada);
                this.reserva.setId(id);
                this.listaReservas.add(reserva);
            }
            this.conexion.desconectarBD(conex);
            return this.listaReservas;
        } catch (SQLException e) {
            throw new ExeptionReserva("Error al obtener lista de personas"+e.getMessage());
        }
    }

    /**
     * Añade una nueva reserva a la base de datos.
     *
     * @param rVO objeto ReservaVO con los datos de la reserva a añadir
     * @throws ExeptionReserva si ocurre un error durante la inserción
     */

    @Override
    public void addReserva(ReservaVO rVO) throws ExeptionReserva {
        try{
            String sql = "INSERT INTO reserva (dia_llegada, dia_salida, num_habitaciones, tipo_habitacion, is_fumador, Regimen_alojamiento, id_usuario, id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexion.conectarBD().prepareStatement(sql);

            Date dateEntrada = Date.valueOf(rVO.getFechaEntrada().plusDays(1));
            ps.setDate(1, dateEntrada);
            Date dateSalida = Date.valueOf(rVO.getFechaSalida().plusDays(1));
            ps.setDate(2, dateSalida);
            ps.setInt(3, rVO.getNumHabitaciones());
            //String tipohabita = rVO.getTipoHAbitaciones().toString();
            ps.setString(4, rVO.getTipoHAbitaciones());
            ps.setBoolean(5, rVO.getIsFumador());
            //String regimenAlogamiento = rVO.getRegimenAlogamiento().toString();
            ps.setString(6, rVO.getRegimenAlogamiento());
            ps.setString(7, rVO.getIdUsuario());
            ps.setInt(8, rVO.getId());

            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ExeptionReserva("Error al obtener lista de personas");
        }
    }

    /**
     * Elimina una reserva de la base de datos mediante su ID.
     *
     * @param reservaId ID de la reserva a eliminar
     * @throws ExeptionReserva si no se puede realizar la eliminación
     */

    @Override
    public void deleteReserva(int reservaId) throws ExeptionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.statm = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM reserva WHERE id = %d", reservaId);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExeptionReserva("No se ha podido realizar la eliminación");
        }
    }

    /**
     * Actualiza los datos de una reserva existente en la base de datos.
     * El ID de la reserva se utiliza como identificador.
     *
     * @param reserVO objeto ReservaVO con los nuevos datos de la reserva
     * @throws ExeptionReserva si no se puede realizar la modificación o si no se encuentra la reserva
     */

    @Override
    public void updateReserva(ReservaVO reserVO) throws ExeptionReserva {
        Connection conn = null;
        try {
            conn = this.conexion.conectarBD();

            // Usar PreparedStatement es más seguro que Statement con String.format
            String sql = "UPDATE reserva SET dia_llegada = ?, dia_salida = ?, " +
                    "num_habitaciones = ?, tipo_habitacion = ?, " +
                    "is_fumador = ?, Regimen_alojamiento = ? " +
                    "WHERE id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);

            // Convertir las fechas de String a java.sql.Date
            Date dateEntrada = Date.valueOf(reserVO.getFechaEntrada());
            ps.setDate(1, dateEntrada);

            Date dateSalida = Date.valueOf(reserVO.getFechaSalida());
            ps.setDate(2, dateSalida);

            ps.setInt(3, reserVO.getNumHabitaciones());
            ps.setString(4, reserVO.getTipoHAbitaciones());
            ps.setBoolean(5, reserVO.getIsFumador());
            ps.setString(6, reserVO.getRegimenAlogamiento());
            ps.setInt(7, reserVO.getId());

            int filasActualizadas = ps.executeUpdate();

            if (filasActualizadas == 0) {
                throw new ExeptionReserva("No se encontró la reserva con ID: " + reserVO.getId());
            }

            System.out.println("Reserva ID " + reserVO.getId() + " actualizada correctamente en BD");

            ps.close();

        } catch (SQLException e) {
            System.out.println("Error SQL: " + e.getMessage());
            e.printStackTrace();
            throw new ExeptionReserva("No se ha podido realizar la modificación: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    this.conexion.desconectarBD(conn);
                } catch (Exception e) {
                    System.out.println("Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Obtiene el ID de la última reserva registrada en la base de datos.
     * Útil para generar nuevos IDs incrementales.
     *
     * @return int con el último ID de reserva utilizado
     * @throws ExeptionReserva si no se puede realizar la búsqueda del ID
     */

    @Override
    public int lastId() throws ExeptionReserva {
        int lastMonedaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT id FROM reserva ORDER BY id DESC LIMIT 1"); registro.next(); lastMonedaId = registro.getInt("id")) {
            }

            return lastMonedaId;
        } catch (SQLException var5) {
            throw new ExeptionReserva("No se ha podido realizar la busqueda del ID");
        }
    }

    /**
     * Obtiene todas las reservas asociadas a un usuario específico mediante su DNI.
     *
     * @param otherDni DNI del usuario del cual se desean obtener las reservas
     * @return ArrayList con todas las reservas del usuario especificado
     * @throws ExeptionReserva si ocurre un error al acceder a la base de datos
     */

    public ArrayList<ReservaVO> obtenerFiltroDniReservas(String otherDni) throws ExeptionReserva{
        try{
            Connection conex = this.conexion.conectarBD();
            this.listaReservas = new ArrayList<>();
            this.statm = conex.createStatement();
            this.sentencia = String.format("SELECT * FROM reserva WHERE id_usuario = '%s'", otherDni);
            ResultSet rs = this.statm.executeQuery(this.sentencia);

            while (rs.next()){
                String diaLlegada = DateUtil.format(rs.getDate("dia_llegada").toLocalDate());
                String diaSalia = DateUtil.format(rs.getDate("dia_salida").toLocalDate());
                int numHabit = rs.getInt("num_habitaciones");
                String temTipoHabi = rs.getString("tipo_habitacion");
                boolean isFum = rs.getBoolean("is_fumador");
                String temResHabit = rs.getString("Regimen_alojamiento");
                String idUser = rs.getString("id_usuario");
                int id = rs.getInt("id");

                this.reserva = new ReservaVO(idUser, isFum, temResHabit, temTipoHabi,numHabit,diaLlegada,diaSalia);
                this.reserva.setId(id);
                this.listaReservas.add(reserva);
            }
            this.conexion.desconectarBD(conex);
            return this.listaReservas;
        } catch (SQLException e) {
            throw new ExeptionReserva("Error al obtener lista de personas"+e.getMessage());
        }
    }


    /**
     * Calcula el porcentaje de ocupación para cada tipo de habitación.
     * Considera el total de habitaciones disponibles por tipo:
     * - Doble: 20 habitaciones
     * - Doble uso individual: 80 habitaciones
     * - Junior Suite: 15 habitaciones
     * - Suite: 5 habitaciones
     *
     * @return Array de Double con los porcentajes de ocupación para cada tipo de habitación
     *         en el orden: [doble, doble_uso_individual, junior_suite, suite]
     * @throws ExeptionReserva si no se puede obtener la información de reservas
     */

    @Override
    public Double[] porcentajeReserRoom() throws ExeptionReserva {
            String[] roomType = {
                    "doble",
                    "doble_uso_individual",
                    "junior_suite",
                    "suite"
            };

            // Número total de habitaciones por tipo
            int[] totalRooms = {20, 80, 15, 5};

            Double[] percentByRoomType = new Double[4];

            try (Connection conn = this.conexion.conectarBD()) {

                String sql = "SELECT COUNT(*) AS total FROM reserva WHERE tipo_habitacion = ?";
                PreparedStatement ps = conn.prepareStatement(sql);

                for (int i = 0; i < roomType.length; i++) {

                    ps.setString(1, roomType[i]);
                    ResultSet rs = ps.executeQuery();

                    int occupied = 0;

                    if (rs.next()) {
                        occupied = rs.getInt("total");
                    }

                    // Calcular porcentaje real de ocupación:
                    // occupied / totalRooms[i]
                    percentByRoomType[i] = (double) occupied / totalRooms[i];

                    rs.close();
                }

                return percentByRoomType;

            } catch (SQLException e) {
                throw new ExeptionReserva("No se ha podido obtener la lista de reservas");
            }
    }

    /**
     * Calcula el número de reservas realizadas para cada mes del año.
     * Cuenta las reservas en función del mes de llegada (dia_llegada).
     *
     * @return Array de int con 12 posiciones, donde cada posición [0-11] representa
     *         el número de reservas para cada mes [Enero-Diciembre]
     * @throws ExeptionReserva si ocurre un error al obtener la ocupación por mes
     */

    public int[] roomOcupationMonth() throws ExeptionReserva {
        int[] meses = new int[12];

        try (Connection conn = this.conexion.conectarBD()) {
            String sql = "SELECT COUNT(*) AS total FROM reserva WHERE MONTH(dia_llegada) = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            // Iterar por cada mes (1-12)
            for (int i = 1; i <= 12; i++) {
                ps.setInt(1, i);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        meses[i - 1] = rs.getInt("total");
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ExeptionReserva("Error al obtener ocupación por mes: " + e.getMessage());
        }
        return meses;
    }
}
