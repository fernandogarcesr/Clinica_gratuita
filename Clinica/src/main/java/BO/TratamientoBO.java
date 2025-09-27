package BO;

import DTO.TratamientoDTO;
import Dominios.TratamientoDominio;
import Interfaces.ICitaDAO;
import Interfaces.ITratamientoDAO;
import java.util.List;

/**
 * Clase de negocio encargada de gestionar las operaciones relacionadas con
 * tratamientos médicos. Valida los datos, verifica la existencia de la cita
 * asociada, y delega las operaciones CRUD al DAO correspondiente.
 */
public class TratamientoBO {

    private ITratamientoDAO tratamientoDAO;
    private ICitaDAO citaDAO;

    /**
     * Constructor que recibe las dependencias necesarias para operar sobre
     * tratamientos.
     *
     * @param tratamientoDAO DAO para operaciones de tratamiento.
     * @param citaDAO DAO para validación de citas asociadas.
     */
    public TratamientoBO(ITratamientoDAO tratamientoDAO, ICitaDAO citaDAO) {
        this.tratamientoDAO = tratamientoDAO;
        this.citaDAO = citaDAO;
    }

    /**
     * Registra un nuevo tratamiento médico asociado a una cita existente.
     * Valida los campos obligatorios y verifica que la cita exista.
     *
     * @param tratamiento Objeto DTO con los datos del tratamiento a registrar.
     * @throws IllegalArgumentException si los datos son inválidos o la cita no
     * existe.
     * @throws RuntimeException si ocurre un error al insertar el tratamiento.
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
     * Actualiza los datos de un tratamiento existente. Verifica que el
     * tratamiento exista, valida los campos y confirma que la cita esté
     * registrada.
     *
     * @param tratamiento Objeto DTO con los nuevos datos del tratamiento.
     * @throws IllegalArgumentException si el tratamiento no existe, los datos
     * son inválidos o la cita no existe.
     * @throws RuntimeException si ocurre un error al actualizar el tratamiento.
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
     * Elimina un tratamiento por su identificador. Verifica que el tratamiento
     * exista antes de eliminar.
     *
     * @param id Identificador del tratamiento a eliminar.
     * @throws IllegalArgumentException si el tratamiento no existe.
     * @throws RuntimeException si ocurre un error al eliminar el tratamiento.
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
     * Recupera todos los tratamientos registrados en el sistema.
     *
     * @return Lista de objetos TratamientoDominio con la información completa
     * de cada tratamiento.
     * @throws RuntimeException si no se encuentran tratamientos o ocurre un
     * error de lectura.
     */
    public List<TratamientoDominio> listarTratamientos() {
        List<TratamientoDominio> tratamientos = tratamientoDAO.readall();
        if (tratamientos == null || tratamientos.isEmpty()) {
            throw new RuntimeException("No se encontraron tratamientos");
        }
        return tratamientos;
    }

    /**
     * Busca un tratamiento por su identificador único.
     *
     * @param id Identificador del tratamiento.
     * @return Objeto TratamientoDominio si se encuentra; null si no existe.
     * @throws IllegalArgumentException si el tratamiento no se encuentra.
     */
    public TratamientoDominio buscarTratamiento(int id) {
        TratamientoDominio tratamiento = tratamientoDAO.buscarId(id);
        if (tratamiento == null) {
            throw new IllegalArgumentException("Tratamiento no encontrado");
        }
        return tratamiento;
    }

    /**
     * Valida los campos obligatorios de un tratamiento. Verifica que
     * descripción, duración y medicamentos estén presentes y sean válidos.
     *
     * @param tratamiento Objeto DTO a validar.
     * @throws IllegalArgumentException si algún campo obligatorio es nulo o
     * inválido.
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
