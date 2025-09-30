package Presentacion.dialogs.detalles;

import Presentacion.paneles.PnlCitas;
import Presentacion.paneles.elementos.PnlElementoCita;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesCita extends JDialog {
     private Style style = new Style();
    private PnlCitas pnlCitas;
    private PnlElementoCita pnlElementoCita;

    boolean testeoColor = false;
   

    //Labels para mostrar los detalles
    private CustomLabel lblDoctor;
    private CustomLabel lblPaciente;
    private CustomLabel lblFechaHora;
    private CustomLabel lblEstado;
    private JTextArea txtMotivo;


    //Boton
    CustomButton btnCancelarCita = new CustomButton("Marcar como cancelada", 1, 200, 30);
    CustomButton btnAtenderCita = new CustomButton("Marcar como en curso", 1, 200, 30);
    CustomButton btnCompletarCita = new CustomButton("Marcar como completada", 1, 200, 30);

    private int idCita;
    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);
    ContainerPanel columnas = new ContainerPanel(style.dialogX, 50, Color.RED, testeoColor);
    ContainerPanel informacion = new ContainerPanel(style.dialogX, 50, Color.ORANGE, testeoColor);
    ContainerPanel botones = new ContainerPanel(style.dialogX, 50, Color.PINK, testeoColor);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorv3 = new Espaciador(10, 50);
    Espaciador espaciadorv4 = new Espaciador(10, 50);

    Espaciador espaciadorh1 = new Espaciador(10, 10);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo cita

    //public DlgDetallesCita(Frame parent, PnlCitas pnlCitas, Cita cita) {
    public DlgDetallesCita(Frame parent, PnlCitas pnlCitas, PnlElementoCita pnlElementoCita,
                           int idCita, String nombreDoctor, String nombrePaciente,
                           String fechaHora, String estado, String motivo) {

          super(parent, "Detalles de cita", true);
        this.pnlCitas = pnlCitas;
        this.pnlElementoCita = pnlElementoCita;
        this.idCita = idCita;

        setSize(style.dimensionDialog); // usa tu tamaño estandar
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);
        setLayout(new BorderLayout(10, 10));
       

    

        // Panel central con la info
        JPanel pnlInfo = new JPanel();
        pnlInfo.setLayout(new BoxLayout(pnlInfo, BoxLayout.Y_AXIS));
        pnlInfo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lblDoctor = new CustomLabel("Médico: " + nombreDoctor, style.letraSize);
        lblPaciente = new CustomLabel("Paciente: " + nombrePaciente, style.letraSize);
        lblFechaHora = new CustomLabel("Fecha y hora: " + fechaHora, style.letraSize);
        lblEstado = new CustomLabel("Estado: " + estado, style.letraSize);

        txtMotivo = new JTextArea("Motivo: " + motivo);
        txtMotivo.setWrapStyleWord(true);
        txtMotivo.setLineWrap(true);
        txtMotivo.setEditable(false);
        txtMotivo.setBackground(getBackground());

        pnlInfo.add(lblDoctor);
        pnlInfo.add(lblPaciente);
        pnlInfo.add(lblFechaHora);
        pnlInfo.add(lblEstado);
        pnlInfo.add(Box.createVerticalStrut(10));
        pnlInfo.add(txtMotivo);

        add(pnlInfo, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        // Acciones de estado
        btnCancelarCita.addActionListener(e -> actualizarEstado("Cancelada"));
        btnAtenderCita.addActionListener(e -> actualizarEstado("En curso"));
        btnCompletarCita.addActionListener(e -> actualizarEstado("Completada"));


        JButton btnCerrar = new JButton("Cerrar");
        

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de eliminar esta cita?", "Confirmar",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    DAO.CitaDAO citaDAO = new DAO.CitaDAO();
                    citaDAO.delete(idCita);
                    JOptionPane.showMessageDialog(this, "Cita eliminada correctamente");
                    dispose();
                    pnlCitas.refresh(); // recarga la lista
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar cita: " + ex.getMessage());
                }
            }
        });
        
        //cerrar
        btnCerrar.addActionListener(e -> dispose());

        pnlBotones.add(btnCancelarCita);
        pnlBotones.add(btnAtenderCita);
        pnlBotones.add(btnCompletarCita);
        pnlBotones.add(btnEliminar);
        pnlBotones.add(btnCerrar);

        add(pnlBotones, BorderLayout.SOUTH);
    }
       
    // Metodo auxiliar para cambiar estado en BD
    private void actualizarEstado(String nuevoEstado) {
        try {
            DAO.CitaDAO citaDAO = new DAO.CitaDAO();
            citaDAO.updateEstado(idCita, nuevoEstado);
            JOptionPane.showMessageDialog(this, "Cita marcada como " + nuevoEstado);
            pnlCitas.refresh();
            pnlElementoCita.refresh();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar estado: " + ex.getMessage());
        }
    }
    


}
