package Model.Repository.Impl;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Timely";
    private static final String USER = "usuario";//Usar root solo como alternativa
    private static final String PASS = "usuario";

    public static Connection conectar() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
