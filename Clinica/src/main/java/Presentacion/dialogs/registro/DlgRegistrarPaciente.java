package Presentacion.dialogs.registro;

import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgRegistrarPaciente extends JDialog {

    boolean testeoColor = false;
    Style style = new Style();
    String nombres, apellidoP, apellidoM, sexo, diaN, mesN, anioN, correo, telefono;

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Registrar paciente", 32);

    CustomLabel lblNombre = new CustomLabel("Nombre completo", 24);
    CustomLabel lblSexo = new CustomLabel("Sexo", 24);
    CustomLabel lblFechaN = new CustomLabel("Fecha de nacimiento", 24);
    CustomLabel lblCorreo = new CustomLabel("Correo electrónico", 24);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", 24);
    CustomLabel lblDireccion = new CustomLabel("Dirección", 24);


    //hardcodeo de sexos (???)
    String[] sexos = {"Masculino", "Femenino", "Helicóptero apache"};
    String[] fechaDias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] fechaMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    String[] fechaAnios = {"2025", "2026"};

    //Textfields y comboboxes
    CustomComboBox cboxSexos = new CustomComboBox(sexos);
    CustomComboBox cboxFechaDias = new CustomComboBox(fechaDias);
    CustomComboBox cboxFechaMeses = new CustomComboBox(fechaMeses);
    CustomComboBox cboxFechaAnios = new CustomComboBox(fechaAnios);

    TxtFieldPh txtfldNombres = new TxtFieldPh("Nombre(s)", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoP = new TxtFieldPh("Apellido paterno", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoM = new TxtFieldPh("Apellido materno", true, 200, 40, 24);
    TxtFieldPh txtfldCorreo = new TxtFieldPh("Correo electrónico", true, 200, 40, 24);
    TxtFieldPh txtfldTelefono = new TxtFieldPh("Teléfono", true, 200, 40, 24);
    TxtFieldPh txtfldDireccion = new TxtFieldPh("Dirección", true, 200, 40, 24);

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


    public DlgRegistrarPaciente(Frame parent) {

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

        contenido.add(lblSexo);
        contenido.add(cboxSexos);

        contenido.add(lblFechaN);
        contenido.add(cboxFechaDias);
        contenido.add(cboxFechaMeses);
        contenido.add(cboxFechaAnios);

        contenido.add(lblCorreo);
        contenido.add(txtfldCorreo);

        contenido.add(lblTelefono);
        contenido.add(txtfldTelefono);

        contenido.add(lblDireccion);
        contenido.add(txtfldDireccion);


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
        sexo = cboxSexos.getSelectedItem().toString();
        diaN = cboxFechaDias.getSelectedItem().toString();
        mesN = cboxFechaMeses.getSelectedItem().toString();
        anioN = cboxFechaAnios.getSelectedItem().toString();
        correo = txtfldCorreo.getText();
        telefono = txtfldTelefono.getText();

        System.out.println("Haz de cuenta que se registró el paciente con los datos: " + nombres + apellidoP + apellidoM + sexo + diaN + mesN + anioN + correo + telefono);

    }
}