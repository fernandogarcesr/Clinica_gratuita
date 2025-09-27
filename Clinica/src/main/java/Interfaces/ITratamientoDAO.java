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

    /**
     * Inserta un nuevo tratamiento en la base de datos. Valida que los campos
     * obligatorios estén presentes y que la cita asociada exista.
     *
     * @param tratamiento Objeto DTO con los datos del tratamiento a registrar.
     * @return true si el tratamiento fue insertado correctamente; false en caso
     * contrario.
     */
    boolean insert(TratamientoDTO tratamiento);

    /**
     * Elimina un tratamiento existente por su identificador único.
     *
     * @param id Identificador del tratamiento a eliminar.
     * @return true si el tratamiento fue eliminado exitosamente; false si no
     * existe o hubo error.
     */
    boolean delete(int id);

    /**
     * Recupera todos los tratamientos registrados en el sistema.
     *
     * @return Lista de objetos TratamientoDominio con la información completa
     * de cada tratamiento.
     */
    List<TratamientoDominio> readall();

    /**
     * Actualiza los datos de un tratamiento existente. Requiere que el ID del
     * tratamiento esté presente y que los datos sean válidos.
     *
     * @param tratamiento Objeto DTO con los nuevos datos del tratamiento.
     * @return true si la actualización fue exitosa; false si el tratamiento no
     * existe o hubo error.
     */
    boolean update(TratamientoDTO tratamiento);

    /**
     * Busca un tratamiento por su identificador único.
     *
     * @param id Identificador del tratamiento.
     * @return Objeto TratamientoDominio si se encuentra; null si no existe.
     */
    TratamientoDominio buscarId(int id);

    /**
     * Busca un tratamiento que coincida con los datos clave del DTO. Se
     * recomienda usar campos como descripción, duración, medicamentos e idCita.
     *
     * @param tratamiento Objeto DTO con los criterios de búsqueda.
     * @return Objeto TratamientoDominio si se encuentra una coincidencia
     * exacta; null si no existe.
     */
    TratamientoDominio buscarTratamiento(TratamientoDTO tratamiento);

    /**
     * Verifica si una cita tiene al menos un tratamiento registrado.
     *
     * @param id_citas Identificador de la cita.
     * @return true si existe al menos un tratamiento asociado a la cita; false
     * en caso contrario.
     */
    boolean existeTratamientoPorCita(int id_citas);

}
