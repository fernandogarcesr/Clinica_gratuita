package Presentacion.dialogs.registro;

import BO.PacienteBO;
import DAO.CitaDAO;
import DAO.PacienteDAO;
import DTO.PacienteDTO;
import Dominios.PacienteDominio;
import Presentacion.paneles.PnlPacientes;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.Period;

public class DlgRegistrarPaciente extends JDialog {
   private PacienteDAO pacienteDAO;
   private CitaDAO citaDAO;
     private final PacienteBO pacienteBO;
    private final PnlPacientes pnlPacientes;

    // Variables de modo edición
    private boolean modoEdicion = false;
    private PacienteDominio pacienteEdicion;

    // Labels
    CustomLabel lblTitulo = new CustomLabel("Registrar paciente", 32);
    CustomLabel lblNombre = new CustomLabel("Nombre completo", 24);
    CustomLabel lblSexo = new CustomLabel("Sexo", 24);
    CustomLabel lblFechaN = new CustomLabel("Fecha de nacimiento", 24);
    CustomLabel lblCorreo = new CustomLabel("Correo electrónico", 24);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", 24);
    CustomLabel lblDireccion = new CustomLabel("Dirección", 24);

    // Combos
    String[] sexos = {"Masculino", "Femenino", "OTRO"};
    CustomComboBox cboxSexos = new CustomComboBox(Dominios.ENUM.Sexo.values());
    CustomComboBox cboxFechaDias = new CustomComboBox(generarDias());
    CustomComboBox cboxFechaMeses = new CustomComboBox(generarMeses());
    CustomComboBox cboxFechaAnios;

    // Inputs
    TxtFieldPh txtfldNombres = new TxtFieldPh("Nombre(s)", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoP = new TxtFieldPh("Apellido paterno", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoM = new TxtFieldPh("Apellido materno", true, 200, 40, 24);
    TxtFieldPh txtfldCorreo = new TxtFieldPh("Correo electrónico", true, 200, 40, 24);
    TxtFieldPh txtfldTelefono = new TxtFieldPh("Teléfono", true, 200, 40, 24);
    TxtFieldPh txtfldDireccion = new TxtFieldPh("Dirección", true, 200, 40, 24);

    // Botón
    CustomButton btnGuardar = new CustomButton("Registrar");

    // Contenedor
    ContainerPanel contenido = new ContainerPanel(new Style().dialogX, new Style().dialogY, new Style().grisDialog, true);

    // ---------- CONSTRUCTOR REGISTRO ----------
    public DlgRegistrarPaciente(Frame parent, PnlPacientes pnlPacientes, PacienteBO pacienteBO) {
        super(parent, "Registrar paciente", true);
        this.pnlPacientes = pnlPacientes;
        this.pacienteBO = pacienteBO;
        inicializar(false, null);
    }

    // ---------- CONSTRUCTOR EDICIÓN ----------
    public DlgRegistrarPaciente(Frame parent, PnlPacientes pnlPacientes,
                            PacienteDominio pacienteEdicion, boolean modoEdicion) {
        super(parent, "Editar paciente", true);
        this.pnlPacientes = pnlPacientes;
        this.pacienteBO = new PacienteBO(pacienteDAO, citaDAO);
        this.modoEdicion = modoEdicion;
        this.pacienteEdicion = pacienteEdicion;
        inicializar(true, pacienteEdicion);
    }

    // ---------- INICIALIZAR UI ----------
    private void inicializar(boolean esEdicion, PacienteDominio paciente) {
        setSize(new Style().dimensionDialog);
        setLocationRelativeTo(getParent());
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        // Años dinámicos
        int anioActual = LocalDate.now().getYear();
        String[] anios = new String[anioActual - 1920 + 1];
        for (int i = 0; i <= anioActual - 1920; i++) {
            anios[i] = String.valueOf(1920 + i);
        }
        cboxFechaAnios = new CustomComboBox(anios);

        // Contenido
        contenido.add(lblTitulo);
        contenido.add(lblNombre);
        contenido.add(txtfldNombres);
        contenido.add(txtfldApellidoP);
        contenido.add(txtfldApellidoM);

        contenido.add(lblSexo);
        contenido.add(cboxSexos);

        contenido.add(lblFechaN);
        contenido.add(cboxFechaDias);
        contenido.add(cboxFechaMeses);
        contenido.add(cboxFechaAnios);

        contenido.add(lblCorreo);
        contenido.add(txtfldCorreo);

        contenido.add(lblTelefono);
        contenido.add(txtfldTelefono);

        contenido.add(lblDireccion);
        contenido.add(txtfldDireccion);

        // Botón guardar
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarPaciente();
            }
        });
        contenido.add(btnGuardar);

        add(contenido);

        // Si es edición, precargar datos
        if (esEdicion && paciente != null) {
            precargarDatos(paciente);
        }
    }

    // ---------- PRECARGAR DATOS EN EDICIÓN ----------
    private void precargarDatos(PacienteDominio p) {
        lblTitulo.setText("Editar paciente");
        btnGuardar.setText("Guardar cambios");

        txtfldNombres.setText(p.getNombre());
        txtfldApellidoP.setText(p.getApellidoPaterno());
        txtfldApellidoM.setText(p.getApellidoMaterno());
        txtfldCorreo.setText(p.getEmail());
        txtfldTelefono.setText(p.getTelefono());
        txtfldDireccion.setText(p.getDireccion());

        if (p.getSexo() != null) {
            try {
                Dominios.ENUM.Sexo sexoEnum = Dominios.ENUM.Sexo.valueOf(p.getSexo());
                cboxSexos.setSelectedItem(sexoEnum);
            } catch (IllegalArgumentException e) {
                // Si en BD hay un valor inesperado, no truena
                cboxSexos.setSelectedIndex(-1);
            }
        }

        // Edad -> aproximar año
        int anoAprox = LocalDate.now().getYear() - p.getEdad();
        cboxFechaAnios.setSelectedItem(String.valueOf(anoAprox));
        cboxFechaMeses.setSelectedItem("Enero");
        cboxFechaDias.setSelectedItem("1");
    }

    // ---------- GUARDAR PACIENTE ----------
    private void guardarPaciente() {
        try {
            String nombres = txtfldNombres.getText().trim();
            String apellidoP = txtfldApellidoP.getText().trim();
            String apellidoM = txtfldApellidoM.getText().trim();
            String correo = txtfldCorreo.getText().trim();
            String telefono = txtfldTelefono.getText().trim();
            String direccion = txtfldDireccion.getText().trim();

            Dominios.ENUM.Sexo sexoEnum = (Dominios.ENUM.Sexo) cboxSexos.getSelectedItem();
            String sexoStr = sexoEnum != null ? sexoEnum.name() : null;

            int dia = Integer.parseInt((String) cboxFechaDias.getSelectedItem());
            int mes = mapMesEspANumero((String) cboxFechaMeses.getSelectedItem());
            int anio = Integer.parseInt((String) cboxFechaAnios.getSelectedItem());
            LocalDate fechaNac = LocalDate.of(anio, mes, dia);
            int edad = Period.between(fechaNac, LocalDate.now()).getYears();

            PacienteDTO p = new PacienteDTO();
            p.setNombre(nombres);
            p.setApellidoPaterno(apellidoP);
            p.setApellidoMaterno(apellidoM);
            p.setEdad(edad);
            p.setTelefono(telefono);
            p.setEmail(correo);
            p.setDireccion(direccion);
            p.setSexo(sexoStr);

            if (modoEdicion && pacienteEdicion != null) {
    // Pasar el id del dominio al BO
    pacienteBO.actualizarPaciente(pacienteEdicion.getId_paciente(), p);
    JOptionPane.showMessageDialog(this, "Paciente actualizado correctamente");
} else {
    pacienteBO.registrarPaciente(p);
    JOptionPane.showMessageDialog(this, "Paciente registrado correctamente");
}

            pnlPacientes.refresh();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar paciente: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    // ---------- UTILS ----------
    private static String[] generarDias() {
        String[] dias = new String[31];
        for (int i = 0; i < 31; i++) dias[i] = String.valueOf(i + 1);
        return dias;
    }

    private static String[] generarMeses() {
        return new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    }

    private int mapMesEspANumero(String mes) {
        switch (mes.toLowerCase()) {
            case "enero": return 1;
            case "febrero": return 2;
            case "marzo": return 3;
            case "abril": return 4;
            case "mayo": return 5;
            case "junio": return 6;
            case "julio": return 7;
            case "agosto": return 8;
            case "septiembre": return 9;
            case "octubre": return 10;
            case "noviembre": return 11;
            case "diciembre": return 12;
            default: return 1;
        }
    }
}
