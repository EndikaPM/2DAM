package Model.Repository.Impl;

import Model.Departamento.Departamento;
import Model.Empresa.Empresa;
import Model.Repository.DepartamentoRepository;
import Model.Repository.DepartemantoException;

import java.sql.*;
import java.util.ArrayList;

public class DepartamentoRepositoryImpl implements DepartamentoRepository {
    private Connection con;
    private String sentenciaSQL;
    private int filas;
    private ArrayList<Departamento> listaDepartamentos;
    private Departamento departamento;

    public DepartamentoRepositoryImpl() throws SQLException, ClassNotFoundException {
        con = Connexion.conectar();
    }

    @Override
    public ArrayList<Departamento> getListDepartamentos() throws DepartemantoException {
        this.sentenciaSQL = "SELECT d.*, e.* FROM departamento d INNER JOIN empresa e ON d.id_empresa = e.nif";
        this.listaDepartamentos = new ArrayList<>();
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            ResultSet resultSet = preStat.executeQuery();
            while (resultSet.next()) {
                int idDepartamento = resultSet.getInt("id");
                String nombreDepartamento = resultSet.getString("nombre_depar");
                String id_empresa = resultSet.getString("id_empresa");

                String nif = resultSet.getString("nif");
                String nombre = resultSet.getString("nombre_empre");
                String direccion = resultSet.getString("direccion");

                Empresa empresa = new Empresa(nif, nombre, direccion);

                departamento = new Departamento(idDepartamento, nombreDepartamento,empresa);
                listaDepartamentos.add(departamento);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return listaDepartamentos;
    }

    @Override
    public boolean addDepartamento(Departamento departamento) throws DepartemantoException {
        this.sentenciaSQL = "INSERT INTO departamento VALUES(?,?,?)";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setInt(1, departamento.getId());
            preStat.setString(2, departamento.getNombre());
            preStat.setString(3, departamento.getId_empresa().getNifEmpresa());

            this.filas = preStat.executeUpdate();//executeUpdate devuelve un int con el número de filas afectadas.
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas > 0;
    }

    @Override
    public boolean updateDepartamento(Departamento departamento) throws DepartemantoException {
        this.sentenciaSQL = "UPDATE departamento SET nombre= ?, id_empresa= ? WHERE id= ?";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1, departamento.getNombre());
            preStat.setString(2, departamento.getId_empresa().getNifEmpresa());
            preStat.setInt(3, departamento.getId());

            this.filas = preStat.executeUpdate();//executeUpdate devuelve un int con el número de filas afectadas.
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas > 0;
    }

    @Override
    public boolean deleteDepartemento(int idDepartamento) throws DepartemantoException {
        this.sentenciaSQL = "DELETE FROM departamento WHERE id= ?";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setInt(1, idDepartamento);

            this.filas = preStat.executeUpdate();//executeUpdate devuelve un int con el número de filas afectadas.
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas >0;
    }
}
