/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionJDBC;
import DTO.PacienteDTO;
import Dominios.ENUM.Sexo;
import Dominios.PacienteDominio;
import Interfaces.IPacienteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class PacienteDAO implements IPacienteDAO {

    @Override
    public int insert(PacienteDTO paciente) {
        String sql = """
                     INSERT INTO Pacientes (nombre, apellido_paterno, apellido_materno, edad, sexo, direccion, telefono, email) VALUES
                     (?, ?, ?, ?, ?, ?, ?, ?);
             """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidoPaterno());
            ps.setString(3, paciente.getApellidoMaterno());
            ps.setInt(4, paciente.getEdad());
            ps.setString(5, paciente.getSexo());
            ps.setString(6, paciente.getDireccion());
            ps.setString(7, paciente.getTelefono());
            ps.setString(8, paciente.getEmail());

            int filas = ps.executeUpdate();
            if (filas == 0) {
                // No se inserto nada
                return -1;
            }
            //obtener el id generado
          
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                } else {
                    return -1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: error al insertar un paciente");
            return -1;

        }

    }

    @Override
    public boolean delete(int id) {
        String sql = """
                   DELETE FROM Pacientes WHERE id_paciente = ?;
                   """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            System.out.println("se elimino correctamente");
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("no se elimino el paciente");
            return false;
        }

    }

    @Override
    public List<PacienteDominio> readall() {
        String sql = """
                    SELECT * FROM pacientes;
                    """;
        List<PacienteDominio> lista = new LinkedList<>();

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id_paciente");
                String nombre = rs.getString("nombre");
                String apellidoP = rs.getString("apellido_paterno");
                String apellidoM = rs.getString("apellido_materno");
                int edad = rs.getInt("edad");
                String sexoStr = rs.getString("sexo");
                Sexo sexo = null;
                if ("Masculino".equalsIgnoreCase(sexoStr)) {
                    sexo = Sexo.MASCULINO;
                } else if ("Femenino".equalsIgnoreCase(sexoStr)) {
                    sexo = Sexo.FEMENINO;
                } else {
                    sexo = Sexo.OTRO;
                }

                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                PacienteDominio paciente = new PacienteDominio(
                        id, nombre, apellidoP, apellidoM, edad, sexoStr, direccion, telefono, email
                );
                lista.add(paciente);
            }
            return lista;

        } catch (Exception e) {
            System.out.println("no se pudo consultar los pacientes");
            return lista;
        }


    }

    @Override
    public boolean update(PacienteDominio paciente) {

        String sql = """
                    UPDATE Pacientes 
                    SET nombre=? ,apellido_paterno =?, apellido_materno =?, edad=? ,sexo=? ,direccion=? ,telefono=?, email=? 
                    WHERE id_paciente = ?;
                    """;
        try (Connection con = ConexionJDBC.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidoPaterno());
            ps.setString(3, paciente.getApellidoMaterno());
            ps.setInt(4, paciente.getEdad());
            ps.setString(5, paciente.getSexo());
            ps.setString(6, paciente.getDireccion());
            ps.setString(7, paciente.getTelefono());
            ps.setString(8, paciente.getEmail());
            ps.setInt(9, paciente.getId_paciente());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("no se pudo actualizar al paciente: " + e.getMessage());
            return false;
        }

    }

    @Override
    public PacienteDominio buscarId(int id) {
        String sql = """
                    SELECT * FROM pacientes WHERE id_paciente=?; 
                    """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id_paciente = rs.getInt("id_paciente");
                String nombre = rs.getString("nombre");
                String apellidoP = rs.getString("apellido_paterno");
                String apellidoM = rs.getString("apellido_materno");
                int edad = rs.getInt("edad");

                Sexo sexo = null;
                String sexoStr = rs.getString("sexo");
                if ("Masculino".equalsIgnoreCase(sexoStr)) {
                    sexo = Sexo.MASCULINO;
                } else if ("Femenino".equalsIgnoreCase(sexoStr)) {
                    sexo = Sexo.FEMENINO;
                } else {
                    sexo = Sexo.OTRO;
                }

                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                PacienteDominio paciente = new PacienteDominio(id_paciente, nombre, apellidoP, apellidoM, edad, sexoStr, direccion, telefono, email);
                return paciente;
            }

        } catch (SQLException e) {
            System.out.println("no se pudo encontrar el paciente id: " + id);
            return null;
        }
        return null;

    }

    @Override
    public PacienteDominio buscarPaciente(PacienteDTO paciente) {
        String sql= """
                    SELECT * FROM pacientes where email=?;
                    """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setString(1, paciente.getEmail());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id_paciente = rs.getInt("id_paciente");
                String nombre = rs.getString("nombre");
                String apellidoP = rs.getString("apellido_paterno");
                String apellidoM = rs.getString("apellido_materno");
                int edad = rs.getInt("edad");

                Sexo sexo = null;
                String sexoStr = rs.getString("sexo");
                if ("Masculino".equalsIgnoreCase(sexoStr)) {
                    sexo = Sexo.MASCULINO;
                } else if ("Femenino".equalsIgnoreCase(sexoStr)) {
                    sexo = Sexo.FEMENINO;
                } else {
                    sexo = Sexo.OTRO;
                }

                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String email = rs.getString("email");

                PacienteDominio pacienteDominio = new PacienteDominio(id_paciente, nombre, apellidoP, apellidoM, edad, sexoStr, direccion, telefono, email);
                
                return pacienteDominio;
            }

        } catch (SQLException e) {
            System.out.println("no se pudo encontrar el paciente: " +paciente);
            return null;
        }
        return null;

    }

}
