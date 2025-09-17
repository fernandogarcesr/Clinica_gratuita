/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.CitaDTO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface ICitaDAO {
    boolean insert(CitaDTO paciente);
    boolean delete(int id);
    boolean readall();
    boolean update(CitaDTO paciente);
    boolean buscarId(int id);
    boolean existeCitaPorPaciente(int id_paciente);
    boolean existeCitaPorDoctor(int id_doctor);
    
}
