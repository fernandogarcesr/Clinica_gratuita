package Presentacion.paneles;

import BO.PacienteBO;
import DAO.CitaDAO;
import DAO.PacienteDAO;
import DTO.PacienteDTO;
import Dominios.PacienteDominio;
import Presentacion.dialogs.registro.DlgRegistrarPaciente;
import Presentacion.paneles.elementos.PnlElementoPaciente;
import Presentacion.styles.*;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlPacientes extends JPanel {

    DAO.PacienteDAO pacienteDAO = new DAO.PacienteDAO();
    DAO.CitaDAO citaDAO = new DAO.CitaDAO();

    private PacienteBO pacienteBO;
    Style style = new Style();
    PnlPacientes pnlPacientes = this;
    boolean testeoColor = false;
    private JPanel pnlListado;
    private JScrollPane scrollPane;

    // Título
    ContainerPanel titulo = new ContainerPanel(style.frameX, 40, Color.RED, testeoColor);
    CustomLabel lblTitulo = new CustomLabel("           Pacientes", 24);

    // Columnas
    ContainerPanel columnas = new ContainerPanel(style.frameX, 50, Color.ORANGE, false);
    CustomLabel lblNombre = new CustomLabel("Nombre", style.letraSize);
    CustomLabel lblEdad = new CustomLabel("Edad", style.letraSize);
    CustomLabel lblCorreo = new CustomLabel("Correo", style.letraSize);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", style.letraSize);


    //Botón
    CustomButton btnRegistrarPaciente = new CustomButton("Registrar nuevo paciente");

    //Espaciadores
    Espaciador espaciov1 = new Espaciador(10, 30);

    public PnlPacientes() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

       
        setOpaque(testeoColor);
        add(espaciov1);

        titulo.setLayout(new BorderLayout());
        titulo.add(lblTitulo, BorderLayout.WEST);
        add(titulo);

        // Sub-encabezado (columnas)
        columnas.setLayout(new GridLayout(1, 4));
        columnas.add(lblNombre);
        columnas.add(lblEdad);
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

        btnRegistrarPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DlgRegistrarPaciente registrar = new DlgRegistrarPaciente(null, pnlPacientes, pacienteBO);
                registrar.setVisible(true);
            }
        });
        add(btnRegistrarPaciente);

        pacienteBO = new PacienteBO(new PacienteDAO(), new CitaDAO());

        refresh();

    }

    public void refresh() {
        try {
            pnlListado.removeAll();

            PacienteDAO pacienteDAO = new PacienteDAO();
            List<PacienteDominio> pacientes = pacienteDAO.readall();

            for (PacienteDominio p : pacientes) {
                PnlElementoPaciente pnl = new PnlElementoPaciente(this, p, pacienteBO);
                pnlListado.add(pnl);
                pnlListado.add(Box.createVerticalStrut(10)); // espacio entre cards
            }

            pnlListado.revalidate();
            pnlListado.repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar pacientes: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
    public PacienteBO getPacienteBO() {
    return pacienteBO;
}
}
