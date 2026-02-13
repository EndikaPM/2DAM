package Model.Usuario;

import Repository.Exception.UsuarioExcepcion;
import Repository.Interface.UsuarioRespository;

import java.util.ArrayList;

public class UsuarioModel {
    private UsuarioRespository usuarioRespository;

    public UsuarioModel(UsuarioRespository usuarioRespository) {this.usuarioRespository = usuarioRespository;}

    public ArrayList<Usuario> getListUsuarios() throws UsuarioExcepcion {
        return usuarioRespository.getListUsuarios();
    }

    public boolean addUsuario(Usuario usuario) throws UsuarioExcepcion {
       return usuarioRespository.addUsuario(usuario);
    }

    public boolean updateUsuario(Usuario usuario) throws UsuarioExcepcion {
        return usuarioRespository.updateUsuario(usuario);
    }

    public boolean deleteUsuario(String dniUsuario) throws UsuarioExcepcion {
        return usuarioRespository.deleteUsuario(dniUsuario);
    }
}
