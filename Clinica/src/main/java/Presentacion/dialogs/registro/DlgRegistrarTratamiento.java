package Presentacion.dialogs.registro;

import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgRegistrarTratamiento extends JDialog{

    boolean testeoColor = false;
    Style style = new Style();
    String cita, tratamiento, duracion, descripcion;

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Recetar tratamiento", 32);

    CustomLabel lblCita = new CustomLabel("Cita correspondiente", 24);
    CustomLabel lblTratamiento = new CustomLabel("Nombre del tratamiento", 24);
    CustomLabel lblDuracion = new CustomLabel("Duración del tratamiento", 24);
    CustomLabel lblDescripcion = new CustomLabel("Descripcion (opcional)", 24);


    //----------LÓGICA AQUI----------
    //Contenido del cbox de ejemplo: cambiar por array con la info correcta
    String[] citas = {"C-001", "C-002", "C-003"};

    //Textfields y comboboxes
    CustomComboBox cboxCitas = new CustomComboBox(citas);

    TxtFieldPh txtfldTratamiento = new TxtFieldPh("Tratamiento", true, 200, 40, 24);
    TxtFieldPh txtfldDuracion = new TxtFieldPh("Duración", true, 200, 40, 24);
    TxtFieldPh txtfldDescripcion = new TxtFieldPh("Descripción", true, 200, 40, 24);

    //Botones
    CustomButton btnGuardar = new CustomButton("Guardar");



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


    public DlgRegistrarTratamiento(Frame parent) {

        //Setup del dialog
        //super(parent, "Detalles de cita", true);
        super(parent, "Agendar cita");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);
        //setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Contenido
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        contenido.add(lblCita);
        contenido.add(cboxCitas);

        contenido.add(lblTratamiento);
        contenido.add(txtfldTratamiento);

        contenido.add(lblDuracion);
        contenido.add(txtfldDuracion);

        contenido.add(lblDescripcion);
        contenido.add(txtfldDescripcion);


        //Botones
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarTratamiento();
            }
        });
        contenido.add(btnGuardar);


        add(contenido);

    }


    public void guardarTratamiento() {

        //----------LÓGICA AQUÍ----------
        //hacer cosa mágica para que se guarde el tratamiento
        //extracción de la info a strings
        cita = cboxCitas.getSelectedItem().toString();
        tratamiento = txtfldTratamiento.getText();
        duracion = txtfldDuracion.getText();
        descripcion = txtfldDescripcion.getText();

        System.out.println("Haz de cuenta que se guardó el tratamiento con los datos: " + cita + tratamiento + duracion + descripcion);

    }
}

