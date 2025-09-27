/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import DTO.PacienteDTO;
import Dominios.PacienteDominio;

import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public interface IPacienteDAO {

    /**
     * Inserta un nuevo paciente en la base de datos. Valida que los campos
     * obligatorios estén presentes y que el email no esté duplicado.
     *
     * @param paciente Objeto DTO con los datos del paciente a registrar.
     * @return true si el paciente fue insertado correctamente; false en caso
     * contrario.
     */
    boolean insert(PacienteDTO paciente);

    /**
     * Elimina un paciente existente por su identificador único. Se recomienda
     * validar previamente que no tenga citas asociadas.
     *
     * @param id Identificador del paciente a eliminar.
     * @return true si el paciente fue eliminado exitosamente; false si no
     * existe o hubo error.
     */
    boolean delete(int id);

    /**
     * Recupera todos los pacientes registrados en el sistema.
     *
     * @return Lista de objetos PacienteDominio con la información completa de
     * cada paciente.
     */
    List<PacienteDominio> readall();

    /**
     * Actualiza los datos de un paciente existente. Requiere que el ID del
     * paciente esté presente y que los datos sean válidos.
     *
     * @param paciente Objeto DTO con los nuevos datos del paciente.
     * @return true si la actualización fue exitosa; false si el paciente no
     * existe o hubo error.
     */
    boolean update(PacienteDTO paciente);

    /**
     * Busca un paciente por su identificador único.
     *
     * @param id Identificador del paciente.
     * @return Objeto PacienteDominio si se encuentra; null si no existe.
     */
    PacienteDominio buscarId(int id);

    /**
     * Busca un paciente que coincida con los datos clave del DTO. Se recomienda
     * usar campos como nombre, edad, sexo, dirección, teléfono y email.
     *
     * @param paciente Objeto DTO con los criterios de búsqueda.
     * @return Objeto PacienteDominio si se encuentra una coincidencia exacta;
     * null si no existe.
     */
    PacienteDominio buscarPaciente(PacienteDTO paciente);

}
