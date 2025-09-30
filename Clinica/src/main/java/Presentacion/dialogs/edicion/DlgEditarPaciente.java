package Presentacion.dialogs.edicion;

import Presentacion.paneles.PnlPacientes;
import Presentacion.paneles.elementos.PnlElementoPaciente;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgEditarPaciente extends JDialog {

    boolean testeoColor = false;
    Style style = new Style();
    PnlPacientes pnlPacientes;
    PnlElementoPaciente pnlElementoPaciente;

    //----------PLACEHOLDER HARCODEADO-----------
    String nombres = "Fulanito";
    String apellidoP = "D´tal";
    String apellidoM = "Gonzales";
    String sexo = "Masculino";
    String diaN = "1";
    String mesN = "Enero";
    String anioN = "2025";
    String telefono = "1234567890";
    String direccion = "Calle de las Sirenas";
    //----------FIN DEL PLACEHOLDER-----------


    //Labels
    CustomLabel lblTitulo = new CustomLabel("Editar paciente", 32);

    CustomLabel lblNombre = new CustomLabel("Nombre completo", 24);
    CustomLabel lblSexo = new CustomLabel("Sexo", 24);
    CustomLabel lblFechaN = new CustomLabel("Fecha de nacimiento", 24);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", 24);
    CustomLabel lblDireccion = new CustomLabel("Dirección", 24);


    //----------LÓGICA AQUÍ----------- ???
    //hardcodeo de sexos (???)
    String[] sexos = {"Masculino", "Femenino", "Helicóptero apache"};
    String[] fechaDias = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
    String[] fechaMeses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    String[] fechaAnios = {"2025", "2026"};
    //----------FIN DE LÓGICA-----------


    //Textfields y comboboxes
    CustomComboBox cboxSexos = new CustomComboBox(sexos);
    CustomComboBox cboxFechaDias = new CustomComboBox(fechaDias);
    CustomComboBox cboxFechaMeses = new CustomComboBox(fechaMeses);
    CustomComboBox cboxFechaAnios = new CustomComboBox(fechaAnios);

    TxtFieldPh txtfldNombres = new TxtFieldPh("Nombre(s)", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoP = new TxtFieldPh("Apellido paterno", true, 200, 40, 24);
    TxtFieldPh txtfldApellidoM = new TxtFieldPh("Apellido materno", true, 200, 40, 24);
    TxtFieldPh txtfldTelefono = new TxtFieldPh("Teléfono", true, 200, 40, 24);
    TxtFieldPh txtfldDireccion = new TxtFieldPh("Dirección", true, 200, 40, 24);

    //Botones
    CustomButton btnGuardar = new CustomButton("Registrar");

    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);

    Espaciador espaciadorh1 = new Espaciador(10, 10);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo paciente

    //public DlgEditarPaciente(Paciente paciente, PnlPacientes pnlPacientes, PnlElementoPaciente pnlElementoPaciente) {
    public DlgEditarPaciente(Frame parent, PnlPacientes pnlPacientes, PnlElementoPaciente pnlElementoPaciente) {

        //Setup del dialog
        super(parent, "Editar paciente");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);

        this.pnlPacientes = pnlPacientes;
        this.pnlElementoPaciente = pnlElementoPaciente;

        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));


        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        nombres = paciente.getNombres();
        apellidoP = paciente.getApellidoP();
        apellidoM = paciente.getApellidoM();
        sexo = paciente.getSexo();
        telefono = paciente.getTelefono();
        direccion = paciente.getDireccion();

         */
        //----------FIN DE LÓGICA----------


        txtfldNombres.setText(nombres);
        txtfldApellidoP.setText(apellidoP);
        txtfldApellidoM.setText(apellidoM);
        cboxSexos.setSelectedItem(sexo);
        txtfldTelefono.setText(telefono);
        txtfldDireccion.setText(direccion);

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

        contenido.add(lblTelefono);
        contenido.add(txtfldTelefono);

        contenido.add(lblDireccion);
        contenido.add(txtfldDireccion);


        //Botones
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarPaciente();
            }
        });
        contenido.add(btnGuardar);


        add(contenido);

    }

    public void guardarPaciente() {

        //hacer cosa mágica para que se guarde el tratamiento

        //extracción de la info a strings
        nombres = txtfldNombres.getText();
        apellidoP = txtfldApellidoP.getText();
        apellidoM = txtfldApellidoM.getText();
        sexo = cboxSexos.getSelectedItem().toString();
        diaN = cboxFechaDias.getSelectedItem().toString();
        mesN = cboxFechaMeses.getSelectedItem().toString();
        anioN = cboxFechaAnios.getSelectedItem().toString();
        telefono = txtfldTelefono.getText();
        direccion = txtfldDireccion.getText();

        System.out.println("Haz de cuenta que se registró el paciente con los datos: " + nombres + apellidoP + apellidoM + sexo + diaN + mesN + anioN + telefono + direccion);

        pnlPacientes.refresh();
        pnlElementoPaciente.refresh();
        this.dispose();
    }

    //----------FIN DE LÓGICA----------

}