package Model.Repositori.implement;

import Model.ExceptionUsuario;
import Model.Repositori.usuarioRepository;
import Model.Usuario;
import Model.UsuarioVO;

import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioRepositoryImple implements usuarioRepository {

    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement statm;
    private String sentencia;
    private ArrayList<UsuarioVO> listaUsuario;
    private Usuario usuario;

    @Override
    public ArrayList<UsuarioVO> ObtenerListaPersona() throws ExceptionUsuario {
        return null;
    }

    @Override
    public void addPersona(UsuarioVO persona) throws ExceptionUsuario {

    }

    @Override
    public void deletePersona(int idPersona) throws ExceptionUsuario {

    }

    @Override
    public void updatePersona(UsuarioVO persona) throws ExceptionUsuario {

    }

    @Override
    public int lastId() throws ExceptionUsuario {
        return 0;
    }
}
