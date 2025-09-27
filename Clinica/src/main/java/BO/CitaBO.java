package BO;

import DTO.CitaDTO;
import Dominios.CitaDominio;
import Interfaces.IPacienteDAO;
import Interfaces.ICitaDAO;
import Interfaces.IDoctorDAO;
import Interfaces.ITratamientoDAO;
import java.util.List;

/**
 * Clase de negocio encargada de gestionar las operaciones relacionadas con citas médicas.
 * Valida datos, verifica relaciones con pacientes, doctores y tratamientos,
 * y delega las operaciones CRUD al DAO correspondiente.
 */
public class CitaBO {

    private ICitaDAO citaDAO;
    private IPacienteDAO pacienteDAO;
    private IDoctorDAO doctorDAO;
    private ITratamientoDAO tratamientoDAO;

    /**
     * Constructor que recibe las dependencias necesarias para operar sobre citas.
     *
     * @param citaDAO DAO para operaciones de cita.
     * @param pacienteDAO DAO para validación de pacientes.
     * @param doctorDAO DAO para validación de doctores.
     * @param tratamientoDAO DAO para validación de tratamientos asociados.
     */
    public CitaBO(ICitaDAO citaDAO, IPacienteDAO pacienteDAO, IDoctorDAO doctorDAO, ITratamientoDAO tratamientoDAO) {
        this.citaDAO = citaDAO;
        this.pacienteDAO = pacienteDAO;
        this.doctorDAO = doctorDAO;
        this.tratamientoDAO = tratamientoDAO;
    }

    /**
     * Programa una nueva cita médica.
     * Valida los campos obligatorios y verifica que el paciente y el doctor existan.
     *
     * @param cita Objeto DTO con los datos de la cita a registrar.
     * @throws IllegalArgumentException si los datos son inválidos o las entidades relacionadas no existen.
     * @throws RuntimeException si ocurre un error al insertar la cita.
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
     * Actualiza los datos de una cita existente.
     * Verifica que la cita exista, valida los campos y confirma que el paciente y el doctor estén registrados.
     *
     * @param cita Objeto DTO con los nuevos datos de la cita.
     * @throws IllegalArgumentException si la cita no existe o los datos son inválidos.
     * @throws RuntimeException si ocurre un error al actualizar la cita.
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
     * Elimina una cita por su identificador.
     * Verifica que la cita exista y que no tenga tratamientos asociados.
     *
     * @param id Identificador de la cita a eliminar.
     * @throws IllegalArgumentException si la cita no existe.
     * @throws IllegalStateException si la cita tiene tratamientos asociados.
     * @throws RuntimeException si ocurre un error al eliminar la cita.
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
     * Recupera todas las citas registradas en el sistema.
     *
     * @return Lista de objetos CitaDominio con la información completa de cada cita.
     * @throws RuntimeException si no se encuentran citas o ocurre un error de lectura.
     */
    public List<CitaDominio> listarCitas() {
        List<CitaDominio> citas = citaDAO.readall();
        if (citas == null || citas.isEmpty()) {
            throw new RuntimeException("No se encontraron citas");
        }
        return citas;
    }

    /**
     * Busca una cita por su identificador único.
     *
     * @param id Identificador de la cita.
     * @return Objeto CitaDominio si se encuentra; null si no existe.
     * @throws IllegalArgumentException si la cita no se encuentra.
     */
    public CitaDominio buscarCita(int id) {
        CitaDominio cita = citaDAO.buscarId(id);
        if (cita == null) {
            throw new IllegalArgumentException("Cita no encontrada");
        }
        return cita;
    }

    /**
     * Valida los campos obligatorios de una cita.
     * Verifica que el motivo, la fecha/hora y el estado sean válidos.
     *
     * @param cita Objeto DTO a validar.
     * @throws IllegalArgumentException si algún campo obligatorio es nulo o inválido.
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
