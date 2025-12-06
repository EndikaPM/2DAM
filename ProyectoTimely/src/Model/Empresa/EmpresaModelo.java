package Model.Empresa;

import Model.Repository.Exception.EmpresaException;
import Model.Repository.Interface.EmpresaRepository;

import java.util.ArrayList;

public class EmpresaModelo {
    private EmpresaRepository  empresaRepository;

    public EmpresaModelo(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public ArrayList<Empresa> getListEmpresa() throws EmpresaException {
        return empresaRepository.getListEmpresa();
    }

    public boolean addEmpresa(Empresa empresa) throws EmpresaException {
        return empresaRepository.addEmpresa(empresa);
    }

    public boolean updateEmpresa(Empresa empresa) throws EmpresaException {
        return empresaRepository.updateEmpresa(empresa);
    }

    public boolean deleteEmpresa(String empresaNif) throws EmpresaException {
        return empresaRepository.deleteEmpresa(empresaNif);
    }
}
