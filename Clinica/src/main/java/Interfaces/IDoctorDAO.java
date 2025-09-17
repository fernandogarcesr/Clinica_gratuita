/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.DoctorDTO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface IDoctorDAO {
    boolean insert(DoctorDTO paciente);
    boolean delete(int id);
    boolean readall();
    boolean update(DoctorDTO paciente);
    boolean buscarId(int id);
   
    
}
