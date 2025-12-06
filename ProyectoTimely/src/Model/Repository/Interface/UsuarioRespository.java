package Model.Repository.Interface;

import Model.Repository.Exception.UsuarioExcepcion;
import Model.Usuario.Usuario;

import java.util.ArrayList;

public interface UsuarioRespository {
    ArrayList<Usuario> getListUsuarios() throws UsuarioExcepcion;
    boolean addUsuario(Usuario usuario) throws UsuarioExcepcion;
    boolean updateUsuario(Usuario usuario) throws UsuarioExcepcion;
    boolean deleteUsuario(String dniUsuario) throws UsuarioExcepcion;
}
