/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;


import java.util.List;

import DTO.DoctorDTO;
import Dominios.DoctorDominio;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */

public interface IDoctorDAO {
    boolean insert(DoctorDTO doctor);
    boolean delete(int id);
    List<DoctorDominio> readall();
    boolean update(DoctorDTO doctor);
    DoctorDominio buscarId(int id);
    DoctorDominio buscarDoctor(DoctorDTO doctor);
    
    
}
