package Presentacion.dialogs.edicion;

import BO.TratamientoBO;
import DTO.TratamientoDTO;
import Dominios.TratamientoDominio;
import Presentacion.paneles.PnlTratamientos;
import Presentacion.paneles.elementos.PnlElementoTratamiento;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgEditarTratamiento extends JDialog {
    
    private final TratamientoBO tratamientoBO;
    private final PnlTratamientos pnlTratamientos;
    private final TratamientoDominio tratamiento;

    CustomLabel lblTitulo = new CustomLabel("Editar tratamiento", 28);
    CustomLabel lblDuracion = new CustomLabel("Duración (días)", 20);
    CustomLabel lblMedicamentos = new CustomLabel("Medicamentos", 20);

    TxtFieldPh txtDuracion = new TxtFieldPh("Duración", true, 200, 40, 16);
    TxtFieldPh txtMedicamentos = new TxtFieldPh("Medicamentos", true, 400, 40, 16);

    CustomButton btnGuardar = new CustomButton("Guardar cambios");

    public DlgEditarTratamiento(Frame owner, PnlTratamientos pnlTratamientos, TratamientoDominio tratamiento, TratamientoBO tratamientoBO) {
        super(owner, "Editar tratamiento", true);
        this.tratamientoBO = tratamientoBO;
        this.pnlTratamientos = pnlTratamientos;
        this.tratamiento = tratamiento;
        inicializarUI();
    }

    private void inicializarUI() {
        setSize(new Style().dimensionDialog);
        setLocationRelativeTo(getOwner());

        ContainerPanel contenido = new ContainerPanel(new Style().dialogX, new Style().dialogY, new Style().grisDialog, true);
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        contenido.add(lblTitulo);
        contenido.add(lblDuracion);
        txtDuracion.setText(tratamiento.getDuracion());
        contenido.add(txtDuracion);

        contenido.add(lblMedicamentos);
        txtMedicamentos.setText(tratamiento.getMedicamentos());
        contenido.add(txtMedicamentos);

        btnGuardar.addActionListener(e -> guardar());
        contenido.add(btnGuardar);

        add(contenido);
    }

    private void guardar() {
        try {
            TratamientoDTO dto = new TratamientoDTO();
            dto.setDuracion(txtDuracion.getText().trim());
            dto.setMedicamentos(txtMedicamentos.getText().trim());
            dto.setIdCita(tratamiento.getId_cita()); // se usa en update query del DAO actual

            tratamientoBO.actualizarTratamiento(dto); // tu BO espera DTO; si tu BO tiene otro método, llama al correcto
            JOptionPane.showMessageDialog(this, "Tratamiento actualizado correctamente");
            if (pnlTratamientos != null) pnlTratamientos.refresh();
            dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

