/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.PacienteDTO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface IPacienteDAO {
    
    //CRUD
    
    boolean insert(PacienteDTO paciente);
    boolean delete(int id);
    boolean readall();
    boolean update(PacienteDTO paciente);
    boolean buscarId(int id);
    
    
    
}
