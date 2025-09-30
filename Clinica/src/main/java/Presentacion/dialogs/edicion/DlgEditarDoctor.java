package Presentacion.dialogs.edicion;

import BO.DoctorBO;
import DAO.CitaDAO;
import DAO.DoctorDAO;
import DTO.DoctorDTO;
import Dominios.DoctorDominio;
import Presentacion.paneles.PnlDoctores;
import Presentacion.paneles.elementos.PnlElementoDoctor;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgEditarDoctor extends JDialog {

    boolean testeoColor = false;
    Style style = new Style();

    // referencias al panel principal y al elemento (puede ser null)
    private PnlDoctores pnlDoctores;
    private PnlElementoDoctor pnlElementoDoctor;

    // doctor que se está editando (puede ser null si es creación)
    private DoctorDominio doctorEdicion;
    private DoctorBO doctorBO;

    // UI
    CustomLabel lblTitulo = new CustomLabel("Editar doctor", 32);
    CustomLabel lblNombre = new CustomLabel("Nombre", 24);
    CustomLabel lblEspecialidad = new CustomLabel("Especialidad", 24);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", 24);
    CustomLabel lblEmail = new CustomLabel("Correo", 24);

    String[] especialidades = {"Cardiólogo", "Psiquiatra", "Otorrinolaringólogo", "Pediatría", "General"};

    CustomComboBox cboxEspecialidades = new CustomComboBox(especialidades);

    TxtFieldPh txtfldNombres = new TxtFieldPh("Nombre(s)", true, 200, 40, 24);
    TxtFieldPh txtfldTelefono = new TxtFieldPh("Teléfono", true, 200, 40, 24);
    TxtFieldPh txtfldEmail = new TxtFieldPh("Correo electrónico", true, 200, 40, 24);

    CustomButton btnGuardar = new CustomButton("Guardar");

    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);

    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);

    // -------------------- CONSTRUCTORES --------------------

    // Constructor antiguo (mantengo para compatibilidad si hay llamadas previas)
    public DlgEditarDoctor(Frame parent, PnlDoctores pnlDoctores, PnlElementoDoctor pnlElementoDoctor) {
        super(parent, "Editar información del doctor");
        this.pnlDoctores = pnlDoctores;
        this.pnlElementoDoctor = pnlElementoDoctor;
        this.doctorEdicion = null;
        this.doctorBO = new DoctorBO(new DoctorDAO(), new CitaDAO());
        initUI(parent);
    }

    // Nuevo constructor - usado desde DlgDetallesDoctor: (Frame, PnlDoctores, DoctorDominio, DoctorBO)
    public DlgEditarDoctor(Frame parent, PnlDoctores pnlDoctores, DoctorDominio doctor, DoctorBO doctorBO) {
        super(parent, "Editar información del doctor");
        this.pnlDoctores = pnlDoctores;
        this.pnlElementoDoctor = null; // no tenemos el elemento individual aquí
        this.doctorEdicion = doctor;
        this.doctorBO = doctorBO != null ? doctorBO : new DoctorBO(new DoctorDAO(), new CitaDAO());
        initUI(parent);
        if (doctor != null) cargarDatosDoctor(doctor);
    }

    // -------------------- UI / Lógica --------------------

    private void initUI(Frame parent) {
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);

        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        contenido.add(lblNombre);
        contenido.add(txtfldNombres);

        contenido.add(lblEspecialidad);
        contenido.add(cboxEspecialidades);

        contenido.add(lblTelefono);
        contenido.add(txtfldTelefono);

        contenido.add(lblEmail);
        contenido.add(txtfldEmail);

        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarDoctor();
            }
        });
        contenido.add(btnGuardar);

        add(contenido);
    }

    private void cargarDatosDoctor(DoctorDominio d) {
        txtfldNombres.setText(d.getNombre());
        cboxEspecialidades.setSelectedItem(d.getEspecialidad());
        txtfldTelefono.setText(d.getTelefono());
        txtfldEmail.setText(d.getEmail());
    }

    private void guardarDoctor() {
        try {
            // Construir DTO (el BO espera DTO)
            DoctorDTO dto = new DoctorDTO();
            dto.setNombre(txtfldNombres.getText().trim());
            dto.setEspecialidad((String) cboxEspecialidades.getSelectedItem());
            dto.setTelefono(txtfldTelefono.getText().trim());
            dto.setEmail(txtfldEmail.getText().trim());

            if (doctorEdicion != null) {
                // Editando: llamar a BO con id + DTO
                doctorBO.actualizarDoctor(doctorEdicion.getId_doctor(), dto);
                JOptionPane.showMessageDialog(this, "Doctor actualizado correctamente");
            } else {
                // Nuevo
                doctorBO.registrarDoctor(dto);
                JOptionPane.showMessageDialog(this, "Doctor registrado correctamente");
            }

            // Refrescar lista en el panel principal si existe (usa el método que tengas: refrescarListaDoctores() o refresh())
            if (pnlDoctores != null) {
                try {
                    // preferimos llamar al método 'refrescarListaDoctores' si existe
                    pnlDoctores.getClass().getMethod("refrescarListaDoctores").invoke(pnlDoctores);
                } catch (NoSuchMethodException nsme) {
                    // si no existe, intentamos 'refresh'
                    try {
                        pnlDoctores.getClass().getMethod("refresh").invoke(pnlDoctores);
                    } catch (Exception ignore) {
                        // si tampoco existe, no hacemos nada
                    }
                } catch (Exception ex) {
                    // fallback: intentar refrescar por revalidate/repaint (seguro nunca falla)
                    pnlDoctores.revalidate();
                    pnlDoctores.repaint();
                }
            }

            // Refrescar elemento si fue provisto
            if (pnlElementoDoctor != null) {
                try {
                    pnlElementoDoctor.getClass().getMethod("refresh").invoke(pnlElementoDoctor);
                } catch (Exception ignored) {
                }
            }

            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar doctor: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}