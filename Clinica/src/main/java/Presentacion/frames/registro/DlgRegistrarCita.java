package Presentacion.frames.registro;

import Presentacion.styles.CustomButton;
import Presentacion.styles.CustomComboBox;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.TxtFieldPh;

import javax.swing.*;

public class DlgRegistrarCita extends JDialog {

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Agendar nueva cita", 32);
    CustomLabel lblEspecialidad = new CustomLabel("Especialidad", 24);
    CustomLabel lblFecha = new CustomLabel("Fecha", 24);
    CustomLabel lblPaciente = new CustomLabel("Paciente", 24);
    CustomLabel lblMotivo = new CustomLabel("Motivo", 24);

    //Botones
    CustomButton agendarCita = new CustomButton("Agendar cita");

    //Textfields y comboboxes
    //TxtFieldPh txtfldDoctor = new TxtFieldPh("Dr", true, 200, 40, 24);
    CustomComboBox cboxEspecialidad = new CustomComboBox(null);
    TxtFieldPh txtfldFecha = new TxtFieldPh("Fecha", true, 200, 40, 24);
    CustomComboBox cboxPaciente = new CustomComboBox(null);

    public DlgRegistrarCita() {

    }
}
