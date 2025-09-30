package Presentacion.paneles;

import BO.TratamientoBO;
import DAO.CitaDAO;
import DAO.TratamientoDAO;
import Dominios.TratamientoDominio;
import Presentacion.dialogs.registro.DlgRegistrarTratamiento;
import Presentacion.paneles.elementos.PnlElementoTratamiento;
import Presentacion.styles.*;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlTratamientos extends JPanel {

Style style = new Style();
    private final TratamientoBO tratamientoBO;
    private JPanel pnlListado;
    private JScrollPane scrollPane;

    // Título y encabezados (igual diseño a pacientes)
    ContainerPanel titulo = new ContainerPanel(style.frameX, 40, Color.RED, false);
    CustomLabel lblTitulo = new CustomLabel("           Tratamientos", 24);

    ContainerPanel columnas = new ContainerPanel(style.frameX, 50, Color.ORANGE, false);
    CustomLabel lblDescripcion = new CustomLabel("Descripción", style.letraSize);
    CustomLabel lblDuracion = new CustomLabel("Duración", style.letraSize);
    CustomLabel lblMedicamentos = new CustomLabel("Medicamentos", style.letraSize);
    CustomLabel lblCita = new CustomLabel("ID Cita", style.letraSize);

    CustomButton btnRegistrarTratamiento = new CustomButton("Registrar nuevo tratamiento");

    Espaciador espaciov1 = new Espaciador(10, 30);

    public PnlTratamientos() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(espaciov1);

        titulo.setLayout(new BorderLayout());
        titulo.add(lblTitulo, BorderLayout.WEST);
        add(titulo);

        columnas.setLayout(new GridLayout(1, 4));
        columnas.add(lblDescripcion);
        columnas.add(lblDuracion);
        columnas.add(lblMedicamentos);
        columnas.add(lblCita);
        add(columnas);

        pnlListado = new JPanel();
        pnlListado.setLayout(new BoxLayout(pnlListado, BoxLayout.Y_AXIS));
        pnlListado.setBackground(Color.DARK_GRAY);

        scrollPane = new JScrollPane(pnlListado);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(style.frameX, 400));
        add(scrollPane);

        // Crear BO con DAO por defecto
        tratamientoBO = new TratamientoBO(new TratamientoDAO(), new CitaDAO());

        btnRegistrarTratamiento.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                DlgRegistrarTratamiento dlg = new DlgRegistrarTratamiento(null, PnlTratamientos.this, tratamientoBO);
                dlg.setVisible(true);
            }
        });

        add(btnRegistrarTratamiento);

        refresh();
    }

    public void refresh() {
        try {
            pnlListado.removeAll();
            List<TratamientoDominio> lista = new TratamientoDAO().readall(); // o tratamientoBO.listarTratamientos() si prefieres BO
            if (lista != null) {
                for (TratamientoDominio t : lista) {
                    PnlElementoTratamiento elemento = new PnlElementoTratamiento(this, t, tratamientoBO);
                    pnlListado.add(elemento);
                    pnlListado.add(Box.createVerticalStrut(8));
                }
            }
            pnlListado.revalidate();
            pnlListado.repaint();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar tratamientos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public TratamientoBO getTratamientoBO() {
        return tratamientoBO;
    }
}
