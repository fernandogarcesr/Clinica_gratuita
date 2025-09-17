/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.PacienteDTO;
import Dominios.PacienteDominio;

import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface IPacienteDAO {
    
    //CRUD
    
    boolean insert(PacienteDTO paciente);
    boolean delete(int id);
    List<PacienteDominio> readall();
    boolean update(PacienteDTO paciente);
    PacienteDominio buscarId(int id);
    PacienteDominio buscarPaciente(PacienteDTO paciente);
    
    
    
}
