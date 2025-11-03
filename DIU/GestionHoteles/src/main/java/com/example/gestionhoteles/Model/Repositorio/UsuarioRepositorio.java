package com.example.gestionhoteles.Model.Repositorio;

import com.example.gestionhoteles.Model.UsuarioVO;

import java.util.ArrayList;

public interface UsuarioRepositorio {
    ArrayList<UsuarioVO> ObtenerListaUsuario() throws ExceptionUsuario;

    void addUsuario(UsuarioVO usuarioVO) throws ExceptionUsuario;
    void deleteUsuario(String usuarioDni) throws ExceptionUsuario;
    void updateUsuario(UsuarioVO usuarioVO) throws ExceptionUsuario;
}
