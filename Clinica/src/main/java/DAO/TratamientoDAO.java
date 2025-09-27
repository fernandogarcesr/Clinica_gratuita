/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionJDBC;
import DTO.TratamientoDTO;
import Dominios.TratamientoDominio;
import Interfaces.ITratamientoDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class TratamientoDAO implements ITratamientoDAO {

    @Override
    public boolean insert(TratamientoDTO tratamiento) {
        String sql = """
                   INSERT INTO tratamientos(Descripcion, duracion, medicamentos,id_cita)
                   VALUES (?,?,?,?);
                   """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {
            ps.setString(1, tratamiento.getDescripcion());
            ps.setString(2, tratamiento.getDuracion());
            ps.setString(3, tratamiento.getMedicamentos());
            ps.setInt(4, tratamiento.getIdCita());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error: error al insertar un tratamiento");
            return false;

        }

    }

    @Override
    public boolean delete(int id) {
        String sql = """
                   DELETE FROM tratamientos WHERE id_tratamientos=?;
                   """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error: error al eliminar un tratamiento con id: " + id);
            return false;
        }

    }

    @Override
    public List<TratamientoDominio> readall() {
        String sql = """
                   SELECT * FROM tratamientos;
                   """;
        List<TratamientoDominio> lista = new LinkedList<>();

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id_tratamiento = rs.getInt(1);
                String descripcion = rs.getString(2);
                String duracion = rs.getString(3);
                String medicamentos = rs.getString(4);
                int id_cita = rs.getInt(5);

                TratamientoDominio tratamientoDominio = new TratamientoDominio(id_tratamiento, descripcion, duracion, medicamentos, id_cita);
                lista.add(tratamientoDominio);

            }
            return lista;
        } catch (Exception e) {
            System.out.println("Error: error al consultar todos los tratamientos");
            return null;

        }

    }

    @Override
    public boolean update(TratamientoDTO tratamiento) {
        String sql = """
                   UPDATE tratamientos 
                   SET duracion= ?, medicamentos=?
                   WHERE id_cita = ?  AND descripcion=?;
                   """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {
            ps.setString(1, tratamiento.getDuracion());
            ps.setString(2, tratamiento.getMedicamentos());
            ps.setInt(3, tratamiento.getIdCita());
            ps.setString(4, tratamiento.getDescripcion());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error: error al actualizar un medicamento");
            return false;
        }

    }

    @Override
    public TratamientoDominio buscarId(int id) {
        String sql = """
                   SELECT * FROM tratamientos WHERE id_tratamiento=?;
                   """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id_tratamiento = rs.getInt(1);
                String descripcion = rs.getString(2);
                String duracion = rs.getString(3);
                String medicamentos = rs.getString(4);
                int id_cita = rs.getInt(5);

                TratamientoDominio tratamientoDominio = new TratamientoDominio(id_tratamiento, descripcion, duracion, medicamentos, id_cita);

                return tratamientoDominio;

            }

        } catch (Exception e) {
            System.out.println("Error: error al consultar tratamiento por id: " + id);
            return null;
        }
        return null;

    }

    @Override
    public boolean existeTratamientoPorCita(int id_citas) {
        String sql = """
                    SELECT * FROM tratamientos WHERE id_cita=?;
                    """;
        try(Connection cn= ConexionJDBC.getConnection();
            PreparedStatement ps= cn.prepareCall(sql)) {
            
            ps.setInt(1, id_citas);
            
            ResultSet rs= ps.executeQuery();
            
            return rs.next();
                    
            
        } catch (Exception e) {
            System.out.println("Error: error al consultar la existencia de un tratamiento por una id de cita: id "+id_citas);
            return false;
        }
        
        
    }

    @Override
    public TratamientoDominio buscarTratamiento(TratamientoDTO tratamiento) {

        String sql= """
                    SELECT * FROM tratamientos
                    WHERE id_cita =? AND descripcion=?;
                    """;
        try(Connection cn= ConexionJDBC.getConnection();
            PreparedStatement ps= cn.prepareCall(sql)) {
            ps.setInt(1, tratamiento.getIdCita());
            ps.setString(2, tratamiento.getDescripcion());
            
            ResultSet rs= ps.executeQuery();
            
            if (rs.next()) {
                int id_tratamiento = rs.getInt(1);
                String descripcion = rs.getString(2);
                String duracion = rs.getString(3);
                String medicamentos = rs.getString(4);
                int id_cita = rs.getInt(5);

                TratamientoDominio tratamientoDominio = new TratamientoDominio(id_tratamiento, descripcion, duracion, medicamentos, id_cita);

                return tratamientoDominio;

            }
            
            
        } catch (Exception e) {
            System.out.println("Error: error al buscar un tratamiento");
            return null;
        }
        return null;

    }

}
