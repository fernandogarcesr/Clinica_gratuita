package BO;

import DTO.DoctorDTO;
import Dominios.DoctorDominio;
import Interfaces.ICitaDAO;
import Interfaces.IDoctorDAO;
import java.util.List;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class DoctorBO {

    private IDoctorDAO doctorDAO;
    private ICitaDAO citaDAO;

    public DoctorBO(IDoctorDAO doctorDAO, ICitaDAO citaDAO) {
        this.doctorDAO = doctorDAO;
        this.citaDAO = citaDAO;
    }

    /**
     * Registra un nuevo doctor.
     */
    public void registrarDoctor(DoctorDTO doctor) {
        validarDoctor(doctor);

        boolean registrado = doctorDAO.insert(doctor);
        if (!registrado) {
            throw new RuntimeException("Error al registrar el doctor");
        }
    }

    /**
     * Actualiza un doctor existente.
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
     * Elimina un doctor por ID.
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
     * Lista a todos los doctores.
     */
    public List<DoctorDominio> listarDoctores() {
        List<DoctorDominio> doctores = doctorDAO.readall();
        if (doctores == null || doctores.isEmpty()) {
            throw new RuntimeException("No se encontraron doctores");
        }
        return doctores;
    }

    /**
     * Busca un doctor por ID.
     */
    public DoctorDominio buscarDoctor(int id) {
        DoctorDominio doctor = doctorDAO.buscarId(id);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor no encontrado");
        }
        return doctor;
    }

    /**
     * Validaciones comunes para registrar y actualizar.
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

