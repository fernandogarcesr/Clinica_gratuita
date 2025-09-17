package BO;

import DTO.TratamientoDTO;
import Dominios.TratamientoDominio;
import Interfaces.ICitaDAO;
import Interfaces.ITratamientoDAO;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class TratamientoBO {

    private ITratamientoDAO tratamientoDAO;
    private ICitaDAO citaDAO;

    public TratamientoBO(ITratamientoDAO tratamientoDAO, ICitaDAO citaDAO) {
        this.tratamientoDAO = tratamientoDAO;
        this.citaDAO = citaDAO;
    }

    /**
     * Registra un tratamiento para una cita existente.
     */
    public void registrarTratamiento(TratamientoDTO tratamiento) {
        validarTratamiento(tratamiento);

        if (citaDAO.buscarId(tratamiento.getIdCita()) == null) {
            throw new IllegalArgumentException("La cita asociada no existe");
        }

        boolean registrado = tratamientoDAO.insert(tratamiento);
        if (!registrado) {
            throw new RuntimeException("Error al registrar el tratamiento");
        }
    }

    /**
     * Actualiza un tratamiento existente.
     */
    public void actualizarTratamiento(TratamientoDTO tratamiento) {
        TratamientoDominio existente = tratamientoDAO.buscarTratamiento(tratamiento);
        if (existente == null) {
            throw new IllegalArgumentException("Tratamiento no existe");
        }

        validarTratamiento(tratamiento);

        if (citaDAO.buscarId(tratamiento.getIdCita()) == null) {
            throw new IllegalArgumentException("La cita asociada no existe");
        }

        boolean actualizado = tratamientoDAO.update(tratamiento);
        if (!actualizado) {
            throw new RuntimeException("Error al actualizar el tratamiento");
        }
    }

    /**
     * Elimina un tratamiento por ID.
     */
    public void eliminarTratamiento(int id) {
        TratamientoDominio tratamiento = tratamientoDAO.buscarId(id);
        if (tratamiento == null) {
            throw new IllegalArgumentException("Tratamiento no existe");
        }

        boolean eliminado = tratamientoDAO.delete(id);
        if (!eliminado) {
            throw new RuntimeException("Error al eliminar el tratamiento");
        }
    }

    /**
     * Lista todos los tratamientos.
     */
    public List<TratamientoDominio> listarTratamientos() {
        List<TratamientoDominio> tratamientos = tratamientoDAO.readall();
        if (tratamientos == null || tratamientos.isEmpty()) {
            throw new RuntimeException("No se encontraron tratamientos");
        }
        return tratamientos;
    }

    /**
     * Busca un tratamiento por ID.
     */
    public TratamientoDominio buscarTratamiento(int id) {
        TratamientoDominio tratamiento = tratamientoDAO.buscarId(id);
        if (tratamiento == null) {
            throw new IllegalArgumentException("Tratamiento no encontrado");
        }
        return tratamiento;
    }

    /**
     * Validaciones comunes para registrar y actualizar.
     */
    private void validarTratamiento(TratamientoDTO tratamiento) {
        if (tratamiento.getDescripcion() == null || tratamiento.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripción es obligatoria");
        }
        if (tratamiento.getDuracion() == null || tratamiento.getDuracion().isEmpty()) {
            throw new IllegalArgumentException("La duración es obligatoria");
        }
        if (tratamiento.getMedicamentos() == null || tratamiento.getMedicamentos().isEmpty()) {
            throw new IllegalArgumentException("Los medicamentos son obligatorios");
        }
    }
}

