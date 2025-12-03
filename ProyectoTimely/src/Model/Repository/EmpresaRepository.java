package Model.Repository;

import Model.Departamento.Departamento;
import Model.Empresa.Empresa;

import java.util.ArrayList;

public interface EmpresaRepository {
    ArrayList<Empresa> getListEmpresa() throws EmpresaException;
    boolean addEmpresa(Empresa empresa) throws EmpresaException;
    boolean updateEmpresa(Empresa empresa) throws EmpresaException;
    boolean deleteEmpresa(String EmpresaId) throws EmpresaException;
}
