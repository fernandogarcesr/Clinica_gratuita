package Presentacion.dialogs.detalles;

import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesCita extends JDialog {

    //WIP


    //------------LÓGICA AQUÍ-----------
    //cambiar por getters del DTO
    String nombreCita = "C-002";
    String nombreDoctor = "Dr.Pancracio Lopez";
    String nombrePaciente = "Fulanito D´tal";
    String fechaHora = "dd/mm/aaaa 00:00hrs";
    String estado = "En curso";
    String motivo = "lele pancha";

    //buscar forma de


    boolean testeoColor = false;

    Style style = new Style();
    //int dlgX = 500, dlgY = 600;

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Cita " + nombreCita, 32);

    CustomLabel lblColMedico = new CustomLabel("        Médico      ", 24);
    CustomLabel lblMedico = new CustomLabel(nombreDoctor, style.letraSize);

    CustomLabel lblColPaciente = new CustomLabel("      Paciente        ", 24);
    CustomLabel lblPaciente = new CustomLabel(nombrePaciente, style.letraSize);

    CustomLabel lblColFechaHora = new CustomLabel("     Fecha y hora        ", 24);
    CustomLabel lblFechaHora = new CustomLabel(fechaHora, style.letraSize);

    CustomLabel lblColEstado = new CustomLabel("        Estado      ", 24);
    CustomLabel lblEstado = new CustomLabel(estado, style.letraSize);

    CustomLabel lblColMotivo = new CustomLabel("        Motivo      ", 24);
    CustomLabel lblMotivo = new CustomLabel(motivo, style.letraSize);


    //Boton
    CustomButton btnEliminarCita = new CustomButton("Eliminar cita");

    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);
    ContainerPanel columnas = new ContainerPanel(style.dialogX, 50, Color.RED, testeoColor);
    ContainerPanel informacion = new ContainerPanel(style.dialogX, 50, Color.ORANGE, testeoColor);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorv3 = new Espaciador(10, 50);
    Espaciador espaciadorv4 = new Espaciador(10, 50);

    Espaciador espaciadorh1 = new Espaciador(10, 10);



    public DlgDetallesCita(Frame parent) {

        //Setup del dialog
        //super(parent, "Detalles de cita", true);
        super(parent, "Detalles de cita");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Título
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        //Info de la cita
        columnas.setLayout(new GridLayout(1, 0));
        columnas.add(lblColMedico);
        columnas.add(lblColPaciente);
        columnas.add(lblColFechaHora);
        columnas.add(lblColEstado);
        contenido.add(columnas);

        informacion.setLayout(new GridLayout(1, 0));
        informacion.add(lblMedico);
        informacion.add(lblPaciente);
        informacion.add(lblFechaHora);
        informacion.add(lblEstado);
        contenido.add(informacion);

        contenido.add(espaciadorv3);
        contenido.add(lblColMotivo);
        contenido.add(lblMotivo);

        contenido.add(espaciadorv4);

        //Tratamientos
        //insertar cosa de los tratamientos

        //Botones
        btnEliminarCita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarCita();
            }
        });
        contenido.add(btnEliminarCita);



        add(contenido);

        //setVisible(true);

    }


    public void eliminarCita() {

        //----------LÓGICA AQUÍ----------
        //hacer cosa mágica para que se elimine la cita actual
        System.out.println("Haz de cuenta que se eliminó la cita");
        this.dispose();

    }

}
