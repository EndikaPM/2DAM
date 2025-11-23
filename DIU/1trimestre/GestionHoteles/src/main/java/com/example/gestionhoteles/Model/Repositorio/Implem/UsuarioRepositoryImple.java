package com.example.gestionhoteles.Model.Repositorio.Implem;

import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.UsuarioRepository;
import com.example.gestionhoteles.Model.Usuario.UsuarioVO;

import java.sql.*;
import java.util.ArrayList;

/**
 * Implementación del repositorio de usuarios para la gestión de hoteles.
 * Esta clase maneja todas las operaciones CRUD relacionadas con los usuarios
 * en la base de datos.
 *
 * @author Endika Perez
 * @version 1.0
 */

public class UsuarioRepositoryImple implements UsuarioRepository {
    /** Conexión a la base de datos */
    private final ConexionJDBC conexion = new ConexionJDBC();
    /** Statement preparado para consultas parametrizadas */
    private PreparedStatement statm;
    /** Sentencia SQL a ejecutar */
    private String sentencia;
    /** Lista de usuarios obtenidos de la base de datos */
    private ArrayList<UsuarioVO> listaUsuario;
    /** Usuario individual */
    private UsuarioVO usuario;
    /** Statement para consultas no parametrizadas */
    private Statement statm2;


    /**
     * Obtiene la lista completa de usuarios desde la base de datos.
     *
     * @return ArrayList con todos los usuarios registrados
     * @throws ExceptionUsuario si ocurre un error al acceder a la base de datos
     */
    @Override
    public ArrayList<UsuarioVO> ObtenerListaUsuario() throws ExceptionUsuario {
        try{
            Connection conex = this.conexion.conectarBD();
            this.listaUsuario = new ArrayList<>();
            this.sentencia = "SELECT * FROM usuario";
            this.statm = conex.prepareStatement(sentencia);
            ResultSet rs = this.statm.executeQuery(this.sentencia);

            while (rs.next()){
                String dni = rs.getString("dni");
                String nomb = rs.getString("nombre");
                String apell = rs.getString("apellido");
                String direcc = rs.getString("direccion");
                String local = rs.getString("localida");
                String provin = rs.getString("provincia");
                int codPostal = rs.getInt("codigo_postal");

                this.usuario = new UsuarioVO(dni, nomb, apell, direcc, local, provin, codPostal);
                this.listaUsuario.add(usuario);
            }
            this.conexion.desconectarBD(conex);
            return this.listaUsuario;
        } catch (SQLException e) {
            throw new ExceptionUsuario("Error al obtener lista de personas"+e.getMessage());
        }
    }
    /**
     * Añade un nuevo usuario a la base de datos.
     *
     * @param u objeto UsuarioVO con los datos del usuario a añadir
     * @throws ExceptionUsuario si ocurre un error durante la inserción
     */
    @Override
    public void addUsuario(UsuarioVO u) throws ExceptionUsuario {
        try{
            Connection conn = this.conexion.conectarBD();
            this.sentencia = "INSERT INTO usuario (dni, nombre, apellido, direccion, localida, provincia, codigo_postal) VALUES (?, ?, ?, ?, ?, ?, ?)";
            this.statm = conn.prepareStatement(this.sentencia);
            this.statm.setString(1, u.getDni());
            this.statm.setString(2, u.getNombre());
            this.statm.setString(3, u.getApellido());
            this.statm.setString(4, u.getDireccion());
            this.statm.setString(5, u.getLocalidad());
            this.statm.setString(6, u.getProvincia());
            this.statm.setInt(7, u.getCodigoPostal());

            this.statm.executeUpdate();
            this.statm.close();
            this.conexion.desconectarBD(conn);
        }catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ExceptionUsuario("Error al obtener lista de personas");
        }
    }

    /**
     * Elimina un usuario de la base de datos mediante su DNI.
     *
     * @param dniUsuario DNI del usuario a eliminar
     * @throws ExceptionUsuario si no se puede realizar la eliminación
     */
    @Override
    public void deleteUsuario(String dniUsuario) throws ExceptionUsuario {
        try {
            Connection conn = this.conexion.conectarBD();
            this.sentencia = "DELETE FROM usuario WHERE dni = ?";
            this.statm = conn.prepareStatement(this.sentencia);
            this.statm.setString(1, dniUsuario);

            this.statm.executeUpdate();
            this.statm.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExceptionUsuario("No se ha podido realizar la eliminación");
        }
    }
    /**
     * Actualiza los datos de un usuario existente en la base de datos.
     * El DNI se utiliza como identificador y no puede ser modificado.
     *
     * @param userVO objeto UsuarioVO con los nuevos datos del usuario
     * @throws ExceptionUsuario si no se puede realizar la modificación
     */
    @Override
    public void updateUsuario(UsuarioVO userVO) throws ExceptionUsuario {
        try {
            Connection conn = this.conexion.conectarBD();
            this.sentencia = "UPDATE usuario SET nombre = ?, apellido = ?, direccion = ?, localida = ?, provincia = ?, codigo_postal = ? WHERE dni = ?";
            this.statm = conn.prepareStatement(this.sentencia);
            this.statm.setString(1, userVO.getNombre());
            this.statm.setString(2, userVO.getApellido());
            this.statm.setString(3, userVO.getDireccion());
            this.statm.setString(4, userVO.getLocalidad());
            this.statm.setString(5, userVO.getProvincia());
            this.statm.setInt(6, userVO.getCodigoPostal());
            this.statm.setString(7, userVO.getDni());

            this.statm.executeUpdate();
            this.statm.close();
            this.conexion.desconectarBD(conn);
        } catch (Exception e) {
            System.out.println("Error aqui "+e.getMessage());
            throw new ExceptionUsuario("No se ha podido realizar la modificiación");
        }
    }
    /**
     * Obtiene un usuario específico por su DNI, usado principalmente para reservas.
     *
     * @param dni DNI del usuario a buscar
     * @return UsuarioVO con los datos del usuario encontrado, o null si no existe
     * @throws ExceptionUsuario si ocurre un error durante la búsqueda
     */
    public UsuarioVO obtenerUsuarioReserva(String dni) throws ExceptionUsuario {
        UsuarioVO usuario = null;

        try {
            Connection conex = this.conexion.conectarBD();
            this.statm2 = conex.createStatement();
            this.sentencia = String.format("SELECT * FROM usuario WHERE dni = '%s'", dni);
            ResultSet rs = this.statm2.executeQuery(this.sentencia);

            while (rs.next()){
                String dniUsuario = rs.getString("dni");
                String nombreUsuario = rs.getString("nombre");
                String apellidoUsuario = rs.getString("apellido");
                String direccionUsuario = rs.getString("direccion");
                String localidadeUsuario = rs.getString("localida");
                String provincia = rs.getString("provincia");
                Integer codigoPostal = Integer.parseInt(rs.getString("codigo_postal"));
                usuario = new UsuarioVO(dniUsuario,nombreUsuario,apellidoUsuario,direccionUsuario,localidadeUsuario,provincia,codigoPostal);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    /**
     * Busca un usuario en la base de datos utilizando su DNI.
     *
     * @param dni DNI del usuario a buscar
     * @return UsuarioVO con los datos del usuario, o null si no se encuentra
     * @throws ExceptionUsuario si ocurre un error durante la búsqueda
     */
    @Override
    public UsuarioVO buscarPorDni(String dni) throws ExceptionUsuario {
        try {
            Connection conn = conexion.conectarBD();
            String sql = "SELECT * FROM usuario WHERE dni = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, dni);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                UsuarioVO usuario = new UsuarioVO(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getString("localida"),
                        rs.getString("provincia"),
                        rs.getInt("codigo_postal")
                );

                conexion.desconectarBD(conn);
                return usuario;
            }

            conexion.desconectarBD(conn);
            return null;

        } catch (SQLException e) {
            throw new ExceptionUsuario("Error buscando usuario por DNI");
        }
    }

}
