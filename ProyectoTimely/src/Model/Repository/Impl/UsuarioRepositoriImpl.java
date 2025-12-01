package Model.Repository.Impl;

import Model.Departamento.Departamento;
import Model.Empresa.Empresa;
import Model.Repository.UsuarioExcepcion;
import Model.Repository.UsuarioRespository;
import Model.UserType;
import Model.Usuario.Usuario;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioRepositoriImpl implements UsuarioRespository {
    private Connection con;
    private String sentenciaSQL;
    private int filas;
    private ArrayList<Usuario> listUsuarios;
    private Usuario usuario;

    public UsuarioRepositoriImpl() throws SQLException, ClassNotFoundException {
        con = Connexion.conectar();
    }

    @Override
    public ArrayList<Usuario> getListUsuarios() throws UsuarioExcepcion {
        this.sentenciaSQL = "SELECT u.*, d.*, e.* FROM usuario u INNER JOIN departamento d ON u.departamento = d.id" +
                "INNER JOIN empresa e ON e.nif = d.id_empresa";
        this.listUsuarios = new ArrayList<>();
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            ResultSet resultSet = preStat.executeQuery();
            while (resultSet.next()) {
                String dni = resultSet.getString("dni");
                String nombre = resultSet.getString("firstName");
                String apellido = resultSet.getString("lastName");
                String email = resultSet.getString("email");
                String passwd = resultSet.getString("password");
                LocalDate bithdate= resultSet.getDate("birthDate").toLocalDate();
                LocalDate dateContract = resultSet.getDate("dateContract").toLocalDate();
                String numSS = resultSet.getString("social_security");
                UserType tipoUser = resultSet.getObject("user_type", UserType.class);

                //Necesito todos estos datos para crear un departamento asociado a una empresa que está asociada a un Usuario
                int idDepartamento = resultSet.getInt("departamento");
                String nombreDepartamento = resultSet.getString("nombre");
                int idEmpresa = resultSet.getInt("id_empresa");

                String nifEmpresa = resultSet.getString("nif");
                String nombreEmpresa = resultSet.getString("nombre");
                String direccion = resultSet.getString("email");

                Empresa empresa = new Empresa(nifEmpresa, nombreEmpresa, direccion);
                Departamento departamentoTem = new Departamento(idDepartamento,nombreEmpresa,empresa);


                usuario = new Usuario(dni, nombre, apellido,email, passwd, bithdate,dateContract,numSS,tipoUser,departamentoTem);
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return listUsuarios;
    }

    @Override
    public boolean addUsuario(Usuario usuario) throws UsuarioExcepcion {
        this.sentenciaSQL = "INSERT INTO usuario VALUES(?,?,?,?,?,?,?,?,?,?)";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1, usuario.getDni());
            preStat.setString(2, usuario.getNombre());
            preStat.setString(3, usuario.getApellido());
            preStat.setString(4, usuario.getEmail());
            preStat.setString(5, usuario.getPassword());
            preStat.setDate(6, Date.valueOf(usuario.getFechaNacimiento()));
            preStat.setDate(7, Date.valueOf(usuario.getFechaContrato()));
            preStat.setString(8,usuario.getSs());
            preStat.setString(9,usuario.getUserType().toString());
            preStat.setInt(10, usuario.getDepartamento().getId());
            this.filas = preStat.executeUpdate();//executeUpdate devuelve un int con el número de filas afectadas.
        }catch(SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas > 0;
    }

    @Override
    public boolean updateUsuario(Usuario usuario) throws UsuarioExcepcion {
        this.sentenciaSQL = "UPDATE usuario SET firstName= ?, lastName= ?, email= ?, password= ?, birthday= ?," +
                "contract_date= ?, social_security= ?,user_type= ?, departamento= ? WHERE dni= ?";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1, usuario.getNombre());
            preStat.setString(2, usuario.getApellido());
            preStat.setString(3, usuario.getEmail());
            preStat.setString(4, usuario.getPassword());
            preStat.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));
            preStat.setDate(6, Date.valueOf(usuario.getFechaContrato()));
            preStat.setString(7, usuario.getSs());
            preStat.setString(8,usuario.getUserType().toString());
            preStat.setInt(9,usuario.getDepartamento().getId());
            preStat.setString(10,usuario.getDni());
            this.filas = preStat.executeUpdate();//executeUpdate devuelve un int con el número de filas afectadas.
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas > 0;
    }

    @Override
    public boolean deleteUsuario(String dniUsuario) throws UsuarioExcepcion {
        this.sentenciaSQL = "DELETE FROM usuario WHERE dni= ?";
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1, dniUsuario);
            this.filas = preStat.executeUpdate();//executeUpdate devuelve un int con el número de filas afectadas.
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas >0;
    }
}
