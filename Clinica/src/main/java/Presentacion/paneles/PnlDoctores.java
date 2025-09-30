    package Presentacion.paneles;

import BO.DoctorBO;
import DAO.CitaDAO;
import DAO.DoctorDAO;
import Dominios.DoctorDominio;
import Presentacion.dialogs.registro.DlgRegistrarDoctor;
import Presentacion.paneles.elementos.PnlElementoDoctor;
import Presentacion.styles.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlDoctores extends JPanel {
      
private final DoctorBO doctorBO;
    private JPanel pnlListado;
    private JScrollPane scrollPane;

    Style style = new Style();
    boolean testeoColor = false;
    PnlDoctores pnlDoctores = this;

    // Título
    ContainerPanel titulo = new ContainerPanel(style.frameX, 40, Color.RED, testeoColor);
    CustomLabel lblTitulo = new CustomLabel("           Doctores", 24);

    // Columnas
    ContainerPanel columnas = new ContainerPanel(style.frameX, 50, Color.ORANGE, false);
    CustomLabel lblNombre = new CustomLabel("Nombre", style.letraSize);
    CustomLabel lblEspecialidad = new CustomLabel("Especialidad", style.letraSize);
    CustomLabel lblCorreo = new CustomLabel("Correo", style.letraSize);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", style.letraSize);

    // Botón
    CustomButton btnRegistrarDoctor = new CustomButton("Registrar nuevo doctor");

    // Espaciador
    Espaciador espaciov1 = new Espaciador(10, 30);

    public PnlDoctores() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(testeoColor);
        add(espaciov1);

        titulo.setLayout(new BorderLayout());
        titulo.add(lblTitulo, BorderLayout.WEST);
        add(titulo);

        // Sub-encabezado (columnas)
        columnas.setLayout(new GridLayout(1, 4));
        columnas.add(lblNombre);
        columnas.add(lblEspecialidad);
        columnas.add(lblCorreo);
        columnas.add(lblTelefono);
        add(columnas);

        // Panel listado dentro de scroll
        pnlListado = new JPanel();
        pnlListado.setLayout(new BoxLayout(pnlListado, BoxLayout.Y_AXIS));
        pnlListado.setBackground(Color.DARK_GRAY);

        scrollPane = new JScrollPane(pnlListado);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(style.frameX, 400));
        add(scrollPane);

        // Botón registrar
        btnRegistrarDoctor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DlgRegistrarDoctor registrar = new DlgRegistrarDoctor(null, pnlDoctores, null, false, doctorBO);
                registrar.setVisible(true);
            }
        });
        add(btnRegistrarDoctor);

        doctorBO = new DoctorBO(new DoctorDAO(), new CitaDAO());
        refrescarListaDoctores();
    }

    public void refrescarListaDoctores() {
        try {
            pnlListado.removeAll();

            List<DoctorDominio> doctores = doctorBO.listarDoctores();

            for (DoctorDominio d : doctores) {
                PnlElementoDoctor pnl = new PnlElementoDoctor(this, d, doctorBO);
                pnlListado.add(pnl);
                pnlListado.add(Box.createVerticalStrut(10)); // espacio entre cards
            }

            pnlListado.revalidate();
            pnlListado.repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar doctores: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public DoctorBO getDoctorBO() {
        return doctorBO;
    }
}