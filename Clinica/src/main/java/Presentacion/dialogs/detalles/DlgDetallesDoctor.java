package Presentacion.dialogs.detalles;

import BO.DoctorBO;
import DAO.CitaDAO;
import DAO.DoctorDAO;
import Dominios.DoctorDominio;
import Presentacion.dialogs.edicion.DlgEditarDoctor;
import Presentacion.dialogs.registro.DlgRegistrarDoctor;
import Presentacion.paneles.PnlDoctores;
import Presentacion.paneles.elementos.PnlElementoDoctor;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesDoctor extends JDialog {

  boolean testeoColor = false;
    Style style = new Style();

    private final PnlDoctores pnlDoctores;
    private final PnlElementoDoctor pnlElementoDoctor;
    private final DoctorDominio doctor;
    private final DoctorBO doctorBO;

    // Labels dinámicos
    private CustomLabel lblTitulo;
    private CustomLabel lblCorreo;
    private CustomLabel lblTelefono;
    private CustomLabel lblEspecialidad;

    // Botones
    private final CustomButton btnEditarDoctor = new CustomButton("Editar información");
    private final CustomButton btnEliminarDoctor = new CustomButton("Eliminar doctor");

    // Contenedores
    private final ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);
    private final ContainerPanel columnas = new ContainerPanel(style.dialogX, 50, Color.RED, false);
    private final ContainerPanel informacion = new ContainerPanel(style.dialogX, 50, Color.ORANGE, false);

    // Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 30);
    Espaciador espaciadorv2 = new Espaciador(10, 30);
    Espaciador espaciadorv3 = new Espaciador(10, 30);

    public DlgDetallesDoctor(Frame parent, PnlDoctores pnlDoctores,
                             PnlElementoDoctor pnlElementoDoctor,
                             DoctorDominio doctor, DoctorBO doctorBO) {

        super(parent, "Detalles del doctor", true);
        setSize(style.dimensionDialog); // mismo tamaño que pacientes
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);

        this.pnlDoctores = pnlDoctores;
        this.pnlElementoDoctor = pnlElementoDoctor;
        this.doctor = doctor;
        this.doctorBO = doctorBO;

        inicializarUI();
    }

    private void inicializarUI() {
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        // Título
        lblTitulo = new CustomLabel(doctor.getNombre() + " " + doctor.getNombre(), 32);
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        // Columnas (encabezados)
        columnas.setLayout(new GridLayout(1, 0));
        columnas.add(new CustomLabel("Correo", 24));
        columnas.add(new CustomLabel("Teléfono", 24));
        columnas.add(new CustomLabel("Especialidad", 24));
        contenido.add(columnas);

        // Datos en fila
        informacion.setLayout(new GridLayout(1, 0));
        lblCorreo = new CustomLabel(doctor.getEmail(), style.letraSize);
        lblTelefono = new CustomLabel(doctor.getTelefono(), style.letraSize);
        lblEspecialidad = new CustomLabel(doctor.getEspecialidad(), style.letraSize);

        informacion.add(lblCorreo);
        informacion.add(lblTelefono);
        informacion.add(lblEspecialidad);
        contenido.add(informacion);

        contenido.add(espaciadorv3);

        // Botones (mismo estilo que pacientes)
        btnEditarDoctor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editarDoctor();
            }
        });
        contenido.add(btnEditarDoctor);

        btnEliminarDoctor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarDoctor();
            }
        });
        contenido.add(btnEliminarDoctor);

        add(contenido);
    }

    private void editarDoctor() {
        DlgEditarDoctor dlg = new DlgEditarDoctor(
                (Frame) getOwner(), // cast para evitar error
                pnlDoctores,
                doctor,
                doctorBO
        );
        dlg.setVisible(true);
        dispose();
    }

    private void eliminarDoctor() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas eliminar este doctor?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                doctorBO.eliminarDoctor(doctor.getId_doctor());
                JOptionPane.showMessageDialog(this, "Doctor eliminado correctamente");
                pnlDoctores.refrescarListaDoctores();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar doctor: " + ex.getMessage());
            }
        }
    }
}
