package com.example.gestionhoteles.Model.Repositorio.Implem;

import com.example.gestionhoteles.Model.RegimenAlogamiento;
import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;
import com.example.gestionhoteles.Model.ReservaVO;
import com.example.gestionhoteles.Model.TipoHabitaciones;
import com.example.gestionhoteles.Model.UsuarioVO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaRepoitoryImple implements ReservaRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement statm;
    private String sentencia;
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
                LocalDate diaLlegada = rs.getDate("dia_llegada").toLocalDate();
                LocalDate diaSalia = rs.getDate("dia_salida").toLocalDate();
                int numHabit = rs.getInt("num_habitaciones");
                String temTipoHabi = rs.getString("tipo_habitacion");
                TipoHabitaciones tipoHabit = TipoHabitaciones.valueOf(temTipoHabi);
                boolean isFum = rs.getBoolean("is_fumador");
                String temResHabit = rs.getString("Regimen_alojamiento");
                RegimenAlogamiento regHabit = RegimenAlogamiento.valueOf(temResHabit);
                String idUser = rs.getString("id_usuario");
                int id = rs.getInt("id");

                this.reserva = new ReservaVO(idUser, isFum, regHabit, tipoHabit,numHabit,diaSalia,diaLlegada);
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
            PreparedStatement ps = conexion.conectarBD().prepareStatement(sql);

            Date dateEntrada = Date.valueOf(rVO.getFechaEntrada());
            ps.setDate(1, dateEntrada);
            Date dateSalida = Date.valueOf(rVO.getFechaSalida());
            ps.setDate(2, dateSalida);
            ps.setInt(3, rVO.getNumHabitaciones());
            String tipohabita = rVO.getTipoHAbitaciones().toString();
            ps.setString(4, tipohabita);
            ps.setBoolean(5, rVO.getIsFumador());
            String regimenAlogamiento = rVO.getRegimenAlogamiento().toString();
            ps.setString(6, regimenAlogamiento);
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
    public void deleteReserva(String reservaDni) throws ExeptionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.statm = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM reserva WHERE dni = %d", reservaDni);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExeptionReserva("No se ha podido realizar la eliminación");
        }
    }

    @Override
    public void updateReserva(ReservaVO reserVO) throws ExeptionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.statm = conn.createStatement();
            String usuario = String.format("UPDATE reserva SET dni = '%s', dia_llegada = '%s', dia_salida = '%s', num_habitaciones = '%s', tipo_habitacion = '%s', " +
                            "is_fumador = '%s' , Regimen_alojamiento = '%s', id_usuario = '%s' WHERE id = %d", reserVO.getFechaEntrada(), reserVO.getFechaSalida(), reserVO.getNumHabitaciones(),
                    reserVO.getTipoHAbitaciones(),reserVO.getIsFumador(), reserVO.getRegimenAlogamiento(), reserVO.getIdUsuario());


            this.statm.executeUpdate(usuario);
        } catch (Exception e) {
            throw new ExeptionReserva("No se ha podido realizar la modificiación"+ e.getMessage());
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
}
