/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.DoctoresDTO;
import Dominios.DoctoresDominio;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface IDoctoresDAO {
    boolean insert(DoctoresDTO doctor);
    boolean delete(int id);
    List<DoctoresDominio> readall();
    boolean update(DoctoresDTO doctor);
    DoctoresDominio buscarId(int id);
    
}
