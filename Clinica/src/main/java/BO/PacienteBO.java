
package BO;

import DTO.PacienteDTO;
import Interfaces.IPacienteDAO;
import Interfaces.ICitaDAO;

/**
 *
 * @author Jesus Gammael Soto Escalante 248336
 */
public class PacienteBO {

    private IPacienteDAO pacienteDAO;
    private ICitaDAO citaDAO;

    public PacienteBO(IPacienteDAO pacienteDAO, ICitaDAO citaDAO) {
        this.pacienteDAO = pacienteDAO;
        this.citaDAO = citaDAO;
    }
    
   /**
    * Registra un nuevo paciente en el sistema
    * valida que los campos obligatorios no sean nulos o vacios
    * y luego llama a DAO para insertar en la base de datos.
    * @param paciente 
    */
    public void registrarPaciente(PacienteDTO paciente) {
        //validaciones de no nulos
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (paciente.getEdad() == null) {
            throw new IllegalArgumentException("La edad es obligatoria");
        }
        if (paciente.getSexo() == null || (!paciente.getSexo().equals("Masculino") && !paciente.getSexo().equals("Femenino"))) {
            throw new IllegalArgumentException("Sexo invalido");
        }
        if (paciente.getDireccion() == null || paciente.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La direccion es obligatoria");
        }
        if (paciente.getTelefono() == null || paciente.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El telefono es obligatorio");
        }
        if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        //inserta al paciente usando el DAO
        boolean registrado = pacienteDAO.insert(paciente);
        if (!registrado) {
            throw new RuntimeException("Error al registrar el paciente");
        }

    }
    /**
     * actualiza un paciente existente
     * valida que el paciente exista y que los campos obligatorios no sean nulos
     * @param paciente 
     */
    public void actualizarPaciente(PacienteDTO paciente){
    if(!pacienteDAO.buscarId(paciente.getId())){
    throw new IllegalArgumentException("Paciente no existe");
    }
    //validaciones de no nulos
    if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (paciente.getEdad() == null) {
            throw new IllegalArgumentException("La edad es obligatoria");
        }
        if (paciente.getSexo() == null || 
            (!paciente.getSexo().equals("Masculino") && !paciente.getSexo().equals("Femenino"))) {
            throw new IllegalArgumentException("Sexo invalido");
        }
        if (paciente.getDireccion() == null || paciente.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La direccion es obligatoria");
        }
        if (paciente.getTelefono() == null || paciente.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El telefono es obligatorio");
        }
        
        if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
        //actualiza usando el DAOs
        boolean actualizado = pacienteDAO.update(paciente);
        if (!actualizado) {
            throw new RuntimeException("Error al actualizar el paciente");
        }
    }
    /**
     * ellimina un paciente por su ID
     * valida que no este asociado a ninguna cita
     * valida que exista antes de intentar eliminar.
     * @param id 
     */
    public void eliminarPaciente(int id) {
        if (!pacienteDAO.buscarId(id)) {
            throw new IllegalArgumentException("Paciente no existe");
        }
        boolean tieneCitas = citaDAO.existeCitaPorPaciente(id);
        if(tieneCitas){
        throw new IllegalArgumentException("No se puede eliminar el paciente por que tiene citas asociadas");
        }
        boolean eliminado = pacienteDAO.delete(id);
        if (!eliminado) {
            throw new RuntimeException("Error al eliminar el paciente");
        }
    }
    /**
     * lista a todos los pacientes llamando al DAO
     */
    public void listarPacientes() {
        boolean listados = pacienteDAO.readall();
        if (!listados) {
            throw new RuntimeException("Error al listar los pacientes");
        }
    }
    /**
     * busca un paciente por su ID
     * valida que existan antes de devolver la informacion
     * @param id 
     */
    public void buscarPaciente(int id) {
        boolean encontrado = pacienteDAO.buscarId(id);
        if (!encontrado) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
    }
}
