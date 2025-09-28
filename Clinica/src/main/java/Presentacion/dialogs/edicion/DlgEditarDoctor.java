package Clinica.src.main.java.Presentacion.dialogs.edicion;

import Presentacion.paneles.PnlDoctores;
import Presentacion.paneles.elementos.PnlElementoDoctor;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgEditarDoctor extends JDialog {

    boolean testeoColor = false;
    Style style = new Style();
    String nombres, apellidoP, apellidoM, especialidad, telefono;
    PnlDoctores pnlDoctores;
    PnlElementoDoctor pnlElementoDoctor;

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Editar doctor", 32);

    CustomLabel lblNombre = new CustomLabel("Nombre completo", 24);
    CustomLabel lblEspecialidad = new CustomLabel("Especialidad", 24);
    //CustomLabel lblCorreo = new CustomLabel("Correo electrónico", 24);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", 24);


    //----------LÓGICA AQUÍ----------
    //hardcodeo de especialidades ??
    String[] especialidades = {"Cardiólogo", "Psiquiatra", "Otorrinolaringólogo"};

    //Textfields y comboboxes
    CustomComboBox cboxEspecialidades = new CustomComboBox(especialidades);


    TxtFieldPh txtfldNombres = new TxtFieldPh("Nombre(s)", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoP = new TxtFieldPh("Apellido paterno", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoM = new TxtFieldPh("Apellido materno", true, 200, 40, 24);
    //TxtFieldPh txtfldCorreo = new TxtFieldPh("Correo electrónico", true, 200, 40, 24);
    TxtFieldPh txtfldTelefono = new TxtFieldPh("Teléfono", true, 200, 40, 24);

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


    //----------LÓGICA AQUÍ-----------
    //agregar objeto tipo doctor como argumento para este constructor
    public DlgEditarDoctor(Frame parent, PnlDoctores pnlDoctores, PnlElementoDoctor pnlElementoDoctor) {

        //Setup del dialog
        super(parent, "Editar información del doctor");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);

        this.pnlDoctores = pnlDoctores;
        this.pnlElementoDoctor = pnlElementoDoctor;

        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));


        //------------LÓGICA AQUÍ----------
        //cambiar datos por los del objeto doctor
        nombres = "juanito";
        apellidoP = "perez";
        apellidoM = "lopez";
        especialidad = "Cardiólogo";
        telefono = "1234567890";

        txtfldNombres.setText(nombres);
        txtfldApellidoP.setText(apellidoP);
        txtfldApellidoM.setText(apellidoM);
        cboxEspecialidades.setSelectedItem(especialidad);
        txtfldTelefono.setText(telefono);


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

        contenido.add(lblTelefono);
        contenido.add(txtfldTelefono);


        //Botones
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarDoctor();
            }
        });
        contenido.add(btnGuardar);

        add(contenido);

    }


    public void guardarDoctor() {

        //----------LÓGICA AQUÍ----------
        //hacer cosa mágica para que se guarde el tratamiento
        //agregar validadores (?)
        //extracción de la info a strings
        nombres = txtfldNombres.getText();
        apellidoP = txtfldApellidoP.getText();
        apellidoM = txtfldApellidoM.getText();
        especialidad = cboxEspecialidades.getSelectedItem().toString();
        telefono = txtfldTelefono.getText();

        System.out.println("Haz de cuenta que se editó el doctor con los datos: " + nombres + apellidoP + apellidoM + especialidad + telefono);

        //validar si el registro fue exitoso

        pnlDoctores.refresh();
        pnlElementoDoctor.refresh();
        this.dispose();

    }
}