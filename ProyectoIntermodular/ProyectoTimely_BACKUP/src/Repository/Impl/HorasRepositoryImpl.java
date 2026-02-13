package Repository.Impl;

import Model.Departamento.Departamento;
import Model.Empresa.Empresa;
import Model.Horas.HorasTrabajadas;
import Repository.Exception.HorasException;
import Repository.Interface.HorasRepository;
import Model.UserType;
import Model.Usuario.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HorasRepositoryImpl implements HorasRepository {
    private Connection con;
    private String sentenciaSQL;
    private ArrayList<HorasTrabajadas> listaHorasTrabajadas;
    private HorasTrabajadas horasTrabajadas;

    public HorasRepositoryImpl() throws SQLException, ClassNotFoundException {
        con = Connexion.conectar();
    }

    @Override
    public ArrayList<HorasTrabajadas> obtenerListHorasTrabajadas() throws HorasException {
        this.sentenciaSQL = "SELECT ht.*, u.*, d.*, e.* " +
                "FROM horas_trabajo ht " +
                "INNER JOIN usuario u ON u.dni = ht.id_usuario " +
                "INNER JOIN departamento d ON u.department = d.id " +
                "INNER JOIN empresa e ON e.nif = d.id_empresa ";
        this.listaHorasTrabajadas = new ArrayList<>();

        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            ResultSet rs = preStat.executeQuery();
            while(rs.next()){
                String dni = rs.getString("dni");
                String nombre = rs.getString("firstName");
                String apellido = rs.getString("lastName");
                String email = rs.getString("email");
                String passwd = rs.getString("password");
                LocalDate bithdate= rs.getDate("birthday").toLocalDate();
                LocalDate dateContract = rs.getDate("contract_date").toLocalDate();
                String numSS = rs.getString("social_security");
                UserType tipoUser = UserType.valueOf(rs.getString("user_type"));

                int idDepartamento = rs.getInt("department");
                String nombreDepartamento = rs.getString("nombre_depar");

                String nifEmpresa = rs.getString("nif");
                String nombreEmpresa = rs.getString("nombre_empre");
                String direccion = rs.getString("direccion");

                Empresa empresa = new Empresa(nifEmpresa, nombreEmpresa, direccion);
                Departamento departamentoTem = new Departamento(idDepartamento,nombreDepartamento,empresa);

                Usuario u = new Usuario(dni, nombre, apellido, email, passwd, bithdate,dateContract, numSS,tipoUser, departamentoTem);

                int horasContrato = rs.getInt("horas_contrato");
                int horasTrabajadas = rs.getInt("horas_trabajadas");

                HorasTrabajadas ht = new HorasTrabajadas(u, horasContrato);
                ht.setHorasTrabajadas(horasTrabajadas);
                listaHorasTrabajadas.add(ht);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return listaHorasTrabajadas;
    }

    @Override
    public boolean addHoras(HorasTrabajadas horasTrabajadas) throws HorasException {
        int filas = 0;
        this.sentenciaSQL = "INSERT INTO horas_trabajo VALUES (?,?,?)";

        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1, horasTrabajadas.getId_usuario().getDni());
            preStat.setFloat(2, horasTrabajadas.getHorasContrato());
            preStat.setInt(3, horasTrabajadas.getHorasTrabajadas());
            filas = preStat.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return filas > 0;
    }

    @Override
    public boolean updateHoras(HorasTrabajadas horasTrabajadas) throws HorasException {
        int filas= 0;
        this.sentenciaSQL = "UPDATE horas_trabajo SET horas_contrato=?, horas_trabajadas=?  WHERE id_usuario = ?";

        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setFloat(1, horasTrabajadas.getHorasContrato());
            preStat.setInt(2, horasTrabajadas.getHorasTrabajadas());
            preStat.setString(3, horasTrabajadas.getId_usuario().getDni());
            filas = preStat.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return  filas > 0;
    }

    @Override
    public boolean deleteHoras(String dniUsuario) throws HorasException {
        int filas = 0;
        this.sentenciaSQL = "DELETE FROM horas_trabajo WHERE dni= ?";
        
        try(PreparedStatement preStat = con.prepareStatement(sentenciaSQL)){
            preStat.setString(1, dniUsuario);
            filas = preStat.executeUpdate();
        }catch (SQLException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        return filas > 0;
    }
}
