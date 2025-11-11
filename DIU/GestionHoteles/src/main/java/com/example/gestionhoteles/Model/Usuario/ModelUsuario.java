package com.example.gestionhoteles.Model.Usuario;

import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.UsuarioRepository;

import java.util.ArrayList;

public class ModelUsuario {
    private UsuarioRepository usuarioRepositorio;

    public ModelUsuario(UsuarioRepository usuarioRepositorio) {
        this.usuarioRepositorio = usuarioRepositorio;
    }

    public ArrayList<UsuarioVO> ObtenerListaUsuario() throws ExceptionUsuario{
       return usuarioRepositorio.ObtenerListaUsuario();
    }

    public void addUsuario(UsuarioVO u) throws ExceptionUsuario {
        usuarioRepositorio.addUsuario(u);
    }

    public void deleteUsuario(String dniUsuario) throws ExceptionUsuario {
        usuarioRepositorio.deleteUsuario(dniUsuario);
    }

    public void updateUsuario(UsuarioVO userVO) throws ExceptionUsuario {
        usuarioRepositorio.updateUsuario(userVO);
    }
}
