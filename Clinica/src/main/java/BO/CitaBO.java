package BO;

import DTO.CitaDTO;
import Interfaces.IPacienteDAO;
import Interfaces.ICitaDAO;
import Interfaces.IDoctorDAO;
import Interfaces.ITratamientoDAO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class CitaBO {

    private ICitaDAO citaDAO;
    private IPacienteDAO pacienteDAO;
    private IDoctorDAO doctorDAO;
    private ITratamientoDAO tratamientoDAO;

    public CitaBO(ICitaDAO citaDAO, IPacienteDAO pacienteDAO, IDoctorDAO doctorDAO, ITratamientoDAO tratamientoDAO) {
        this.citaDAO = citaDAO;
        this.pacienteDAO = pacienteDAO;
        this.doctorDAO = doctorDAO;
        this.tratamientoDAO = tratamientoDAO;
    }

    /**
     * Programa una nueva cita. Valida campos obligatorios, estado valido,
     * paciente y doctor existentes.
     *
     * @param cita
     */
    public void programarCita(CitaDTO cita) {
        if (cita.getMotivo() == null || cita.getMotivo().isEmpty()) {
            throw new IllegalArgumentException("El motivo es obligatorio");
        }
        if (cita.getFechaHora() == null) {
            throw new IllegalArgumentException("La fecha y hora son obligatorias");
        }
        if (cita.getEstado() == null
                || (!cita.getEstado().equals("Programada")
                && !cita.getEstado().equals("En curso")
                && !cita.getEstado().equals("Completada")
                && !cita.getEstado().equals("Cancelada"))) {
            throw new IllegalArgumentException("Estado invalido");
        }
        if (!pacienteDAO.buscarId(cita.getIdPaciente())) {
            throw new IllegalArgumentException("Paciente no existe");
        }
        if (!doctorDAO.buscarId(cita.getIdDoctor())) {
            throw new IllegalArgumentException("Doctor no existe");
        }

        boolean programada = citaDAO.insert(cita);
        if (!programada) {
            throw new RuntimeException("Error al programar la cita");
        }
    }

    //actualiza una cita existente
    public void actualizarCita(CitaDTO cita) {
        if (!citaDAO.buscarId(cita.getId())) {
            throw new IllegalArgumentException("Cita no existe");
        }

        // Validaciones de campos obligatorios
        if (cita.getMotivo() == null || cita.getMotivo().isEmpty()) {
            throw new IllegalArgumentException("El motivo es obligatorio");
        }
        if (cita.getFechaHora() == null) {
            throw new IllegalArgumentException("La fecha y hora son obligatorias");
        }
        if (cita.getEstado() == null
                || (!cita.getEstado().equals("Programada")
                && !cita.getEstado().equals("En curso")
                && !cita.getEstado().equals("Completada")
                && !cita.getEstado().equals("Cancelada"))) {
            throw new IllegalArgumentException("Estado invalido");
        }
        if (!pacienteDAO.buscarId(cita.getIdPaciente())) {
            throw new IllegalArgumentException("Paciente no existe");
        }
        if (!doctorDAO.buscarId(cita.getIdDoctor())) {
            throw new IllegalArgumentException("Doctor no existe");
        }
        boolean actualizada = citaDAO.update(cita);
        if (!actualizada) {
            throw new RuntimeException("Error al actualizar la cita");
        }
    }
    //elimina una cita por ID
    //valida si esta asociada a un tratamiento

    public void eliminarCita(int id) {
        if (!citaDAO.buscarId(id)) {
            throw new IllegalArgumentException("Cita no existe");
        }
        boolean tieneTratamientos = tratamientoDAO.existeTratamientoPorCita(id);
        if (tieneTratamientos) {
            throw new IllegalStateException("No se puede eliminar la cita por que tiene tratamientos asociados.");
        }

        boolean eliminada = citaDAO.delete(id);
        if (!eliminada) {
            throw new RuntimeException("Error al eliminar la cita");
        }
    }

    //lista todas las citas
    public void listarCitas() {
        boolean listadas = citaDAO.readall();
        if (!listadas) {
            throw new RuntimeException("Error al listar las citas");
        }
    }

    //busca una cita por ID
    public void buscarCita(int id) {
        boolean encotrada = citaDAO.buscarId(id);
        if (!encotrada) {
            throw new IllegalArgumentException("Cita no encontrada");
        }
    }

}
