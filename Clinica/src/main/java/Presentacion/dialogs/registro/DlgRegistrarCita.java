package Clinica.src.main.java.Presentacion.dialogs.registro;

import Presentacion.paneles.PnlCitas;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgRegistrarCita extends JDialog {

    boolean testeoColor = false;
    Style style = new Style();
    String doctor, dia, mes, anio, hora, paciente, motivo;
    PnlCitas pnlCitas;

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Agendar nueva cita", 32);

    CustomLabel lblDoctor = new CustomLabel("Doctor", 24);
    CustomLabel lblFecha = new CustomLabel("Fecha", 24);
    CustomLabel lblHora = new CustomLabel("Hora", 24);
    CustomLabel lblPaciente = new CustomLabel("Paciente", 24);
    CustomLabel lblMotivo = new CustomLabel("Motivo", 24);


    //----------LÓGICA AQUI----------
    //Contenido de las comboboxes de ejemplo: cambiar por arrays con la info correcta de las opciones disponibles en la base
    String[] doctores = {"Dr.Pancracio López", "Dra. Juliana Pérez", "Dr. Simi"};
    String[] fechaDias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] fechaMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    String[] fechaAnios = {"2025", "2026"};
    String[] horas = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"};
    String[] pacientes = {"Fulanito D´tal", "Juan Pérez", "Hermenegildo García"};
    //----------FIN DE LÓGICA----------


    //Textfields y comboboxes
    CustomComboBox cboxDoctores = new CustomComboBox(doctores);
    CustomComboBox cboxFechaDias = new CustomComboBox(fechaDias);
    CustomComboBox cboxFechaMeses = new CustomComboBox(fechaMeses);
    CustomComboBox cboxFechaAnios = new CustomComboBox(fechaAnios);
    CustomComboBox cboxHoras = new CustomComboBox(horas);
    CustomComboBox cboxPacientes = new CustomComboBox(pacientes);
    TxtFieldPh txtfldMotivo = new TxtFieldPh("Motivo", true, 200, 40, 24);

    //Botones
    CustomButton btnAgendarCita = new CustomButton("Agendar cita");



    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorh1 = new Espaciador(10, 10);


    public DlgRegistrarCita(Frame parent, PnlCitas pnlCitas) {

        //Setup del dialog
        super(parent, "Agendar cita");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);
        this.pnlCitas = pnlCitas;


        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Contenido
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        contenido.add(lblDoctor);
        contenido.add(cboxDoctores);

        contenido.add(lblFecha);
        contenido.add(cboxFechaDias);
        contenido.add(cboxFechaMeses);
        contenido.add(cboxFechaAnios);

        contenido.add(lblHora);
        contenido.add(cboxHoras);

        contenido.add(lblPaciente);
        contenido.add(cboxPacientes);

        contenido.add(lblMotivo);
        contenido.add(txtfldMotivo);


        //Botones
        btnAgendarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                agendarCita();
            }
        });
        contenido.add(btnAgendarCita);



        add(contenido);

        //setVisible(true);

    }


    //----------LÓGICA AQUÍ----------

    public void agendarCita() {

        //hacer cosa mágica para que se agende la cita actual
        //extracción de la info a strings (dr y paciente deben ser objetos en vez de strings)
        doctor = cboxDoctores.getSelectedItem().toString();
        dia = cboxFechaDias.getSelectedItem().toString();
        mes = cboxFechaMeses.getSelectedItem().toString();
        anio = cboxFechaAnios.getSelectedItem().toString();
        hora = cboxDoctores.getSelectedItem().toString();
        paciente = cboxPacientes.getSelectedItem().toString();
        motivo = txtfldMotivo.getText();

        System.out.println("Haz de cuenta que se agendó la cita con los datos: " + doctor + dia + mes + anio + hora + paciente + motivo );

        //Meter la info a la BD
        //Crear y agregar nuevo elemento a la lista ???

        pnlCitas.refresh();

        this.dispose();

    }

    //----------FIN DE LÓGICA----------

}
