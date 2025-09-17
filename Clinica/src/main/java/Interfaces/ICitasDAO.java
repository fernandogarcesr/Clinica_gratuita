/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.CitasDTO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface ICitasDAO {
    boolean insert(CitasDTO paciente);
    boolean delete(int id);
    boolean readall();
    boolean update(CitasDTO paciente);
    boolean buscarId(int id);
    
    
}
