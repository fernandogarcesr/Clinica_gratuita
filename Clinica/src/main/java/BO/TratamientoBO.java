package BO;

import DTO.TratamientoDTO;
import Interfaces.ICitaDAO;
import Interfaces.ITratamientoDAO;

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
     * Registra un tratamiento para una cita existente. Valida campos
     * obligatorios y que la cita exista.
     *
     * @param tratamiento
     */
    public void registrarTratamiento(TratamientoDTO tratamiento) {
        if (tratamiento.getDescripcion() == null || tratamiento.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripcion es obligatoria");
        }
        if (tratamiento.getDuracion() == null || tratamiento.getDuracion().isEmpty()) {
            throw new IllegalArgumentException("La duracion es obligatoria");
        }
        if (tratamiento.getMedicamentos() == null || tratamiento.getMedicamentos().isEmpty()) {
            throw new IllegalArgumentException("Los medicamentos son obligatorios");
        }
        if (!citaDAO.buscarId(tratamiento.getIdCita())) {
            throw new IllegalArgumentException("La cita asociada no existe");
        }

        boolean registrado = tratamientoDAO.insert(tratamiento);
        if (!registrado) {
            throw new RuntimeException("Error al registrar el tratamiento");
        }
    }
    //actualiza el tratamiento existente

    public void actualizarTratamiento(TratamientoDTO tratamiento) {
        if (!tratamientoDAO.buscarId(tratamiento.getId())) {
            throw new IllegalArgumentException("Tratamiento no existe");
        }
        // Validaciones de campos obligatorios
        if (tratamiento.getDescripcion() == null || tratamiento.getDescripcion().isEmpty()) {
            throw new IllegalArgumentException("La descripcion es obligatoria");
        }
        if (tratamiento.getDuracion() == null || tratamiento.getDuracion().isEmpty()) {
            throw new IllegalArgumentException("La duracion es obligatoria");
        }
        if (tratamiento.getMedicamentos() == null || tratamiento.getMedicamentos().isEmpty()) {
            throw new IllegalArgumentException("Los medicamentos son obligatorios");
        }
        if (!citaDAO.buscarId(tratamiento.getIdCita())) {
            throw new IllegalArgumentException("La cita asociada no existe");
        }

        boolean actualizado = tratamientoDAO.update(tratamiento);
        if (!actualizado) {
            throw new RuntimeException("Error al actualizar el tratamiento");
        }
    }
    //elimina un tratamiento por ID

    public void eliminarTratamiento(int id) {
        if (!tratamientoDAO.buscarId(id)) {
            throw new IllegalArgumentException("Tratamiento no existe");
        }
        boolean eliminado = tratamientoDAO.delete(id);

        if (!eliminado) {
            throw new RuntimeException("Error al eliminar el tratamiento");
        }
    }
    //lista todos los tratamientos

    public void listarTratamientos() {
        boolean listados = tratamientoDAO.readall();
        if (!listados) {
            throw new RuntimeException("Error al listar los tratamientos");
        }
    }

    //busca un tratamiento por ID
    public void buscarTratamiento(int id) {
        boolean encontrado = tratamientoDAO.buscarId(id);
        if (!encontrado) {
            throw new IllegalArgumentException("Tratamiento no encontrado");
        }
    }
}
