/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Config.ConexionJDBC;
import DTO.CitaDTO;
import Dominios.CitaDominio;
import Dominios.ENUM.Estado;
import Interfaces.ICitaDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class CitaDAO implements ICitaDAO {

    @Override
    public boolean insert(CitaDTO cita) {
        String sql = """
                   INSERT INTO citas(motivo, fecha_hora, estado, id_doctor, id_paciente)
                   VALUES (?,?,?,?,?);
                   """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setString(1, cita.getMotivo());
            ps.setDate(2, cita.getFechaHora());
            ps.setString(3, cita.getEstado().toString());
            ps.setInt(4, cita.getIdDoctor());
            ps.setInt(5, cita.getIdPaciente());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error: error al guardar una cita");
            return false;
        }

    }

    @Override
    public boolean delete(int id) {
        String sql = """
                    DELETE FROM citas WHERE id_citas=?;                   
                   """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error: error al eliminar con id: " + id);
            return false;
        }

    }

    @Override
    public List<CitaDominio> readall() {
        String sql = """
                    SELECT * FROM citas;
                    """;
        List<CitaDominio> lista = new LinkedList<CitaDominio>();

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql); ResultSet rs = ps.executeQuery();) {

            while (rs.next()) {

                String motivo = rs.getString(2);
                Date fechaHora = rs.getDate(3);
                Estado estado = null;
                if (rs.getString(4).equalsIgnoreCase("Programada")) {
                    estado = Estado.PROGRAMADA;
                }
                if (rs.getString(4).equalsIgnoreCase("En curso")) {
                    estado = Estado.ENCURSO;
                }
                if (rs.getString(4).equalsIgnoreCase("completada")) {
                    estado = Estado.COMPLETADA;
                }
                if (rs.getString(4).equalsIgnoreCase("cancelada")) {
                    estado = Estado.CANCELADA;
                }

                int id_doctor = rs.getInt(5);
                int id_paciente = rs.getInt(6);

                CitaDominio citaDominio = new CitaDominio(id_doctor, motivo, fechaHora, estado, id_doctor, id_paciente);

                lista.add(citaDominio);

            }
            return lista;

        } catch (SQLException e) {
            System.out.println("Error: error al consultar todas las citas");
            return lista;
        }

    }

    @Override
    public boolean update(CitaDTO cita) {
        String sql = """
                    UPDATE citas
                    SET estado= ?
                    WHERE fecha_hora=? AND id_doctor = ? AND id_paciente=?; 
                    """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setString(1, cita.getEstado().toString());
            ps.setDate(2, cita.getFechaHora());
            ps.setInt(3, cita.getIdDoctor());
            ps.setInt(4, cita.getIdPaciente());

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.out.println("Error: error al actualizar la cita");
            return false;

        }

    }

    @Override
    public CitaDominio buscarId(int id) {
        String sql = """
                    SELECT * FROM citas WHERE id_citas= ?;
                    """;

        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String motivo = rs.getString(2);
                Date fechaHora = rs.getDate(3);
                Estado estado = null;
                if (rs.getString(4).equalsIgnoreCase("Programada")) {
                    estado = Estado.PROGRAMADA;
                }
                if (rs.getString(4).equalsIgnoreCase("En curso")) {
                    estado = Estado.ENCURSO;
                }
                if (rs.getString(4).equalsIgnoreCase("completada")) {
                    estado = Estado.COMPLETADA;
                }
                if (rs.getString(4).equalsIgnoreCase("cancelada")) {
                    estado = Estado.CANCELADA;
                }

                int id_doctor = rs.getInt(5);
                int id_paciente = rs.getInt(6);

                CitaDominio citaDominio = new CitaDominio(id_doctor, motivo, fechaHora, estado, id_doctor, id_paciente);

                return citaDominio;

            }

        } catch (SQLException e) {
            System.out.println("Error: error al consultar paciente con id: " + id);
            return null;
        }
        return null;

    }

    @Override
    public boolean existeCitaPorPaciente(int id_paciente) {
        String sql = """
                        SELECT 1 FROM citas WHERE id_paciente= ?;
                        
                        """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id_paciente);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error: error al consultar \n la existencia de un paciente en una cita");
            return false;
        }

    }

    @Override
    public boolean existeCitaPorDoctor(int id_doctor) {
        String sql = """
                        SELECT 1 FROM citas WHERE id_doctor= ?;
                        
                        """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setInt(1, id_doctor);

            int filasAfectadas = ps.executeUpdate();

            return filasAfectadas > 0;

        } catch (Exception e) {
            System.out.println("Error: error al consultar \n la existencia de un doctor en una cita");
            return false;
        }

    }

    @Override
    public CitaDominio buscarCita(CitaDTO cita) {
        String sql = """
                    SELECT * FROM citas 
                    WHERE motivo= ? AND 
                    fecha_hora= ? AND
                    estado= ? AND
                    id_doctor= ? AND 
                    id_paciente= ?;
                    """;
        try (Connection cn = ConexionJDBC.getConnection(); PreparedStatement ps = cn.prepareCall(sql)) {

            ps.setString(1, cita.getMotivo());
            ps.setDate(2, cita.getFechaHora());
            ps.setString(3, cita.getEstado().toString());
            ps.setInt(4, cita.getIdDoctor());
            ps.setInt(5, cita.getIdPaciente());

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                String motivo = rs.getString(2);
                Date fechaHora = rs.getDate(3);
                Estado estado = null;
                if (rs.getString(4).equalsIgnoreCase("Programada")) {
                    estado = Estado.PROGRAMADA;
                }
                if (rs.getString(4).equalsIgnoreCase("En curso")) {
                    estado = Estado.ENCURSO;
                }
                if (rs.getString(4).equalsIgnoreCase("completada")) {
                    estado = Estado.COMPLETADA;
                }
                if (rs.getString(4).equalsIgnoreCase("cancelada")) {
                    estado = Estado.CANCELADA;
                }

                int id_doctor = rs.getInt(5);
                int id_paciente = rs.getInt(6);

                CitaDominio citaDominio = new CitaDominio(id_doctor, motivo, fechaHora, estado, id_doctor, id_paciente);

                return citaDominio;

            }

        } catch (Exception e) {
            System.out.println("Error: error al buscar una cita");
            return null;
            
        }
        return null;

    }
    
     // 🔹 Actualizar el estado de una cita por su id
    public void updateEstado(int idCita, String nuevoEstado) throws Exception {
        String sql = "UPDATE citas SET estado = ? WHERE id = ?";

        try (Connection conn = ConexionJDBC.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, nuevoEstado);
            ps.setInt(2, idCita);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new Exception("No se encontró la cita con id " + idCita);
            }
        }
    }

}
