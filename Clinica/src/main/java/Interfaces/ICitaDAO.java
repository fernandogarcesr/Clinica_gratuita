/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.CitaDTO;
import Dominios.CitaDominio;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface ICitaDAO {

    /**
     * Inserta una nueva cita en la base de datos. Valida que los campos
     * obligatorios estén presentes y que el paciente y doctor existan.
     *
     * @param cita Objeto DTO con los datos de la cita a registrar.
     * @return true si la cita fue insertada correctamente; false en caso
     * contrario.
     */
    boolean insert(CitaDTO cita);

    /**
     * Elimina una cita existente por su identificador único.
     *
     * @param id Identificador de la cita a eliminar.
     * @return true si la cita fue eliminada exitosamente; false si no existe o
     * hubo error.
     */
    boolean delete(int id);

    /**
     * Recupera todas las citas registradas en el sistema.
     *
     * @return Lista de objetos CitaDominio con la información completa de cada
     * cita.
     */
    List<CitaDominio> readall();

    /**
     * Actualiza los datos de una cita existente. Requiere que el ID de la cita
     * esté presente y que los datos sean válidos.
     *
     * @param cita Objeto DTO con los nuevos datos de la cita.
     * @return true si la actualización fue exitosa; false si la cita no existe
     * o hubo error.
     */
    boolean update(CitaDTO cita);

    /**
     * Busca una cita por su identificador único.
     *
     * @param id Identificador de la cita.
     * @return Objeto CitaDominio si se encuentra; null si no existe.
     */
    CitaDominio buscarId(int id);

    /**
     * Busca una cita que coincida con los datos clave del DTO. Se recomienda
     * usar campos como fechaHora, idPaciente, idDoctor y estado.
     *
     * @param cita Objeto DTO con los criterios de búsqueda.
     * @return Objeto CitaDominio si se encuentra una coincidencia exacta; null
     * si no existe.
     */
    CitaDominio buscarCita(CitaDTO cita);

    /**
     * Verifica si un paciente tiene al menos una cita registrada.
     *
     * @param id_paciente Identificador del paciente.
     * @return true si existe al menos una cita asociada al paciente; false en
     * caso contrario.
     */
    boolean existeCitaPorPaciente(int id_paciente);

    /**
     * Verifica si un doctor tiene al menos una cita registrada.
     *
     * @param id_doctor Identificador del doctor.
     * @return true si existe al menos una cita asociada al doctor; false en
     * caso contrario.
     */
    boolean existeCitaPorDoctor(int id_doctor);
}
