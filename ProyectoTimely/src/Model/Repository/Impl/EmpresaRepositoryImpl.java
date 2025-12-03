package Model.Repository.Impl;

import Model.Departamento.Departamento;
import Model.Empresa.Empresa;
import Model.Repository.EmpresaException;
import Model.Repository.EmpresaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmpresaRepositoryImpl implements EmpresaRepository {
    private Connection con;
    private String sentenciaSQL;
    private int filas;
    private ArrayList<Empresa> listaEmpresas;
    private Empresa empresa;

    public EmpresaRepositoryImpl() throws SQLException, ClassNotFoundException {
        con = Connexion.conectar();
    }
    @Override
    public ArrayList<Empresa> getListEmpresa() throws EmpresaException {
        this.sentenciaSQL = "SELECT * FROM empresa";
        listaEmpresas = new ArrayList<>();

        try (PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            ResultSet  rs = preStat.executeQuery();
            while(rs.next()){
                String nif = rs.getString("nif");
                String nombre = rs.getString("nombre_empre");
                String direccion = rs.getString("direccion");

                empresa = new Empresa(nif,nombre,direccion);

                listaEmpresas.add(empresa);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();;
        }
        return listaEmpresas;
    }

    @Override
    public boolean addEmpresa(Empresa empresa) throws EmpresaException {
        this.sentenciaSQL = "INSERT INTO empresa VALUES(?,?,?)";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1, empresa.getNifEmpresa());
            preStat.setString(2, empresa.getNomEmpresa());
            preStat.setString(3, empresa.getDireEmpresa());

            this.filas = preStat.executeUpdate();//executeUpdate devuelve un int con el número de filas afectadas.
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas > 0;
    }

    @Override
    public boolean updateEmpresa(Empresa empresa) throws EmpresaException {
        this.sentenciaSQL = "UPDATE empresa set nombre=, direccion= where nif=? (?,?,?)";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1, empresa.getDireEmpresa());
            preStat.setString(2, empresa.getNomEmpresa());
            preStat.setString(3, empresa.getNifEmpresa());

            this.filas = preStat.executeUpdate();//executeUpdate devuelve un int con el número de filas afectadas.
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas > 0;
    }

    @Override
    public boolean deleteEmpresa(String empresaNif) throws EmpresaException {
        this.sentenciaSQL = "DELETE FROM empresa WHERE nif= ?";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1, empresaNif);

            this.filas = preStat.executeUpdate();//executeUpdate devuelve un int con el número de filas afectadas.
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas >0;
    }
}
