package com.example.gestionhoteles.Model.Repositorio.Implem;

import com.example.gestionhoteles.Model.Repositorio.ExceptionUsuario;
import com.example.gestionhoteles.Model.Repositorio.UsuarioRepositorio;
import com.example.gestionhoteles.Model.UsuarioVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioRepositoryImple implements UsuarioRepositorio {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement statm;
    private String sentencia;
    private ArrayList<UsuarioVO> listaUsuario;
    private UsuarioVO usuario;

    @Override
    public ArrayList<UsuarioVO> ObtenerListaUsuario() throws ExceptionUsuario {
        try{
            Connection conex = this.conexion.conectarBD();
            this.listaUsuario = new ArrayList<>();
            this.statm = conex.createStatement();
            this.sentencia = "SELECT * FROM usuario";
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
            this.statm = conn.createStatement();
            this.sentencia = "INSERT INTO usuario (dni, nombre,apellido, direccion,  localida , provincia, codigo_postal) VALUES ('" +
                    u.getDni() + "','" +
                    u.getNombre() + "','" +
                    u.getApellido() + "','" +
                    u.getDireccion() + "','" +
                    u.getLocalidad() + "','" +
                    u.getProvincia() + "','" +
                    u.getCodigoPostal() + "')";


            this.statm.executeUpdate(this.sentencia);
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
            this.statm = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM usuario WHERE dni = %d", dniUsuario);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExceptionUsuario("No se ha podido realizar la eliminación");
        }
    }

    @Override
    public void updateUsuario(UsuarioVO userVO) throws ExceptionUsuario {
        try {
            Connection conn = this.conexion.conectarBD();
            this.statm = conn.createStatement();
            String usuario = String.format("UPDATE usuario SET dni = '%s', nombre = '%s', apellido = '%s', direccion = '%s', localida = '%s', " +
                            "provincia = '%s'+ codigo_postal = '%s' WHERE id = %d", userVO.getDni(), userVO.getNombre(), userVO.getApellido(),
                    userVO.getDireccion(),userVO.getLocalidad(), userVO.getProvincia(), userVO.getCodigoPostal(), userVO.getDireccion());


            this.statm.executeUpdate(usuario);
        } catch (Exception e) {
            throw new ExceptionUsuario("No se ha podido realizar la modificiación"+ e.getMessage());
        }
    }

}
