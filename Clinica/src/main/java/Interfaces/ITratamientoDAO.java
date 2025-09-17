/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.TratamientoDTO;
import Dominios.TratamientoDominio;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface ITratamientoDAO {

    boolean insert(TratamientoDTO paciente);
    boolean delete(int id);
    List<TratamientoDominio> readall();
    boolean update(TratamientoDTO paciente);
    TratamientoDominio buscarId(int id);
    TratamientoDominio buscarTratamiento(TratamientoDTO tratamiento);
    boolean existeTratamientoPorCita(int id_citas);
    
}
