package Model.Departamento;

import Model.Repository.Interface.DepartamentoRepository;
import Model.Repository.Exception.DepartemantoException;

import java.util.ArrayList;

public class DepartamentoModel {
    private DepartamentoRepository departamentoRepository;

    public DepartamentoModel(DepartamentoRepository departamentoRepository) {this.departamentoRepository = departamentoRepository;}

    public ArrayList<Departamento> getListDepartamentos() throws DepartemantoException {
        return departamentoRepository.getListDepartamentos();
    }

    public boolean addDepartamento(Departamento departamento) throws DepartemantoException {
        return departamentoRepository.addDepartamento(departamento);
    }

    public boolean updateDepartamento(Departamento departamento) throws DepartemantoException {
        return departamentoRepository.updateDepartamento(departamento);
    }

    public boolean deleteDepartemento(int idDepartamento) throws DepartemantoException {
        return departamentoRepository.deleteDepartemento(idDepartamento);
    }
}
