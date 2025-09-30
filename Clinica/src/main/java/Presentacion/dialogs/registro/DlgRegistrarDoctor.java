package Presentacion.dialogs.registro;

import BO.DoctorBO;
import DAO.CitaDAO;
import DAO.DoctorDAO;
import DTO.DoctorDTO;
import Dominios.DoctorDominio;
import Presentacion.paneles.PnlDoctores;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgRegistrarDoctor extends JDialog {

   private final DoctorBO doctorBO;
    private final PnlDoctores pnlDoctores;

    // --- Modo edición ---
    private boolean modoEdicion = false;
    private DoctorDominio doctorEdicion;

    // --- Labels ---
    private final CustomLabel lblTitulo = new CustomLabel("Registrar doctor", 32);
    private final CustomLabel lblNombre = new CustomLabel("Nombre", 24);
    private final CustomLabel lblTelefono = new CustomLabel("Teléfono", 24);
    private final CustomLabel lblCorreo = new CustomLabel("Correo", 24);
    private final CustomLabel lblEspecialidad = new CustomLabel("Especialidad", 24);

    // --- Inputs ---
    private final TxtFieldPh txtNombre = new TxtFieldPh("Nombre completo", true, 200, 40, 24);
    private final TxtFieldPh txtTelefono = new TxtFieldPh("Teléfono", true, 200, 40, 24);
    private final TxtFieldPh txtCorreo = new TxtFieldPh("Correo", true, 200, 40, 24);
    private final CustomComboBox cboxEspecialidad =
            new CustomComboBox(new String[]{"General", "Odontólogo", "Ortodoncia", "Implantología"});

    // --- Botón principal ---
    private final CustomButton btnGuardar = new CustomButton("Registrar");

    // --- Contenedor principal ---
    private final ContainerPanel contenido =
            new ContainerPanel(new Style().dialogX, new Style().dialogY, new Style().grisDialog, true);

    // ---------- CONSTRUCTOR REGISTRO ----------
    public DlgRegistrarDoctor(Frame parent, PnlDoctores pnlDoctores, DoctorBO doctorBO) {
        super(parent, "Registrar doctor", true);
        this.pnlDoctores = pnlDoctores;
        this.doctorBO = doctorBO;
        inicializar(false, null);
    }

    // ---------- CONSTRUCTOR EDICIÓN ----------
    public DlgRegistrarDoctor(Frame parent, PnlDoctores pnlDoctores,
                              DoctorDominio doctorEdicion, boolean modoEdicion, DoctorBO doctorBO) {
        super(parent, "Editar doctor", true);
        this.pnlDoctores = pnlDoctores;
        this.doctorBO = doctorBO;
        this.modoEdicion = modoEdicion;
        this.doctorEdicion = doctorEdicion;
        inicializar(true, doctorEdicion);
    }

    // ---------- INICIALIZAR UI ----------
    private void inicializar(boolean esEdicion, DoctorDominio doctor) {
        setSize(new Style().dimensionDialog);
        setLocationRelativeTo(getParent());
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        // --- Construcción UI ---
        contenido.add(lblTitulo);

        contenido.add(lblNombre);
        contenido.add(txtNombre);

        contenido.add(lblTelefono);
        contenido.add(txtTelefono);

        contenido.add(lblCorreo);
        contenido.add(txtCorreo);

        contenido.add(lblEspecialidad);
        contenido.add(cboxEspecialidad);

        // --- Botón guardar ---
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarDoctor();
            }
        });
        contenido.add(btnGuardar);

        add(contenido);

        // Precargar datos si es edición
        if (esEdicion && doctor != null) {
            precargarDatos(doctor);
        }
    }

    // ---------- PRECARGAR DATOS ----------
    private void precargarDatos(DoctorDominio d) {
        lblTitulo.setText("Editar doctor");
        btnGuardar.setText("Guardar cambios");

        txtNombre.setText(d.getNombre());
        txtTelefono.setText(d.getTelefono());
        txtCorreo.setText(d.getEmail());
        cboxEspecialidad.setSelectedItem(d.getEspecialidad());
    }

    // ---------- GUARDAR DOCTOR ----------
    private void guardarDoctor() {
        try {
            // Obtener datos
            String nombre = txtNombre.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String correo = txtCorreo.getText().trim();
            String especialidad = (String) cboxEspecialidad.getSelectedItem();

            DoctorDTO dto = new DoctorDTO();
            dto.setNombre(nombre);
            dto.setTelefono(telefono);
            dto.setEmail(correo);
            dto.setEspecialidad(especialidad);

            if (modoEdicion && doctorEdicion != null) {
                // Actualizar (pasar el id del dominio y el DTO)
                doctorBO.actualizarDoctor(doctorEdicion.getId_doctor(), dto);
                JOptionPane.showMessageDialog(this, "Doctor actualizado correctamente");
            } else {
                // Registrar nuevo
                doctorBO.registrarDoctor(dto);
                JOptionPane.showMessageDialog(this, "Doctor registrado correctamente");
            }

            pnlDoctores.refrescarListaDoctores();
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar doctor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
