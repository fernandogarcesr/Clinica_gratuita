package Presentacion.dialogs.detalles;

import BO.PacienteBO;
import DAO.PacienteDAO;
import Dominios.PacienteDominio;
import Presentacion.dialogs.edicion.DlgEditarPaciente;
import Presentacion.dialogs.registro.DlgRegistrarPaciente;
import Presentacion.paneles.PnlPacientes;
import Presentacion.paneles.elementos.PnlElementoPaciente;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesPaciente extends JDialog {

    boolean testeoColor = false;
    Style style = new Style();
    PnlPacientes pnlPacientes;
    PnlElementoPaciente pnlElementoPaciente;
    
    
    private PacienteDominio paciente;
    private final PacienteBO pacienteBO;

    // Labels dinámicos
    private CustomLabel lblTitulo;
    private CustomLabel lblCorreo;
    private CustomLabel lblTelefono;
    private CustomLabel lblDireccion;

    // Botones
    private final CustomButton btnEditarPaciente = new CustomButton("Editar información");
    private final CustomButton btnEliminarPaciente = new CustomButton("Eliminar paciente");

    // Contenedores
    private final ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);
    private final ContainerPanel columnas = new ContainerPanel(style.dialogX, 50, Color.RED, false);
    private final ContainerPanel informacion = new ContainerPanel(style.dialogX, 50, Color.ORANGE, false);

    // Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorv3 = new Espaciador(10, 50);

    public DlgDetallesPaciente(Frame parent, PnlPacientes pnlPacientes,
                               PnlElementoPaciente pnlElementoPaciente,
                               PacienteDominio paciente, PacienteBO pacienteBO) {

        super(parent, "Detalles del paciente");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);

        this.pnlPacientes = pnlPacientes;
        this.pnlElementoPaciente = pnlElementoPaciente;
        this.paciente = paciente;
        this.pacienteBO = pacienteBO;

        inicializarUI();
    }

    private void inicializarUI() {
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        // Título
        lblTitulo = new CustomLabel(paciente.getNombre() + " " + paciente.getApellidoPaterno(), 32);
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        // Columnas
        columnas.setLayout(new GridLayout(1, 0));
        columnas.add(new CustomLabel("Correo", 24));
        columnas.add(new CustomLabel("Teléfono", 24));
        columnas.add(new CustomLabel("Dirección", 24));
        contenido.add(columnas);

        // Información
        informacion.setLayout(new GridLayout(1, 0));
        lblCorreo = new CustomLabel(paciente.getEmail(), style.letraSize);
        lblTelefono = new CustomLabel(paciente.getTelefono(), style.letraSize);
        lblDireccion = new CustomLabel(paciente.getDireccion(), style.letraSize);

        informacion.add(lblCorreo);
        informacion.add(lblTelefono);
        informacion.add(lblDireccion);
        contenido.add(informacion);

        contenido.add(espaciadorv3);

        // Botones
        btnEditarPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editarPaciente();
            }
        });
        contenido.add(btnEditarPaciente);

        btnEliminarPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarPaciente();
            }
        });
        contenido.add(btnEliminarPaciente);

        add(contenido);
    }

    private void editarPaciente() {
        DlgRegistrarPaciente dlg = new DlgRegistrarPaciente(null, pnlPacientes, paciente, true);
        dlg.setVisible(true);
        this.dispose();
    }

    private void eliminarPaciente() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Seguro que deseas eliminar este paciente?",
                "Confirmar", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                pacienteBO.eliminarPaciente(paciente.getId_paciente());
                JOptionPane.showMessageDialog(this, "Paciente eliminado correctamente");
                pnlPacientes.refresh();
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al eliminar paciente: " + ex.getMessage());
            }
        }
    }

}
