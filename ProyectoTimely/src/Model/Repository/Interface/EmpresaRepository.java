package Model.Repository.Interface;

import Model.Empresa.Empresa;
import Model.Repository.Exception.EmpresaException;

import java.util.ArrayList;

public interface EmpresaRepository {
    ArrayList<Empresa> getListEmpresa() throws EmpresaException;
    boolean addEmpresa(Empresa empresa) throws EmpresaException;
    boolean updateEmpresa(Empresa empresa) throws EmpresaException;
    boolean deleteEmpresa(String EmpresaId) throws EmpresaException;
}
