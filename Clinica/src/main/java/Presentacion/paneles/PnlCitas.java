package Presentacion.paneles;

import DAO.CitaDAO;
import DAO.DoctorDAO;
import DAO.PacienteDAO;
import Dominios.CitaDominio;
import Dominios.DoctorDominio;
import Dominios.PacienteDominio;
import Presentacion.dialogs.registro.DlgRegistrarCita;
import Presentacion.paneles.elementos.PnlElementoCita;
import Presentacion.styles.*;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlCitas extends JPanel {
    private JPanel pnlListado;
    private javax.swing.JScrollPane scrollPane;
    Style style = new Style();
    boolean testeoColor = false;
    PnlCitas pnlCitas = this;

    //Título
    ContainerPanel titulo = new ContainerPanel(style.frameX, 40, Color.RED, testeoColor);
    CustomLabel lblTitulo = new CustomLabel("           Citas", 24);

    //Columnas
    ContainerPanel columnas = new ContainerPanel(style.frameX, 50, Color.ORANGE, testeoColor);
    CustomLabel lblMedico = new CustomLabel("Médico", style.letraSize);
    CustomLabel lblPaciente = new CustomLabel("Paciente", style.letraSize);
    CustomLabel lblFechaHora = new CustomLabel("Fecha y hora", style.letraSize);
    CustomLabel lblEstado = new CustomLabel("Estado", style.letraSize);

    //Botón
    CustomButton btnAgendarCita = new CustomButton("Agendar nueva cita");

    //Espaciadores
    Espaciador espaciov1 = new Espaciador(10, 30);

    Espaciador espacioh1 = new Espaciador(100, 10);
    Espaciador espacioh2 = new Espaciador(150, 10);
    Espaciador espacioh3 = new Espaciador(80, 10);
    Espaciador espacioh4 = new Espaciador(80, 10);

    public boolean x = false;

    public PnlCitas() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //pnlCitas = this;

        add(espaciov1);

        //Encabezado
        titulo.setLayout(new BorderLayout());
        titulo.add(lblTitulo, BorderLayout.WEST);
        add(titulo);

        columnas.setLayout(new GridLayout(1, 4));
        columnas.add(lblMedico);
        columnas.add(lblPaciente);
        columnas.add(lblFechaHora);
        columnas.add(lblEstado);
        add(columnas);
        
        // Panel listado de citas dentro de scroll
        pnlListado = new JPanel();
        pnlListado.setLayout(new BoxLayout(pnlListado, BoxLayout.Y_AXIS));
        pnlListado.setBackground(Color.DARK_GRAY);
        
         scrollPane = new JScrollPane(pnlListado);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(style.frameX, 400));
        add(scrollPane);
 

        btnAgendarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                DlgRegistrarCita registrar = new DlgRegistrarCita(null, pnlCitas);
                registrar.setVisible(true);


            }
        });
        add(btnAgendarCita);

        setOpaque(testeoColor);
        setVisible(true);
        
        refresh();//cargar datos al inicio

    }

    public void refresh() {

        try {
            // limpiar panel
            pnlListado.removeAll();

            // usar DAO para obtener todas las citas
            CitaDAO citaDAO = new CitaDAO();
            List<CitaDominio> listCitas = citaDAO.readall();

            // obtener doctores y pacientes
            DoctorDAO doctorDAO = new DoctorDAO();
            List<DoctorDominio> docs = doctorDAO.readall();

            PacienteDAO pacienteDAO = new PacienteDAO();
            List<PacienteDominio> pacs = pacienteDAO.readall();

            for (CitaDominio cdom : listCitas) {
                int idCita = cdom.getId_citas();
                int idDoc = cdom.getId_doctor();
                int idPac = cdom.getId_paciente();
                String fechaHora = cdom.getFechaHora() != null ? cdom.getFechaHora().toString() : "";
                String motivo = cdom.getMotivo() != null ? cdom.getMotivo() : "";
                String estado = cdom.getEstado() != null ? cdom.getEstado().name(): "";

                String nombreDoc = docs.stream()
                        .filter(d -> d.getId_doctor() == idDoc)
                        .map(DoctorDominio::getNombre)
                        .findFirst().orElse("Doctor N/D");

                String nombrePac = pacs.stream()
                        .filter(p -> p.getId_paciente() == idPac)
                        .map(PacienteDominio::getNombre)
                        .findFirst().orElse("Paciente N/D");

                // Crear panel de cita con tus estilos (PnlElementoCita)
                PnlElementoCita elemento = new PnlElementoCita(idCita, nombreDoc, nombrePac, fechaHora, estado, motivo, this);
                pnlListado.add(elemento);
                pnlListado.add(Box.createVerticalStrut(10)); // espacio entre elementos
            }

            pnlListado.revalidate();
            pnlListado.repaint();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar citas: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
