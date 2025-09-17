package BO;
import DTO.PacienteDTO;
import Dominios.PacienteDominio;
import Interfaces.IPacienteDAO;
import Interfaces.ICitaDAO;
import java.util.List;

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
     */
    public void registrarPaciente(PacienteDTO paciente) {
        validarPaciente(paciente);

        boolean registrado = pacienteDAO.insert(paciente);
        if (!registrado) {
            throw new RuntimeException("Error al registrar el paciente");
        }
    }

    /**
     * Actualiza un paciente existente
     */
    public void actualizarPaciente(PacienteDTO paciente) {
        PacienteDominio existente = pacienteDAO.buscarPaciente(paciente);
        
        
        if (existente == null) {
            throw new IllegalArgumentException("Paciente no existe");
        }

        validarPaciente(paciente);

        boolean actualizado = pacienteDAO.update(paciente);
        if (!actualizado) {
            throw new RuntimeException("Error al actualizar el paciente");
        }
    }

    /**
     * Elimina un paciente por su ID
     */
    public void eliminarPaciente(int id) {
        PacienteDominio paciente = pacienteDAO.buscarId(id);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no existe");
        }

        boolean tieneCitas = citaDAO.existeCitaPorPaciente(id);
        if (tieneCitas) {
            throw new IllegalArgumentException("No se puede eliminar el paciente porque tiene citas asociadas");
        }

        boolean eliminado = pacienteDAO.delete(id);
        if (!eliminado) {
            throw new RuntimeException("Error al eliminar el paciente");
        }
    }

    /**
     * Lista a todos los pacientes
     */
    public List<PacienteDominio> listarPacientes() {
        List<PacienteDominio> pacientes = pacienteDAO.readall();
        if (pacientes == null || pacientes.isEmpty()) {
            throw new RuntimeException("No se encontraron pacientes");
        }
        return pacientes;
    }

    /**
     * Busca un paciente por su ID
     */
    public PacienteDominio buscarPaciente(int id) {
        PacienteDominio paciente = pacienteDAO.buscarId(id);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
        return paciente;
    }

    /**
     * Validaciones comunes para registrar y actualizar
     */
    private void validarPaciente(PacienteDTO paciente) {
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (paciente.getEdad() <0) {
            throw new IllegalArgumentException("La edad es obligatoria");
        }
        if (paciente.getSexo() == null || 
            (!paciente.getSexo().equals("Masculino") && !paciente.getSexo().equals("Femenino"))) {
            throw new IllegalArgumentException("Sexo inválido");
        }
        if (paciente.getDireccion() == null || paciente.getDireccion().isEmpty()) {
            throw new IllegalArgumentException("La dirección es obligatoria");
        }
        if (paciente.getTelefono() == null || paciente.getTelefono().isEmpty()) {
            throw new IllegalArgumentException("El teléfono es obligatorio");
        }
        if (paciente.getEmail() == null || paciente.getEmail().isEmpty()) {
            throw new IllegalArgumentException("El email es obligatorio");
        }
    }
}

