package com.example.gestionhoteles.Model.Repositorio.Implem;

import com.example.gestionhoteles.Model.RegimenAlogamiento;
import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.ExeptionReserva;
import com.example.gestionhoteles.Model.Repositorio.ReservaRepository;
import com.example.gestionhoteles.Model.ReservaVO;
import com.example.gestionhoteles.Model.TipoHabitaciones;
import com.example.gestionhoteles.Model.UsuarioVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

                this.reserva = new ReservaVO(id,idUser, isFum, regHabit, tipoHabit,numHabit,diaSalia,diaLlegada);
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
            Connection conn = this.conexion.conectarBD();
            this.statm = conn.createStatement();
            this.sentencia = "INSERT INTO reserva (dia_llegada, dia_salida,num_habitaciones, tipo_habitacion,  is_fumador , Regimen_alojamiento, id_usuario,id) VALUES ('" +
                    rVO.getFechaEntrada() + "','" +
                    rVO.getFechaSalida() + "','" +
                    rVO.getNumHabitaciones() + "','" +
                    rVO.getTipoHAbitaciones() + "','" +
                    rVO.getIsFumador() + "','" +
                    rVO.getRegimenAlogamiento() + "','" +
                    rVO.getIdUsuario() + "','" +
                    rVO.getId() + "')";


            this.statm.executeUpdate(this.sentencia);
            this.statm.close();
            this.conexion.desconectarBD(conn);
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
    public void updateReserva(ReservaVO reservaVO) throws ExeptionReserva {
        try {
            Connection conn = this.conexion.conectarBD();
            this.statm = conn.createStatement();
            String usuario = String.format("UPDATE reserva SET dni = '%s', nombreCompleto = '%s', direccion = '%s', localidad = '%s', " +
                            "provincia = '%s' WHERE id = %d", userVO.getDni(), userVO.getNombre(), userVO.getApellido(),
                    userVO.getDireccion(),userVO.getLocalidad(), userVO.getProvincia(), userVO.getCodigoPostal());


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
