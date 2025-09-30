package Presentacion.paneles.elementos;

import BO.DoctorBO;
import DAO.CitaDAO;
import DAO.DoctorDAO;
import Dominios.DoctorDominio;
import Presentacion.dialogs.detalles.DlgDetallesDoctor;

import Presentacion.paneles.PnlDoctores;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoDoctor extends JPanel {

    private final PnlDoctores pnlDoctores;
    private final DoctorDominio doctor;
    private final DoctorBO doctorBO;
    private Style style = new Style();

    private CustomLabel lblNombre;
    private CustomLabel lblEspecialidad;
    private CustomLabel lblCorreo;
    private CustomLabel lblTelefono;

    public PnlElementoDoctor(PnlDoctores pnlDoctores, DoctorDominio doctor, DoctorBO doctorBO) {
        this.pnlDoctores = pnlDoctores;
        this.doctor = doctor;
        this.doctorBO = doctorBO;

        construirUI();
        inicializarEventos();

        setMaximumSize(new Dimension(style.frameX, 50));
        setPreferredSize(new Dimension(style.frameX, 50));
        setBackground(style.grisBase);
        setLayout(new GridLayout(1, 4));
    }

    private void construirUI() {
        lblNombre = new CustomLabel(doctor.getNombre(), style.letraSize);
        lblEspecialidad = new CustomLabel(doctor.getEspecialidad(), style.letraSize);
        lblCorreo = new CustomLabel(doctor.getEmail(), style.letraSize);
        lblTelefono = new CustomLabel(doctor.getTelefono(), style.letraSize);

        add(lblNombre);
        add(lblEspecialidad);
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
    
    DlgDetallesDoctor dlgDetalles = new DlgDetallesDoctor(
            (Frame) SwingUtilities.getWindowAncestor(this), 
            pnlDoctores,                                   
            this,                                 
            doctor,                                        
            doctorBO                                    
    );
    dlgDetalles.setVisible(true);}

    public void refresh() {
        lblNombre.setText(doctor.getNombre());
        lblEspecialidad.setText(doctor.getEspecialidad());
        lblCorreo.setText(doctor.getEmail());
        lblTelefono.setText(doctor.getTelefono());
        revalidate();
        repaint();
    }
}
