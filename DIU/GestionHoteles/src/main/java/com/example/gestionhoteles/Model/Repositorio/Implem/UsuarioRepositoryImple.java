package com.example.gestionhoteles.Model.Repositorio.Implem;

import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.UsuarioRepository;
import com.example.gestionhoteles.Model.Usuario.UsuarioVO;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioRepositoryImple implements UsuarioRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private PreparedStatement statm;
    private String sentencia;
    private ArrayList<UsuarioVO> listaUsuario;
    private UsuarioVO usuario;

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

}
