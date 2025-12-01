package Model.Repository;

import Model.Departamento.Departamento;

import java.util.ArrayList;

public interface DepartamentoRepository {
    ArrayList<Departamento> getListDepartamentos() throws DepartemantoException;
    boolean addDepartamento(Departamento departamento) throws DepartemantoException;
    boolean updateDepartamento(Departamento departamento) throws DepartemantoException;
    boolean deleteDepartemento(int departamento) throws DepartemantoException;
}
