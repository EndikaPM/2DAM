package com.example.gestionhoteles.Model.Repositorio;

import com.example.gestionhoteles.Model.Usuario.UsuarioVO;

import java.util.ArrayList;

public interface UsuarioRepository {
    ArrayList<UsuarioVO> ObtenerListaUsuario() throws ExceptionUsuario;

    void addUsuario(UsuarioVO usuarioVO) throws ExceptionUsuario;
    void deleteUsuario(String usuarioDni) throws ExceptionUsuario;
    void updateUsuario(UsuarioVO usuarioVO) throws ExceptionUsuario;
    UsuarioVO obtenerUsuarioReserva(String dni) throws ExceptionUsuario;
    UsuarioVO buscarPorDni(String dni) throws ExceptionUsuario;
}
