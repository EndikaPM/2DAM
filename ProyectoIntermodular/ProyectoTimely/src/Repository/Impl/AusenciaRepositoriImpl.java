package Repository.Impl;

import Model.Ausencia.Ausencia;
import Model.Departamento.Departamento;
import Repository.Exception.AusenciaException;
import Repository.Interface.AusenciaRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class AusenciaRepositoriImpl implements AusenciaRepository {
    private Connection con;
    private String sentenciaSQL;
    private ArrayList<Departamento> listaDepartamentos;
    private Departamento departamento;

    public AusenciaRepositoriImpl() throws SQLException, ClassNotFoundException{
        con = Connexion.conectar();
    }

    @Override
    public ArrayList<AusenciaRepository> getListAusencias() throws AusenciaException {
        return null;
    }

    @Override
    public boolean addAusencia(Ausencia ausencia) throws AusenciaException {
        return false;
    }

    @Override
    public boolean updateAusencia(Ausencia ausencia) throws AusenciaException {
        return false;
    }

    @Override
    public boolean deleteAusecia(Ausencia ausencia) throws AusenciaException {
        return false;
    }
}
