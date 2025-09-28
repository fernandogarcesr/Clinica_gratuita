package Clinica.src.main.java.Presentacion.dialogs.detalles;

import Presentacion.dialogs.edicion.DlgEditarPaciente;
import Presentacion.paneles.PnlPacientes;
import Presentacion.paneles.elementos.PnlElementoPaciente;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesPaciente extends JDialog {

    //----------PLACEHOLDER HARCODEADO-----------
    //Dejar valores sin asignar como declaración simple

    String nombre = "Fulanito D´tal";
    String fechaN = "dd/mm/aaaa";
    String sexo = "Masculino";
    String direccion = "Calle de las sirenas, Luna";
    String correo = "ola@ola.com";
    String telefono = "1234567890";
    //----------FIN DEL PLACEHOLDER-----------


    boolean testeoColor = false;
    Style style = new Style();
    PnlPacientes pnlPacientes;
    PnlElementoPaciente pnlElementoPaciente;

    //Labels
    CustomLabel lblTitulo = new CustomLabel(nombre, 32);

    CustomLabel lblColSexo = new CustomLabel("        Sexo      ", 24);
    CustomLabel lblSexo = new CustomLabel(sexo, style.letraSize);

    CustomLabel lblColFechaN = new CustomLabel("        Fecha de nacimiento      ", 24);
    CustomLabel lblFechaN = new CustomLabel(fechaN, style.letraSize);

    CustomLabel lblColDireccion = new CustomLabel("        Dirección      ", 24);
    CustomLabel lblDireccion = new CustomLabel(direccion, style.letraSize);

    CustomLabel lblColCorreo = new CustomLabel("      Correo        ", 24);
    CustomLabel lblCorreo = new CustomLabel(correo, style.letraSize);

    CustomLabel lblColTelefono = new CustomLabel("     Teléfono        ", 24);
    CustomLabel lblTelefono = new CustomLabel(telefono, style.letraSize);

    CustomLabel lblCitasAgendadas = new CustomLabel("        Citas agendadas      ", 24);


    //Boton
    CustomButton btnEditarPaciente = new CustomButton("Editar información");
    CustomButton btnEliminarPaciente = new CustomButton("Eliminar paciente");

    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);
    ContainerPanel columnas = new ContainerPanel(style.dialogX, 50, Color.RED, testeoColor);
    ContainerPanel informacion = new ContainerPanel(style.dialogX, 50, Color.ORANGE, testeoColor);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorv3 = new Espaciador(10, 50);
    Espaciador espaciadorv4 = new Espaciador(10, 50);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo paciente

    //public DlgDetallesPaciente(Frame parent, PnlPacientes pnlPacientes, PnlElementoPaciente pnlElementoPaciente, Paciente paciente) {
    public DlgDetallesPaciente(Frame parent, PnlPacientes pnlPacientes, PnlElementoPaciente pnlElementoPaciente) {

        //Setup del dialog
        super(parent, "Detalles de paciente");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);


        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        nombre = paciente.getNombre();
        fechaN = paciente.getFechaNacimiento();
        sexo = paciente.getSexo();
        direccion = paciente.getDirección;
        correo = paciente.getCorreo();
        telefono = paciente.getTelefono();

        */
        //----------FIN DE LÓGICA----------


        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Título
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        //Info del dr
        columnas.setLayout(new GridLayout(1, 0));
        columnas.add(lblColSexo);
        columnas.add(lblColFechaN);
        columnas.add(lblColDireccion);
        columnas.add(lblColCorreo);
        columnas.add(lblColTelefono);
        contenido.add(columnas);

        informacion.setLayout(new GridLayout(1, 0));
        informacion.add(lblSexo);
        informacion.add(lblFechaN);
        informacion.add(lblDireccion);
        informacion.add(lblCorreo);
        informacion.add(lblTelefono);
        contenido.add(informacion);

        contenido.add(espaciadorv3);

        //Citas agendadas
        contenido.add(lblCitasAgendadas);
        //copiar lógica de mostracion de citas

        contenido.add(espaciadorv4);


        //Tratamientos

        //----------LÓGICA AQUÍ----------
        /*
        //Listar citas
        //Nota: no estoy seguro si esto vaya a jalar

        //(Recorre todas las citas que tenga este paciente)
        for(int i = 0; i < paciente.citas.length; i++) {

            ContainerPanel cita = new ContainerPanel(style.dialogX, 50, Color.RED, testeoColor);

            CustomLabel lblDoctor = new CustomLabel(paciente.citas[i].getNombrePaciente(), style.letraSize);
            CustomLabel lblFechaHora = new CustomLabel(paciente.citas[i].getFechaHora(), style.letraSize);
            CustomLabel lblEstado = new CustomLabel(paciente.citas[i].getEstado(), style.letraSize);
            CustomLabel lblCita = new CustomLabel(paciente.citas[i].getNombreCita(), style.letraSize);

            cita.add(lblDoctor);
            cita.add(lblFechaHora);
            cita.add(lblEstado);
            cita.add(lblCita);

            add(cita)

        }

        */
        //----------FIN DE LÓGICA----------


        //Botones
        btnEditarPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editarPaciente();
            }
        });
        contenido.add(btnEditarPaciente);

        btnEliminarPaciente.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarPaciente();
            }
        });
        contenido.add(btnEliminarPaciente);



        add(contenido);

    }

    //----------LÓGICA AQUÍ----------

    public void eliminarPaciente() {

        //hacer cosa mágica para que se elimine
        System.out.println("Haz de cuenta que se eliminó el paciente " + nombre);

        pnlPacientes.refresh();
        pnlElementoPaciente.refresh();
        this.dispose();

    }

    public void editarPaciente() {

        System.out.println("Desplegar dialog de edición");

        DlgEditarPaciente edicion = new DlgEditarPaciente(null, pnlPacientes, pnlElementoPaciente);
        edicion.setVisible(true);

        pnlPacientes.refresh();
        pnlElementoPaciente.refresh();
        this.dispose();

    }

    //----------FIN DE LÓGICA----------


}