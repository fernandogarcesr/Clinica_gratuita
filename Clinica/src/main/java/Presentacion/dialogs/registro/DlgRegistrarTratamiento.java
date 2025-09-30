package Presentacion.dialogs.registro;

import BO.TratamientoBO;
import DTO.TratamientoDTO;
import Presentacion.paneles.PnlTratamientos;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgRegistrarTratamiento extends JDialog{
private final TratamientoBO tratamientoBO;
    private final PnlTratamientos pnlTratamientos;

    // UI
    CustomLabel lblTitulo = new CustomLabel("Registrar tratamiento", 32);
    CustomLabel lblDescripcion = new CustomLabel("Descripción", 24);
    CustomLabel lblDuracion = new CustomLabel("Duración (días)", 24);
    CustomLabel lblMedicamentos = new CustomLabel("Medicamentos", 24);
    CustomLabel lblIdCita = new CustomLabel("ID de cita (num)", 24);

    TxtFieldPh txtDescripcion = new TxtFieldPh("Descripción", true, 400, 40, 18);
    TxtFieldPh txtDuracion = new TxtFieldPh("Duración (ej. 7)", true, 200, 40, 18);
    TxtFieldPh txtMedicamentos = new TxtFieldPh("Medicamentos", true, 400, 40, 18);
    TxtFieldPh txtIdCita = new TxtFieldPh("Id de cita (número)", true, 200, 40, 18);

    CustomButton btnGuardar = new CustomButton("Registrar");

    ContainerPanel contenido = new ContainerPanel(new Style().dialogX, new Style().dialogY, new Style().grisDialog, true);

        public DlgRegistrarTratamiento(Frame owner, PnlTratamientos pnlTratamientos, TratamientoBO tratamientoBO) {
        super(owner, "Registrar tratamiento", true);
        this.tratamientoBO = tratamientoBO;
        this.pnlTratamientos = pnlTratamientos;

        inicializarUI();
    }

    private void inicializarUI() {
        setSize(new Style().dimensionDialog);
        setLocationRelativeTo(getOwner());

        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        contenido.add(lblTitulo);
        contenido.add(lblDescripcion);
        contenido.add(txtDescripcion);
        contenido.add(lblDuracion);
        contenido.add(txtDuracion);
        contenido.add(lblMedicamentos);
        contenido.add(txtMedicamentos);
        contenido.add(lblIdCita);
        contenido.add(txtIdCita);

        btnGuardar.addActionListener(e -> guardar());
        contenido.add(btnGuardar);

        add(contenido);
    }

    private void guardar() {
        try {
            String descripcion = txtDescripcion.getText().trim();
            String duracion = txtDuracion.getText().trim();
            String medicamentos = txtMedicamentos.getText().trim();
            int idCita = Integer.parseInt(txtIdCita.getText().trim()); // si quieres select combos reemplazar

            TratamientoDTO dto = new TratamientoDTO();
            dto.setDescripcion(descripcion);
            dto.setDuracion(duracion);
            dto.setMedicamentos(medicamentos);
            dto.setIdCita(idCita);

            tratamientoBO.registrarTratamiento(dto);
            JOptionPane.showMessageDialog(this, "Tratamiento registrado correctamente");
            if (pnlTratamientos != null) pnlTratamientos.refresh();
            dispose();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Id de cita inválido (debe ser número).");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al registrar: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}

