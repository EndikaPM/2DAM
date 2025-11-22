package com.example.gestionhoteles.Model.Repositorio.Implem;

import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;
import com.example.gestionhoteles.Model.Reserva.ReservaVO;
import com.example.gestionhoteles.Model.Usuario.UsuarioVO;
import com.example.gestionhoteles.Util.DateUtil;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class ReservaRepoitoryImple implements ReservaRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement statm;
    private String sentencia;
    PreparedStatement ps;
    private ArrayList<ReservaVO> listaReservas;
    private ReservaVO reserva;

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

    @Override
    public void addReserva(ReservaVO rVO) throws ExeptionReserva {
        try{
            String sql = "INSERT INTO reserva (dia_llegada, dia_salida, num_habitaciones, tipo_habitacion, is_fumador, Regimen_alojamiento, id_usuario, id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conexion.conectarBD().prepareStatement(sql);

            Date dateEntrada = Date.valueOf(rVO.getFechaEntrada());
            ps.setDate(1, dateEntrada);
            Date dateSalida = Date.valueOf(rVO.getFechaSalida());
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
