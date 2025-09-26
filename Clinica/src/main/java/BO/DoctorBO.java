package BO;

import DTO.DoctorDTO;
import Dominios.DoctorDominio;
import Interfaces.ICitaDAO;
import Interfaces.IDoctorDAO;
import java.util.List;

/**
 * Clase de negocio encargada de gestionar las operaciones relacionadas con
 * doctores. Valida datos, verifica relaciones con citas médicas, y delega las
 * operaciones CRUD al DAO correspondiente.
 */
public class DoctorBO {

    private IDoctorDAO doctorDAO;
    private ICitaDAO citaDAO;

    /**
     * Constructor que recibe las dependencias necesarias para operar sobre
     * doctores.
     *
     * @param doctorDAO DAO para operaciones de doctor.
     * @param citaDAO DAO para validación de citas asociadas.
     */
    public DoctorBO(IDoctorDAO doctorDAO, ICitaDAO citaDAO) {
        this.doctorDAO = doctorDAO;
        this.citaDAO = citaDAO;
    }

    /**
     * Registra un nuevo doctor en el sistema. Valida los campos obligatorios
     * antes de delegar al DAO.
     *
     * @param doctor Objeto DTO con los datos del doctor a registrar.
     * @throws IllegalArgumentException si los datos son inválidos.
     * @throws RuntimeException si ocurre un error al insertar el doctor.
     */
    public void registrarDoctor(DoctorDTO doctor) {
        validarDoctor(doctor);

        boolean registrado = doctorDAO.insert(doctor);
        if (!registrado) {
            throw new RuntimeException("Error al registrar el doctor");
        }
    }

    /**
     * Actualiza los datos de un doctor existente. Verifica que el doctor exista
     * y valida los campos antes de actualizar.
     *
     * @param doctor Objeto DTO con los nuevos datos del doctor.
     * @throws IllegalArgumentException si el doctor no existe o los datos son
     * inválidos.
     * @throws RuntimeException si ocurre un error al actualizar el doctor.
     */
    public void actualizarDoctor(DoctorDTO doctor) {
        DoctorDominio existente = doctorDAO.buscarDoctor(doctor);
        if (existente == null) {
            throw new IllegalArgumentException("Doctor no existe");
        }

        validarDoctor(doctor);

        boolean actualizado = doctorDAO.update(doctor);
        if (!actualizado) {
            throw new RuntimeException("Error al actualizar el doctor");
        }
    }

    /**
     * Elimina un doctor por su identificador. Verifica que el doctor exista y
     * que no tenga citas asociadas.
     *
     * @param id Identificador del doctor a eliminar.
     * @throws IllegalArgumentException si el doctor no existe o tiene citas
     * asociadas.
     * @throws RuntimeException si ocurre un error al eliminar el doctor.
     */
    public void eliminarDoctor(int id) {
        DoctorDominio doctor = doctorDAO.buscarId(id);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor no existe");
        }

        boolean tieneCitas = citaDAO.existeCitaPorDoctor(id);
        if (tieneCitas) {
            throw new IllegalArgumentException("No se puede eliminar el doctor porque tiene citas asociadas.");
        }

        boolean eliminado = doctorDAO.delete(id);
        if (!eliminado) {
            throw new RuntimeException("Error al eliminar el doctor");
        }
    }

    /**
     * Recupera todos los doctores registrados en el sistema.
     *
     * @return Lista de objetos DoctorDominio con la información completa de
     * cada doctor.
     * @throws RuntimeException si no se encuentran doctores o ocurre un error
     * de lectura.
     */
    public List<DoctorDominio> listarDoctores() {
        List<DoctorDominio> doctores = doctorDAO.readall();
        if (doctores == null || doctores.isEmpty()) {
            throw new RuntimeException("No se encontraron doctores");
        }
        return doctores;
    }

    /**
     * Busca un doctor por su identificador único.
     *
     * @param id Identificador del doctor.
     * @return Objeto DoctorDominio si se encuentra; null si no existe.
     * @throws IllegalArgumentException si el doctor no se encuentra.
     */
    public DoctorDominio buscarDoctor(int id) {
        DoctorDominio doctor = doctorDAO.buscarId(id);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor no encontrado");
        }
        return doctor;
    }

    /**
     * Valida los campos obligatorios de un doctor. Verifica que nombre,
     * especialidad, teléfono y email estén presentes.
     *
     * @param doctor Objeto DTO a validar.
     * @throws IllegalArgumentException si algún campo obligatorio es nulo o
     * inválido.
     */
    private void validarDoctor(DoctorDTO doctor) {
        if (doctor.getNombre() == null || doctor.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (doctor.getEspecialidad() == null || doctor.getEspecialidad().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (doctor.getTelefono() == null || doctor.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (doctor.getEmail() == null || doctor.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
    }
}
