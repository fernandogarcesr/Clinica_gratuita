/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionJDBC;
import DTO.PacienteDTO;
import Dominios.ENUM.Sexo;
import Dominios.PacientesDominio;
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
    public boolean insert(PacienteDTO paciente) {
        String sql = """
                     INSERT INTO Pacientes (nombre, edad, sexo, direccion, telefono, email) VALUES
                     (?, ?, ?, ?, ?, ?);
             """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql);) {
            ps.setString(1, paciente.getNombre());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getSexo().toString());
            ps.setString(4, paciente.getDireccion());
            ps.setString(5, paciente.getTelefono());
            ps.setString(6, paciente.getEmail());

            int filasAfectadas = ps.executeUpdate();

            System.out.println("paciente insertado correctamente");
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error: error al insertar un paciente");
            return false;
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
    public List<PacientesDominio> readall(){
        String sql = """
                    SELECT * FROM pacientes;
                    """;
        List<PacientesDominio> lista= new LinkedList<>();
        
                
                
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_paciente=rs.getInt(1);
                String nombre=rs.getString(2);
                int edad= rs.getInt(3);
                Sexo sexo=null;
                if (rs.getString(4).equalsIgnoreCase("Masculino")) {
                sexo= Sexo.MASCULINO;
                }
                if (rs.getString(4).equalsIgnoreCase("Femenino")) {
                sexo= Sexo.FEMENINO;
                }
                String direccion=rs.getString(5);
                String telefono=rs.getString(6);
                String email=rs.getString(7);

                PacientesDominio paciente= new PacientesDominio(id_paciente, nombre, edad, sexo, direccion, telefono, email);
                lista.add(paciente);
            }
            return lista;


        } catch (Exception e) {
            System.out.println("no se pudo consultar los pacientes");
            return lista;
        }
        

    }

    @Override
    public boolean update(PacienteDTO paciente) {

        String sql = """
                    UPDATE Pacientes 
                    SET nombre=? ,edad=? ,sexo=? ,direccion=? ,telefono=? 
                    WHERE email = ?;
                    """;
        try (Connection con = ConexionJDBC.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setString(1, paciente.getNombre());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getSexo().toString());
            ps.setString(4, paciente.getDireccion());
            ps.setString(5, paciente.getTelefono());
            ps.setString(6, paciente.getEmail());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("no se pudo actualizar al paciente: " + e.getMessage());
            return false;
        }

    }

    @Override
    public PacientesDominio buscarId(int id) {
        String sql = """
                    SELECT * FROM pacientes WHERE id_paciente=?; 
                    """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id_paciente=rs.getInt(1);
                String nombre=rs.getString(2);
                int edad= rs.getInt(3);
                Sexo sexo=null;
                if (rs.getString(4).equalsIgnoreCase("Masculino")) {
                sexo= Sexo.MASCULINO;
                }
                if (rs.getString(4).equalsIgnoreCase("Femenino")) {
                sexo= Sexo.FEMENINO;
                }
                String direccion=rs.getString(5);
                String telefono=rs.getString(6);
                String email=rs.getString(7);

                PacientesDominio paciente= new PacientesDominio(id_paciente, nombre, edad, sexo, direccion, telefono, email);
                return paciente;
            }

        } catch (SQLException e) {
            System.out.println("no se pudo encontrar el paciente id: " + id);
            return null;
        }
        return null;

    }

}
