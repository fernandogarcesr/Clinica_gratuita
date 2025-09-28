package Presentacion.dialogs.detalles;

import Presentacion.dialogs.registro.DlgRegistrarDoctor;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesDoctor extends JDialog {

    //------------LÓGICA AQUÍ-----------
    //cambiar por getters del DTO
    String nombreDoctor = "Pancracio Lopez";
    String especialidad = "Cardiólogo";
    String correo = "ola@ola.com";
    String telefono = "1234567890";


    boolean testeoColor = false;

    Style style = new Style();

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Dr. " + nombreDoctor, 32);

    CustomLabel lblColEspecialidad = new CustomLabel("        Especialidad      ", 24);
    CustomLabel lblEspecialidad = new CustomLabel(especialidad, style.letraSize);

    CustomLabel lblColCorreo = new CustomLabel("      Correo        ", 24);
    CustomLabel lblCorreo = new CustomLabel(correo, style.letraSize);

    CustomLabel lblColTelefono = new CustomLabel("     Teléfono        ", 24);
    CustomLabel lblTelefono = new CustomLabel(telefono, style.letraSize);

    CustomLabel lblCitasAgendadas = new CustomLabel("        Citas agendadas      ", 24);


    //Boton
    CustomButton btnEditarDoctor = new CustomButton("Editar información");
    CustomButton btnEliminarDoctor = new CustomButton("Eliminar doctor");

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



    public DlgDetallesDoctor(Frame parent) {

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
        //copiar lógica de mostracion de citas

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

        //setVisible(true);

    }


    public void eliminarDoctor() {

        //----------LÓGICA AQUÍ----------
        //hacer cosa mágica para que se elimine
        System.out.println("Haz de cuenta que se eliminó el dr." + nombreDoctor);

        //validar que se haya eliminado
        this.dispose();

    }

    public void editarDoctor() {

        //----------LÓGICA AQUÍ----------
        //meter de argumento al dr

        System.out.println("Desplegar dialog de edición");

        //se utiliza el mismo dialog que al registrar, pero un constructor alternativo con el dr como argumento
        DlgRegistrarDoctor edicion = new DlgRegistrarDoctor(null);
        edicion.setVisible(true);

        this.dispose();

    }

}