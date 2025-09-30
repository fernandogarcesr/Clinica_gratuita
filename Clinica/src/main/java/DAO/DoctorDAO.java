/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionJDBC;
import DTO.DoctorDTO;
import Dominios.DoctorDominio;
import Interfaces.IDoctorDAO;

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
public class DoctorDAO implements IDoctorDAO {

    @Override
    public boolean insert(DoctorDTO doctor) {
        String sql= """
                    INSERT INTO doctores(nombre, especialidad, telefono, email)
                    VALUES (?,?,?,?);
                    """;
        
        try(Connection cn= ConexionJDBC.getConnection();
            PreparedStatement ps= cn.prepareCall(sql)) {
            
            ps.setString(1, doctor.getNombre());
            ps.setString(2, doctor.getEspecialidad());
            ps.setString(3, doctor.getTelefono());
            ps.setString(4, doctor.getEmail());
            
            int filasAfectadas = ps.executeUpdate();
            
            
            return filasAfectadas>0;
            
            
            
            
            
        } catch (SQLException e) {
            System.out.println("no se pudo insertar el doctor");
        }
        return false;
        
        
        
        
    }

    @Override
    public boolean delete(int id) {
        String sql = """
                   DELETE FROM doctores WHERE id_doctores = ?;
                   """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            System.out.println("se elimino correctamente");
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("no se elimino el doctor");
            return false;
        }
    }

    @Override
    public List<DoctorDominio> readall() {
        String sql = """
                    SELECT * FROM doctores;
                    """;
        List<DoctorDominio> lista= new LinkedList<>();
        
                
                
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_doctor= rs.getInt(1);
                String nombre = rs.getString(2);
                String especialidad= rs.getString(3);
                String telefono = rs.getString(4);
                String email = rs.getString(5);
                
                DoctorDominio doctor= new DoctorDominio(id_doctor, nombre, especialidad, telefono, email);
                lista.add(doctor);
                
            }
            return lista;


        } catch (Exception e) {
            System.out.println("no se pudo consultar los doctores");
            return lista;
        }
        
    }

    @Override
    public boolean update(int id,DoctorDominio dominio) {
        
        String sql = """
                    UPDATE doctores 
                    SET nombre=? ,especialidad=? ,telefono=?, email=?
                    WHERE id_doctores = ?;
                    """;
        try (Connection con = ConexionJDBC.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setString(1, dominio.getNombre());
            ps.setString(2, dominio.getEspecialidad());
            ps.setString(3, dominio.getTelefono());
            ps.setString(4, dominio.getEmail());
            ps.setInt(5, dominio.getId_doctor());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("no se pudo actualizar al doctor: " + e.getMessage());
            return false;
        }

    }
    

    @Override
    public DoctorDominio buscarId(int id) {
        String sql = """
                    SELECT * FROM doctores WHERE id_doctores=?; 
                    """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idDoctor= rs.getInt(1);
                String nombre=rs.getString(2);
                String especialidad=rs.getString(3);
                String telefono=rs.getString(4);
                String email=rs.getString(5);
                
                DoctorDominio doctor= new DoctorDominio(idDoctor, nombre, especialidad, telefono, email);
                
                return doctor;
                
                
            }

        } catch (SQLException e) {
            System.out.println("no se pudo encontrar el doctor id: " + id);
            return null;
        }
        return null;

    }

    @Override
    public DoctorDominio buscarDoctor(DoctorDTO doctor) {
        String sql= """
                    SELECT * FROM doctores where email=?;
                    """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setString(1,doctor.getEmail());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int idDoctor= rs.getInt(1);
                String nombre=rs.getString(2);
                String especialidad=rs.getString(3);
                String telefono=rs.getString(4);
                String email=rs.getString(5);
                
                DoctorDominio doctorDominio= new DoctorDominio(idDoctor, nombre, especialidad, telefono, email);
                
                
                return doctorDominio;
            }

        } catch (SQLException e) {
            System.out.println("no se pudo encontrar el paciente: " +doctor);
            return null;
        }
        return null;
    }
    
}
