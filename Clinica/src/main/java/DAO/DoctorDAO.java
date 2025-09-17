/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionJDBC;
import DTO.DoctoresDTO;
import Dominios.DoctoresDominio;
import Interfaces.IDoctoresDAO;
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
public class DoctorDAO implements IDoctoresDAO {

    @Override
    public boolean insert(DoctoresDTO doctor) {
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
    public List<DoctoresDominio> readall() {
        String sql = """
                    SELECT * FROM doctores;
                    """;
        List<DoctoresDominio> lista= new LinkedList<>();
        
                
                
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_doctor= rs.getInt(1);
                String nombre = rs.getString(2);
                String especialidad= rs.getString(3);
                String telefono = rs.getString(4);
                String email = rs.getString(5);
                
                DoctoresDominio doctor= new DoctoresDominio(id_doctor, nombre, especialidad, telefono, email);
                lista.add(doctor);
                
            }
            return lista;


        } catch (Exception e) {
            System.out.println("no se pudo consultar los doctores");
            return lista;
        }
        
    }

    @Override
    public boolean update(DoctoresDTO doctor) {
        
        String sql = """
                    UPDATE doctores 
                    SET nombre=? ,especialidad=? ,telefono=?
                    WHERE email = ?;
                    """;
        try (Connection con = ConexionJDBC.getConnection(); PreparedStatement ps = con.prepareCall(sql)) {
            ps.setString(1, doctor.getNombre());
            ps.setString(2, doctor.getEspecialidad());
            ps.setString(3, doctor.getTelefono());
            ps.setString(4, doctor.getEmail());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("no se pudo actualizar al doctor: " + e.getMessage());
            return false;
        }

    }
    

    @Override
    public DoctoresDominio buscarId(int id) {
        String sql = """
                    SELECT * FROM doctores WHERE id_doctores=?; 
                    """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombre=rs.getString(1);
                String especialidad=rs.getString(2);
                String telefono=rs.getString(3);
                String email=rs.getString(4);
                
                DoctoresDominio doctor= new DoctoresDominio(id, nombre, especialidad, telefono, email);
                
                return doctor;
                
                
            }

        } catch (SQLException e) {
            System.out.println("no se pudo encontrar el doctor id: " + id);
            return null;
        }
        return null;

    }
    
}
