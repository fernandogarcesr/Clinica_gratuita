/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.CitaDTO;
import Dominios.CitaDominio;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface ICitaDAO {
    boolean insert(CitaDTO cita);
    boolean delete(int id);
    List<CitaDominio> readall();
    boolean update(CitaDTO cita);
    CitaDominio buscarId(int id);
    CitaDominio buscarCita(CitaDTO cita);
    boolean existeCitaPorPaciente(int id_paciente);
    boolean existeCitaPorDoctor(int id_doctor);
    
}
