/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.TratamientoDTO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface ITratamientoDAO {
    boolean insert(TratamientoDTO paciente);
    boolean delete(int id);
    boolean readall();
    boolean update(TratamientoDTO paciente);
    boolean buscarId(int id);
    boolean existeTratamientoPorCita(int id_citas);
    
}
