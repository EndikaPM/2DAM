package org.example.agendafmx.Model.Repository.impl;

import org.example.agendafmx.Model.ExceptionPersona;
import org.example.agendafmx.Model.PersonVO;
import org.example.agendafmx.Model.Repository.PersonaRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonaRepositorioImpl implements PersonaRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement statm;
    private String sentencia;
    private ArrayList<PersonVO> people;
    private PersonVO person;


    @Override
    public ArrayList<PersonVO> ObtenerListaPersona() throws ExceptionPersona {
        try{
            Connection conex = this.conexion.conectarBD();
            this.people = new ArrayList<>();
            this.statm = conex.createStatement();
            this.sentencia = "SELECT * FROM Persona";
            ResultSet rs = this.statm.executeQuery(this.sentencia);

            while (rs.next()){
                String firNam = rs.getString("firstName");
                String lastNam = rs.getString("lastName");
                String street = rs.getString("streetLabel");
                String postCode = rs.getString("postalCode");
                String city = rs.getString("cityLabel");
                LocalDate bDay = rs.getDate("birthdayLabel").toLocalDate();
                String birthDay = bDay.toString();
                int id = rs.getInt("id");

                this.person = new PersonVO(firNam,lastNam,street,postCode,city,birthDay);
                this.person.setId(id);
                this.people.add(person);
            }
            this.conexion.desconectarBD(conex);
            return this.people;
        } catch (SQLException e) {
            throw new ExceptionPersona("Error al obtener lista de personas");
        }
    }

    @Override
    public void addPersona(PersonVO p) throws ExceptionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.statm = conn.createStatement();
            this.sentencia = "INSERT INTO Persona (firstName, lastName, streetLabel, postalCode, cityLabel, birthdayLabel) VALUES ('"
                    + p.getFirstName() + "','"
                    + p.getLastName() + "','"
                    + p.getStreetLabel() + "','"
                    + p.getPostalCode() + "','"
                    + p.getCity() + "','"
                    + p.getBirthDate() + "')";


            this.statm.executeUpdate(this.sentencia);
            this.statm.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            System.out.println(var3.getMessage());
            throw new ExceptionPersona("No se ha podido realizar la operación");
        }

    }

    @Override
    public void deletePersona(int idPersona) throws ExceptionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.statm = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM Persona WHERE id = %d", idPersona);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
            throw new ExceptionPersona("No se ha podido relaizr la eliminación");
        }
    }

    @Override
    public void updatePersona(PersonVO p) throws ExceptionPersona {
        try {
            Connection conn = this.conexion.conectarBD();
            this.statm = conn.createStatement();
            String sql = String.format("UPDATE Persona SET firstName = '%s', lastName = '%s', streetLabel = '%s', postalCode = '%s', cityLabel = '%s', birthdayLabel = '%s' WHERE id = %d", p.getFirstName(), p.getLastName(), p.getStreetLabel(), p.getPostalCode(), p.getCity(), p.getBirthDate(), p.getId());
            this.statm.executeUpdate(sql);
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
            throw new ExceptionPersona("No se ha podido relaizr la edición");
        }
    }

    @Override
    public int lastId() throws ExceptionPersona {
        int lastMonedaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT codigo FROM monedas ORDER BY codigo DESC LIMIT 1"); registro.next(); lastMonedaId = registro.getInt("id")) {
            }

            return lastMonedaId;
        } catch (SQLException var5) {
            throw new ExceptionPersona("No se ha podido realizar la busqueda del ID");
        }
    }
}
