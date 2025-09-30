package Presentacion.paneles.elementos;

import BO.PacienteBO;
import DAO.PacienteDAO;
import Dominios.PacienteDominio;
import Presentacion.dialogs.detalles.DlgDetallesPaciente;
import Presentacion.paneles.PnlPacientes;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Espaciador;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoPaciente extends JPanel {
 private Style style = new Style();
    private PnlPacientes pnlPacientes;
    private final PacienteDominio paciente;
    private final PacienteBO pacienteBO;
    private boolean testeoColor = false;
    

     private CustomLabel lblNombre;
    private CustomLabel lblEdad;
     private CustomLabel lblCorreo;
    private CustomLabel lblTelefono;
   
    //public PnlElementoPaciente(Paciente paciente) {
    public PnlElementoPaciente(PnlPacientes pnlPacientes, PacienteDominio paciente, PacienteBO pacienteBO) {
        this.pnlPacientes = pnlPacientes;    
        this.paciente = paciente;
        this.pacienteBO = pacienteBO;

        construirUI();
        inicializarEventos();
         // Estilo panel
        setMaximumSize(new Dimension(style.frameX, 50));
        setMinimumSize(new Dimension(style.frameX, 50));
        setPreferredSize(new Dimension(style.frameX, 50));
        setBackground(style.grisBase);
        setLayout(new GridLayout(1, 4));

        
    }
    
    private void construirUI() {
        setLayout(new GridLayout(1, 4));
        setPreferredSize(new Dimension(style.frameX, 50));
        setBackground(Color.LIGHT_GRAY);

        lblNombre = new CustomLabel(paciente.getNombre() + " " + paciente.getApellidoPaterno(), style.letraSize);
        lblEdad = new CustomLabel(String.valueOf(paciente.getEdad()), style.letraSize);
        lblCorreo = new CustomLabel(paciente.getEmail(), style.letraSize);
        lblTelefono = new CustomLabel(paciente.getTelefono(), style.letraSize);

        add(lblNombre);
        add(lblEdad);
        add(lblCorreo);
        add(lblTelefono);

        setOpaque(true);
    }

    private void inicializarEventos() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirDetalles();
            }
        });
    }

    private void abrirDetalles() {
        DlgDetallesPaciente dlgDetalles = new DlgDetallesPaciente(
                null, pnlPacientes, this, paciente, pacienteBO
        );
        dlgDetalles.setVisible(true);
    }

    // Para refrescar después de editar/eliminar
    public void refresh() {
        lblNombre.setText(paciente.getNombre() + " " + paciente.getApellidoPaterno());
        lblEdad.setText(String.valueOf(paciente.getEdad())); 
        lblCorreo.setText(paciente.getEmail());
        lblTelefono.setText(paciente.getTelefono());
        revalidate();
        repaint();
    }
}
