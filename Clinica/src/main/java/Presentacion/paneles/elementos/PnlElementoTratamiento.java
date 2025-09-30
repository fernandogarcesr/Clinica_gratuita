package Presentacion.paneles.elementos;

import BO.TratamientoBO;
import Dominios.TratamientoDominio;
import Presentacion.dialogs.detalles.DlgDetallesTratamiento;
import Presentacion.paneles.PnlTratamientos;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoTratamiento extends JPanel {
 private Style style = new Style();
    private final PnlTratamientos pnlTratamientos;
    private final TratamientoDominio tratamiento;
    private final TratamientoBO tratamientoBO;

    private CustomLabel lblDescripcion;
    private CustomLabel lblDuracion;
    private CustomLabel lblMedicamentos;
    private CustomLabel lblCita;

    public PnlElementoTratamiento(PnlTratamientos pnlTratamientos, TratamientoDominio tratamiento, TratamientoBO tratamientoBO) {
        this.pnlTratamientos = pnlTratamientos;
        this.tratamiento = tratamiento;
        this.tratamientoBO = tratamientoBO;

        construirUI();
        inicializarEventos();

        setMaximumSize(new Dimension(style.frameX, 50));
        setMinimumSize(new Dimension(style.frameX, 50));
        setPreferredSize(new Dimension(style.frameX, 50));
        setBackground(style.grisBase);
    }

    private void construirUI() {
        setLayout(new GridLayout(1, 4));
        setBackground(style.grisBase);

        lblDescripcion = new CustomLabel(tratamiento.getDescripcion(), style.letraSize);
        lblDuracion = new CustomLabel(tratamiento.getDuracion(), style.letraSize);
        lblMedicamentos = new CustomLabel(tratamiento.getMedicamentos(), style.letraSize);
        lblCita = new CustomLabel(String.valueOf(tratamiento.getId_cita()), style.letraSize);

        add(lblDescripcion);
        add(lblDuracion);
        add(lblMedicamentos);
        add(lblCita);

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
        DlgDetallesTratamiento dlg = new DlgDetallesTratamiento((Frame) SwingUtilities.getWindowAncestor(this),
                pnlTratamientos, tratamiento, tratamientoBO);
        dlg.setVisible(true);
    }

    public void refresh() {
        lblDescripcion.setText(tratamiento.getDescripcion());
        lblDuracion.setText(tratamiento.getDuracion());
        lblMedicamentos.setText(tratamiento.getMedicamentos());
        lblCita.setText(String.valueOf(tratamiento.getId_cita()));
        revalidate();
        repaint();
    }
}
