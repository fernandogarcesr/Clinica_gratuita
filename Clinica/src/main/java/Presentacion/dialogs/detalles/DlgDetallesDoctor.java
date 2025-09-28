package Clinica.src.main.java.Presentacion.dialogs.detalles;

import Presentacion.dialogs.edicion.DlgEditarDoctor;
import Presentacion.paneles.PnlDoctores;
import Presentacion.paneles.elementos.PnlElementoDoctor;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesDoctor extends JDialog {

    //----------PLACEHOLDER HARCODEADO-----------
    //Dejar valores sin asignar como declaración simple

    String nombre = "Pancracio Lopez";
    String especialidad = "Cardiólogo";
    String correo = "ola@ola.com";
    String telefono = "1234567890";
    //----------FIN DEL PLACEHOLDER-----------


    boolean testeoColor = false;

    Style style = new Style();
    PnlDoctores pnlDoctores;
    PnlElementoDoctor pnlElementoDoctor;

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Dr. " + nombre, 32);

    CustomLabel lblColEspecialidad = new CustomLabel("        Especialidad      ", 24);
    CustomLabel lblEspecialidad = new CustomLabel(especialidad, style.letraSize);

    CustomLabel lblColCorreo = new CustomLabel("      Correo        ", 24);
    CustomLabel lblCorreo = new CustomLabel(correo, style.letraSize);

    CustomLabel lblColTelefono = new CustomLabel("     Teléfono        ", 24);
    CustomLabel lblTelefono = new CustomLabel(telefono, style.letraSize);

    CustomLabel lblCitasAgendadas = new CustomLabel("        Citas agendadas      ", 24);

    CustomLabel lblColPaciente = new CustomLabel("Paciente", style.letraSize);
    CustomLabel lblColFechaHora = new CustomLabel("Fecha y hora", style.letraSize);
    CustomLabel lblColEstado = new CustomLabel("Estado", style.letraSize);
    CustomLabel lblColCita = new CustomLabel("ID", style.letraSize);


    //Boton
    CustomButton btnEditarDoctor = new CustomButton("Editar información");
    CustomButton btnEliminarDoctor = new CustomButton("Eliminar doctor");

    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);
    ContainerPanel columnas = new ContainerPanel(style.dialogX, 50, Color.RED, testeoColor);
    ContainerPanel informacion = new ContainerPanel(style.dialogX, 50, Color.ORANGE, testeoColor);
    ContainerPanel colCitas = new ContainerPanel(style.dialogX, 50, Color.RED, testeoColor);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorv3 = new Espaciador(10, 50);
    Espaciador espaciadorv4 = new Espaciador(10, 50);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo cita

    //public DlgDetallesDoctor(Frame parent, PnlDoctores pnlDoctores, PnlElementoDoctor pnlElementoDoctor, Doctor doctor) {
    public DlgDetallesDoctor(Frame parent, PnlDoctores pnlDoctores, PnlElementoDoctor pnlElementoDoctor) {

        //Setup del dialog
        super(parent, "Detalles del doctor");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);

        this.pnlDoctores = pnlDoctores;
        this.pnlElementoDoctor = pnlElementoDoctor;

        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        nombre = doctor.getNombre();
        especialidad = doctor.getEspecialidad();
        correo = doctor.getCorreo();
        telefono = doctor.getTelefono();

        */
        //----------FIN DE LÓGICA----------


        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Título
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        //Info del dr
        columnas.setLayout(new GridLayout(1, 0));
        columnas.add(lblColEspecialidad);
        columnas.add(lblColCorreo);
        columnas.add(lblColTelefono);
        //columnas.add(lblColEstado);
        contenido.add(columnas);

        informacion.setLayout(new GridLayout(1, 0));
        informacion.add(lblEspecialidad);
        informacion.add(lblCorreo);
        informacion.add(lblTelefono);
        contenido.add(informacion);

        contenido.add(espaciadorv3);

        //Citas agendadas
        contenido.add(lblCitasAgendadas);
        colCitas.add(lblColPaciente);
        colCitas.add(lblColFechaHora);
        colCitas.add(lblColEstado);
        colCitas.add(lblColCita);

        //----------LÓGICA AQUÍ----------
        /*
        //Listar citas
        //Nota: no estoy seguro si esto vaya a jalar

        //(Recorre todas las citas que tenga este doctor)
        for(int i = 0; i < doctor.citas.length; i++) {

            ContainerPanel cita = new ContainerPanel(style.dialogX, 50, Color.RED, testeoColor);

            CustomLabel lblPaciente = new CustomLabel(doctor.citas[i].getNombrePaciente(), style.letraSize);
            CustomLabel lblFechaHora = new CustomLabel(doctor.citas[i].getFechaHora(), style.letraSize);
            CustomLabel lblEstado = new CustomLabel(doctor.citas[i].getEstado(), style.letraSize);
            CustomLabel lblCita = new CustomLabel(doctor.citas[i].getNombreCita(), style.letraSize);

            cita.add(lblPaciente);
            cita.add(lblFechaHora);
            cita.add(lblEstado);
            cita.add(lblCita);

            add(cita)

        }

        */
        //----------FIN DE LÓGICA----------



        contenido.add(espaciadorv4);

        //Tratamientos
        //insertar cosa de los tratamientos

        //Botones
        btnEditarDoctor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editarDoctor();
            }
        });
        contenido.add(btnEditarDoctor);

        btnEliminarDoctor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarDoctor();
            }
        });
        contenido.add(btnEliminarDoctor);



        add(contenido);

    }


    //----------LÓGICA AQUÍ----------

    public void eliminarDoctor() {

        //hacer cosa mágica para que se elimine
        System.out.println("Haz de cuenta que se eliminó el dr." + nombre);

        pnlDoctores.refresh();
        pnlElementoDoctor.refresh();
        this.dispose();

    }

    public void editarDoctor() {

        System.out.println("Desplegar dialog de edición");

        //----------PLACEHOLDER HARCODEADO-----------
        DlgEditarDoctor edicion = new DlgEditarDoctor(null, pnlDoctores, pnlElementoDoctor);
        edicion.setVisible(true);
        //----------FIN DEL PLACEHOLDER-----------

        /*
        DlgEditarDoctor edicion = new DlgEditarDoctor(null,pnlDoctores, pnlElementoDoctor, doctor);
        edicion.setVisible(true);
         */


        this.dispose();

    }

    //----------FIN DE LÓGICA----------

}