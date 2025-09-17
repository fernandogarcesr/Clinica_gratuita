package BO;

import DTO.CitaDTO;
import Dominios.CitaDominio;
import Interfaces.IPacienteDAO;
import Interfaces.ICitaDAO;
import Interfaces.IDoctorDAO;
import Interfaces.ITratamientoDAO;
import java.util.List;

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
     * Programa una nueva cita.
     */
    public void programarCita(CitaDTO cita) {
        validarCita(cita);

        if (pacienteDAO.buscarId(cita.getIdPaciente()) == null) {
            throw new IllegalArgumentException("Paciente no existe");
        }
        if (doctorDAO.buscarId(cita.getIdDoctor()) == null) {
            throw new IllegalArgumentException("Doctor no existe");
        }

        boolean programada = citaDAO.insert(cita);
        if (!programada) {
            throw new RuntimeException("Error al programar la cita");
        }
    }

    /**
     * Actualiza una cita existente.
     */
    public void actualizarCita(CitaDTO cita) {
        CitaDominio existente = citaDAO.buscarCita(cita);
        if (existente == null) {
            throw new IllegalArgumentException("Cita no existe");
        }

        validarCita(cita);

        if (pacienteDAO.buscarId(cita.getIdPaciente()) == null) {
            throw new IllegalArgumentException("Paciente no existe");
        }
        if (doctorDAO.buscarId(cita.getIdDoctor()) == null) {
            throw new IllegalArgumentException("Doctor no existe");
        }

        boolean actualizada = citaDAO.update(cita);
        if (!actualizada) {
            throw new RuntimeException("Error al actualizar la cita");
        }
    }

    /**
     * Elimina una cita por ID.
     */
    public void eliminarCita(int id) {
        CitaDominio cita = citaDAO.buscarId(id);
        if (cita == null) {
            throw new IllegalArgumentException("Cita no existe");
        }

        boolean tieneTratamientos = tratamientoDAO.existeTratamientoPorCita(id);
        if (tieneTratamientos) {
            throw new IllegalStateException("No se puede eliminar la cita porque tiene tratamientos asociados.");
        }

        boolean eliminada = citaDAO.delete(id);
        if (!eliminada) {
            throw new RuntimeException("Error al eliminar la cita");
        }
    }

    /**
     * Lista todas las citas.
     */
    public List<CitaDominio> listarCitas() {
        List<CitaDominio> citas = citaDAO.readall();
        if (citas == null || citas.isEmpty()) {
            throw new RuntimeException("No se encontraron citas");
        }
        return citas;
    }

    /**
     * Busca una cita por ID.
     */
    public CitaDominio buscarCita(int id) {
        CitaDominio cita = citaDAO.buscarId(id);
        if (cita == null) {
            throw new IllegalArgumentException("Cita no encontrada");
        }
        return cita;
    }

    /**
     * Validaciones comunes para programar y actualizar.
     */
    private void validarCita(CitaDTO cita) {
        if (cita.getMotivo() == null || cita.getMotivo().isEmpty()) {
            throw new IllegalArgumentException("El motivo es obligatorio");
        }
        if (cita.getFechaHora() == null) {
            throw new IllegalArgumentException("La fecha y hora son obligatorias");
        }
        if (cita.getEstado() == null ||
            (!cita.getEstado().equals("Programada") &&
             !cita.getEstado().equals("En curso") &&
             !cita.getEstado().equals("Completada") &&
             !cita.getEstado().equals("Cancelada"))) {
            throw new IllegalArgumentException("Estado inválido");
        }
    }
}
