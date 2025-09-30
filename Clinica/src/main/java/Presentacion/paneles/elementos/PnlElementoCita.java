package Presentacion.paneles.elementos;

import Presentacion.dialogs.detalles.DlgDetallesCita;
import Presentacion.paneles.PnlCitas;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoCita extends JPanel {
 private Style style = new Style();
    private PnlCitas pnlCitas;
    private PnlElementoCita pnlElementoCita;
    
     private CustomLabel lblMedico;
    private CustomLabel lblPaciente;
    private CustomLabel lblFechaHora;
    private CustomLabel lblEstado;
     private int idCita;
     private String motivo;


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo cita

    //public PnlElementoCita(PnlCitas pnlCitas, Cita cita) {
    public PnlElementoCita(int idCita, String nombreMedico, String nombrePaciente,
                           String fechaHora, String estado, String motivo,PnlCitas pnlCitas) {

        // Guardar referencias
        this.idCita = idCita;
        this.pnlCitas = pnlCitas;
        this.pnlElementoCita = this;
        this.motivo = motivo;

        // Establecimiento de panel
        setMaximumSize(new Dimension(style.frameX, 50));
        setMinimumSize(new Dimension(style.frameX, 50));
        setPreferredSize(new Dimension(style.frameX, 50));
        setBackground(style.grisBase);
        setLayout(new GridLayout(1, 4)); // columnas igual que en PnlCitas

        // Crear labels con los datos
        lblMedico = new CustomLabel(nombreMedico, style.letraSize);
        lblPaciente = new CustomLabel(nombrePaciente, style.letraSize);
        lblFechaHora = new CustomLabel(fechaHora, style.letraSize);
        lblEstado = new CustomLabel(estado, style.letraSize);

        // Agregar labels al panel
        add(lblMedico);
        add(lblPaciente);
        add(lblFechaHora);
        add(lblEstado);

        // Eventos 
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(style.grisBaseHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(style.grisBase);
            }

            // Mostrar info al hacer click
            @Override
            public void mouseClicked(MouseEvent e) {
                // Ahora pasas la info real a DlgDetallesCita
                DlgDetallesCita detalles = new DlgDetallesCita(null, pnlCitas, pnlElementoCita, 
                        idCita, nombreMedico, nombrePaciente, fechaHora, estado, motivo);
                detalles.setVisible(true);
            }
        });
    }

    public void refresh() {
    
        System.out.println("refresh pnlElementoCitas");

        revalidate();
        repaint();
    }


}
