package Presentacion.dialogs.detalles;

import BO.TratamientoBO;
import Dominios.TratamientoDominio;
import Presentacion.dialogs.edicion.DlgEditarTratamiento;
import Presentacion.paneles.PnlTratamientos;
import Presentacion.paneles.elementos.PnlElementoTratamiento;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesTratamiento extends JDialog {
 private final PnlTratamientos pnlTratamientos;
    private final TratamientoDominio tratamiento;
    private final TratamientoBO tratamientoBO;
    Style style = new Style();

    private CustomLabel lblTitulo;
    private CustomLabel lblDescripcion;
    private CustomLabel lblDuracion;
    private CustomLabel lblMedicamentos;
    private CustomLabel lblCita;

    CustomButton btnEditar = new CustomButton("Editar");
    CustomButton btnEliminar = new CustomButton("Eliminar");

    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);
    ContainerPanel columnas = new ContainerPanel(style.dialogX, 50, Color.RED, false);
    ContainerPanel informacion = new ContainerPanel(style.dialogX, 50, Color.ORANGE, false);

    Espaciador esp1 = new Espaciador(10, 50);
    Espaciador esp2 = new Espaciador(10, 50);

    public DlgDetallesTratamiento(Frame owner, PnlTratamientos pnlTratamientos, TratamientoDominio tratamiento, TratamientoBO tratamientoBO) {
        super(owner, "Detalles del tratamiento", true);
        this.pnlTratamientos = pnlTratamientos;
        this.tratamiento = tratamiento;
        this.tratamientoBO = tratamientoBO;
        inicializarUI();
    }

    private void inicializarUI() {
        setSize(style.dimensionDialog);
        setLocationRelativeTo(getOwner());
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        lblTitulo = new CustomLabel(tratamiento.getDescripcion(), 28);
        contenido.add(esp1);
        contenido.add(lblTitulo);
        contenido.add(esp2);

        columnas.setLayout(new GridLayout(1, 0));
        columnas.add(new CustomLabel("Duración", 24));
        columnas.add(new CustomLabel("Medicamentos", 24));
        columnas.add(new CustomLabel("ID Cita", 24));
        contenido.add(columnas);

        informacion.setLayout(new GridLayout(1, 0));
        lblDuracion = new CustomLabel(tratamiento.getDuracion(), style.letraSize);
        lblMedicamentos = new CustomLabel(tratamiento.getMedicamentos(), style.letraSize);
        lblCita = new CustomLabel(String.valueOf(tratamiento.getId_cita()), style.letraSize);
        informacion.add(lblDuracion);
        informacion.add(lblMedicamentos);
        informacion.add(lblCita);
        contenido.add(informacion);

        contenido.add(Box.createVerticalStrut(20));

        btnEditar.addActionListener(e -> {
            DlgEditarTratamiento dlgEd = new DlgEditarTratamiento((Frame) getOwner(), pnlTratamientos, tratamiento, tratamientoBO);
            dlgEd.setVisible(true);
            dispose();
        });
        contenido.add(btnEditar);

        btnEliminar.addActionListener(e -> {
            int ok = JOptionPane.showConfirmDialog(this, "¿Eliminar tratamiento?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                try {
                    tratamientoBO.eliminarTratamiento(tratamiento.getId_tratamiento());
                    JOptionPane.showMessageDialog(this, "Tratamiento eliminado");
                    if (pnlTratamientos != null) pnlTratamientos.refresh();
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
                }
            }
        });
        contenido.add(btnEliminar);

        add(contenido);
    }
  
}