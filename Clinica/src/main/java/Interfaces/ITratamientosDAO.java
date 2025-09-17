/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.TratamientosDTO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface ITratamientosDAO {
    boolean insert(TratamientosDTO paciente);
    boolean delete(int id);
    boolean readall();
    boolean update(TratamientosDTO paciente);
    boolean buscarId(int id);
    boolean existeTratamientoPorCita(int id_citas);
    
}
