package BO;

import DTO.DoctoresDTO;
import Interfaces.ICitasDAO;
import Interfaces.IDoctoresDAO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class DoctorBO {

    private IDoctoresDAO doctorDAO;
    private ICitasDAO citaDAO;

    public DoctorBO(IDoctoresDAO doctorDAO, ICitasDAO citaDAO) {
        this.doctorDAO = doctorDAO;
        this.citaDAO = citaDAO;
    }
   
    /**
     * Registra un nuevo doctor.
     * Valida campos obligatorios y llama al DAO para insertar.
     * @param doctor 
     */
    public void registrarDoctor(DoctoresDTO doctor) {
        if (doctor.getNombre() == null || doctor.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (doctor.getEspecialidad() == null || doctor.getEspecialidad().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (doctor.getTelefono() == null || doctor.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El telefono es obligatorio");
        }
        if (doctor.getEmail() == null || doctor.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        boolean registrado = doctorDAO.insert(doctor);
        if (!registrado) {
            throw new RuntimeException("Error al registrar el doctor");
        }
    }
   /**
    * Actualiza un doctor existente.
    * Valida que exista y que los campos obligatorios no sean nulos.
    * @param doctor 
    */
    public void actualizarDoctor(DoctoresDTO doctor) {
        if (!doctorDAO.buscarId(doctor.getId())) {
            throw new IllegalArgumentException("Doctor no existe");
        }
        // Validaciones de no nulos
        if (doctor.getNombre() == null || doctor.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (doctor.getEspecialidad() == null || doctor.getEspecialidad().isEmpty()) {
            throw new IllegalArgumentException("La especialidad es obligatoria");
        }
        if (doctor.getTelefono() == null || doctor.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El telefono es obligatorio");
        }
        if (doctor.getEmail() == null || doctor.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }

        boolean actualizado = doctorDAO.update(doctor);
        if (!actualizado) {
            throw new RuntimeException("Error al actualizar el doctor");
        }
    }
    /**
     * Elimina un doctor por ID.
     * valida que no este asociado a ninguna cita
     * Valida que exista antes de eliminar.
     * @param id 
     */
    public void eliminarDoctor(int id) {
        if (!doctorDAO.buscarId(id)) {
            throw new IllegalArgumentException("Doctor no existe");
        }
        boolean tieneCitas = citaDAO.existeCitaPorDoctor(id);
        if(tieneCitas){
        throw new IllegalArgumentException("No se puede eliminar el doctor por que tiene citas asociadas.");
        }
        
        boolean eliminado = doctorDAO.delete(id);
        if (!eliminado) {
            throw new RuntimeException("Error al eliminar el doctor");
        }
    }
    //lista a todos los doctores
    public void listarDoctores() {
        boolean listados = doctorDAO.readall();
        if (!listados) {
            throw new RuntimeException("Error al listar doctores");
        }
    }
    //busca un doctor por ID
    public void buscarDoctor(int id) {
        boolean encontrado = doctorDAO.buscarId(id);
        if (!encontrado) {
            throw new IllegalArgumentException("Doctor no encontrado");
        }
    }
}
