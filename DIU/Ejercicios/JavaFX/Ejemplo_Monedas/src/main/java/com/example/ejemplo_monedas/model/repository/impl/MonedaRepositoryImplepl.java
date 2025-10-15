package com.example.ejemplo_monedas.model.repository.impl;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.ConexionJDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MonedaRepositoryImplepl  implements MonedaRepository {
    private final Modelo.repository.impl.ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<MonedaVO> monedas;
    private MonedaVO moneda;

    public ArrayList<MonedaVO> ObtenerListaMonedas() throws ExcepcionMoneda {
        try {
            Connection conn = this.conexion.conectarBD();
            this.monedas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM monedas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while(rs.next()) {
                String m = rs.getString("nombre");
                float mul = rs.getFloat("multiplicador");
                Integer codigo = rs.getInt("codigo");
                this.moneda = new MonedaVO(m, mul);
                this.moneda.setCodigo(codigo);
                this.monedas.add(this.moneda);
            }

            this.conexion.desconectarBD(conn);
            return this.monedas;
        } catch (SQLException var6) {
            throw new ExcepcionMoneda("No se ha podido realizar la operación");
        }
    }

    public void addMoneda(MonedaVO m) throws ExcepcionMoneda {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO monedas (nombre, multiplicador) VALUES ('" + m.getNombre() + "','" + m.getMultiplicador() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            throw new ExcepcionMoneda("No se ha podido realizar la operación");
        }
    }

    public void deleteMoneda(Integer idMoneda) throws ExcepcionMoneda {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM monedas WHERE codigo = %d", idMoneda);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            throw new ExcepcionMoneda("No se ha podido relaizr la eliminación");
        }
    }

    public void editMoneda(MonedaVO monedaVO) throws ExcepcionMoneda {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE monedas SET nombre = '%s', multiplicador = '%s' WHERE codigo = %d", monedaVO.getNombre(), monedaVO.getMultiplicador(), monedaVO.getCodigo());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            throw new ExcepcionMoneda("No se ha podido relaizr la edición");
        }
    }

    public int lastId() throws ExcepcionMoneda {
        int lastMonedaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT codigo FROM monedas ORDER BY codigo DESC LIMIT 1"); registro.next(); lastMonedaId = registro.getInt("codigo")) {
            }

            return lastMonedaId;
        } catch (SQLException var5) {
            throw new ExcepcionMoneda("No se ha podido realizar la busqueda del ID");
        }
    }
}
