package Model.Repositori;

import Model.ExceptionUsuario;
import Model.UsuarioVO;

import java.util.ArrayList;

public interface usuarioRepository {
    ArrayList<UsuarioVO> ObtenerListaPersona() throws ExceptionUsuario;

    void addPersona(UsuarioVO persona) throws ExceptionUsuario;
    void deletePersona(int idPersona) throws ExceptionUsuario;
    void updatePersona(UsuarioVO persona) throws ExceptionUsuario;
    int lastId() throws ExceptionUsuario;
}
