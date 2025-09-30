package BO;

import DAO.PacienteDAO;
import DTO.PacienteDTO;
import Dominios.PacienteDominio;
import Interfaces.IPacienteDAO;
import Interfaces.ICitaDAO;
import java.sql.SQLException;
import java.util.List;

/**
 * Clase de negocio encargada de gestionar las operaciones relacionadas con
 * pacientes. Valida datos, verifica relaciones con citas médicas, y delega las
 * operaciones CRUD al DAO correspondiente.
 */
public class PacienteBO {

    private IPacienteDAO pacienteDAO;
    private ICitaDAO citaDAO;

    /**
     * Constructor que recibe las dependencias necesarias para operar sobre
     * pacientes.
     *
     * @param pacienteDAO DAO para operaciones de paciente.
     * @param citaDAO DAO para validación de citas asociadas.
     */
    public PacienteBO(IPacienteDAO pacienteDAO, ICitaDAO citaDAO) {
        this.pacienteDAO = new PacienteDAO();
        this.citaDAO = citaDAO;
    }

    /**
     * Registra un nuevo paciente en el sistema. Valida los campos obligatorios
     * antes de delegar al DAO.
     *
     * @param paciente Objeto DTO con los datos del paciente a registrar.
     * @throws IllegalArgumentException si los datos son inválidos.
     * @throws RuntimeException si ocurre un error al insertar el paciente.
     */
    public int registrarPaciente(PacienteDTO paciente) throws Exception {
        validarPaciente(paciente);

        int id = pacienteDAO.insert(paciente);
        if (id == -1) {
            throw new Exception("Error al registrar paciente");
        }
        return id;

    }

    /**
     * Actualiza los datos de un paciente existente. Verifica que el paciente
     * exista y valida los campos antes de actualizar.
     *
     * @param paciente Objeto DTO con los nuevos datos del paciente.
     * @throws IllegalArgumentException si el paciente no existe o los datos son
     * inválidos.
     * @throws RuntimeException si ocurre un error al actualizar el paciente.
     */
    public void actualizarPaciente(int id_paciente,PacienteDTO paciente) {
        // Validar DTO antes de convertir
    validarPaciente(paciente);

    // Convertir DTO → Dominio
    PacienteDominio dominio = convertirDTOaDominio(paciente);
    dominio.setId_paciente(id_paciente);

    // Buscar si existe en la BD
    PacienteDominio existente = pacienteDAO.buscarId(id_paciente);
    if (existente == null) {
        throw new IllegalArgumentException("El paciente con id " + id_paciente + " no existe");
    }

    // Actualizar en la BD
    boolean actualizado = pacienteDAO.update(dominio);
    if (!actualizado) {
        throw new RuntimeException("Error al actualizar el paciente");
    }
    }

    /**
     * Elimina un paciente por su identificador. Verifica que el paciente exista
     * y que no tenga citas asociadas.
     *
     * @param id Identificador del paciente a eliminar.
     * @throws IllegalArgumentException si el paciente no existe o tiene citas
     * asociadas.
     * @throws RuntimeException si ocurre un error al eliminar el paciente.
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
     * Recupera todos los pacientes registrados en el sistema.
     *
     * @return Lista de objetos PacienteDominio con la información completa de
     * cada paciente.
     * @throws RuntimeException si no se encuentran pacientes o ocurre un error
     * de lectura.
     */
    public List<PacienteDominio> listarPacientes() {
        List<PacienteDominio> pacientes = pacienteDAO.readall();
        if (pacientes == null || pacientes.isEmpty()) {
            throw new RuntimeException("No se encontraron pacientes");
        }
        return pacientes;
    }

    /**
     * Busca un paciente por su identificador único.
     *
     * @param id Identificador del paciente.
     * @return Objeto PacienteDominio si se encuentra; null si no existe.
     * @throws IllegalArgumentException si el paciente no se encuentra.
     */
    public PacienteDominio buscarPaciente(int id) {
        PacienteDominio paciente = pacienteDAO.buscarId(id);
        if (paciente == null) {
            throw new IllegalArgumentException("Paciente no encontrado");
        }
        return paciente;
    }

    /**
     * Valida los campos obligatorios de un paciente. Verifica que nombre, edad,
     * sexo, dirección, teléfono y email estén presentes y sean válidos.
     *
     * @param paciente Objeto DTO a validar.
     * @throws IllegalArgumentException si algún campo obligatorio es nulo o
     * inválido.
     */
    private void validarPaciente(PacienteDTO paciente) {
        if (paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (paciente.getEdad() < 0) {
            throw new IllegalArgumentException("La edad es obligatoria");
        }
        if (paciente.getSexo() == null
                || (!paciente.getSexo().equals("MASCULINO") && !paciente.getSexo().equals("FEMENINO") && !paciente.getSexo().equals("OTRO"))) {
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

    private PacienteDominio convertirDTOaDominio(PacienteDTO dto) {
        PacienteDominio dominio = new PacienteDominio();
        dominio.setNombre(dto.getNombre());
        dominio.setApellidoPaterno(dto.getApellidoPaterno());
        dominio.setApellidoMaterno(dto.getApellidoMaterno());
        dominio.setEmail(dto.getEmail());
        dominio.setTelefono(dto.getTelefono());
        dominio.setDireccion(dto.getDireccion());
        dominio.setEdad(dto.getEdad());   // 👈 aquí estaba faltando
        dominio.setSexo(dto.getSexo());
        // 👇 no se pone id aquí, porque lo asignas después en actualizarPaciente
        return dominio;
    }
}
