package Model.Departamento;

import Repository.Impl.DepartamentoRepositoryImpl;
import Repository.Interface.DepartamentoRepository;
import Repository.Exception.DepartemantoException;

import java.util.ArrayList;

public class DepartamentoModel implements DepartamentoRepository{
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
