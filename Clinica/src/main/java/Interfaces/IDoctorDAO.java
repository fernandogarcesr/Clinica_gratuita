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
    /**
 * Inserta un nuevo doctor en la base de datos.
 * Valida que los campos obligatorios estén presentes y que el email no esté duplicado.
 *
 * @param doctor Objeto DTO con los datos del doctor a registrar.
 * @return true si el doctor fue insertado correctamente; false en caso contrario.
 */
boolean insert(DoctorDTO doctor);

/**
 * Elimina un doctor existente por su identificador único.
 * Se recomienda validar previamente que no tenga citas asociadas.
 *
 * @param id Identificador del doctor a eliminar.
 * @return true si el doctor fue eliminado exitosamente; false si no existe o hubo error.
 */
boolean delete(int id);

/**
 * Recupera todos los doctores registrados en el sistema.
 *
 * @return Lista de objetos DoctorDominio con la información completa de cada doctor.
 */
List<DoctorDominio> readall();

/**
 * Actualiza los datos de un doctor existente.
 * Requiere que el ID del doctor esté presente y que los datos sean válidos.
 *
 * @param doctor Objeto DTO con los nuevos datos del doctor.
 * @return true si la actualización fue exitosa; false si el doctor no existe o hubo error.
 */
boolean update(int id_doctor,DoctorDominio dominio);

/**
 * Busca un doctor por su identificador único.
 *
 * @param id Identificador del doctor.
 * @return Objeto DoctorDominio si se encuentra; null si no existe.
 */
DoctorDominio buscarId(int id);

/**
 * Busca un doctor que coincida con los datos clave del DTO.
 * Se recomienda usar campos como nombre, especialidad y email.
 *
 * @param doctor Objeto DTO con los criterios de búsqueda.
 * @return Objeto DoctorDominio si se encuentra una coincidencia exacta; null si no existe.
 */
DoctorDominio buscarDoctor(DoctorDTO doctor);

    
    
}
