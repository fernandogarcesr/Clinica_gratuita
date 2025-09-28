package Presentacion.dialogs.registro;

import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgRegistrarDoctor extends JDialog {

    boolean testeoColor = false;
    Style style = new Style();
    String nombres, apellidoP, apellidoM, especialidad, correo, telefono;

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Registrar doctor", 32);

    CustomLabel lblNombre = new CustomLabel("Nombre completo", 24);
    CustomLabel lblEspecialidad = new CustomLabel("Especialidad", 24);
    CustomLabel lblCorreo = new CustomLabel("Correo electrónico", 24);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", 24);


    //hardcodeo de sexos (???)
    String[] especialidades = {"Cardiólogo", "Psiquiatra", "Otorrinolaringólogo"};

    //Textfields y comboboxes
    CustomComboBox cboxEspecialidades = new CustomComboBox(especialidades);


    TxtFieldPh txtfldNombres = new TxtFieldPh("Nombre(s)", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoP = new TxtFieldPh("Apellido paterno", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoM = new TxtFieldPh("Apellido materno", true, 200, 40, 24);
    TxtFieldPh txtfldCorreo = new TxtFieldPh("Correo electrónico", true, 200, 40, 24);
    TxtFieldPh txtfldTelefono = new TxtFieldPh("Teléfono", true, 200, 40, 24);

    //Botones
    CustomButton btnGuardar = new CustomButton("Registrar");



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


    public DlgRegistrarDoctor(Frame parent) {

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

        contenido.add(lblNombre);
        contenido.add(txtfldNombres);
        contenido.add(txtfldApellidoP);
        contenido.add(txtfldApellidoM);

        contenido.add(lblEspecialidad);
        contenido.add(cboxEspecialidades);

        contenido.add(lblCorreo);
        contenido.add(txtfldCorreo);

        contenido.add(lblTelefono);
        contenido.add(txtfldTelefono);


        //Botones
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                registrarDoctor();
            }
        });
        contenido.add(btnGuardar);

        add(contenido);

    }


    public void registrarDoctor() {

        //----------LÓGICA AQUÍ----------
        //hacer cosa mágica para que se guarde el tratamiento
        //agregar validadores (?)
        //extracción de la info a strings
        nombres = txtfldNombres.getText();
        apellidoP = txtfldApellidoP.getText();
        apellidoM = txtfldApellidoM.getText();
        especialidad = cboxEspecialidades.getSelectedItem().toString();
        correo = txtfldCorreo.getText();
        telefono = txtfldTelefono.getText();

        System.out.println("Haz de cuenta que se registró el doctor con los datos: " + nombres + apellidoP + apellidoM + especialidad + correo + telefono);

        //validar si el registro fue exitoso
        this.dispose();

    }
}