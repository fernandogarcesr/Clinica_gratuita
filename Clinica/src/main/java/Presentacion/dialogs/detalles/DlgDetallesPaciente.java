package Presentacion.dialogs.detalles;

import Presentacion.dialogs.registro.DlgRegistrarPaciente;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesPaciente extends JDialog {

    //------------LÓGICA AQUÍ-----------
    //cambiar por getters del DTO
    String nombrePaciente = "Fulanito D´tal";
    String fechaN = "dd/mm/aaaa";
    String sexo = "Masculino";
    String direccion = "Calle de las sirenas, Luna";
    String correo = "ola@ola.com";
    String telefono = "1234567890";


    boolean testeoColor = false;

    Style style = new Style();

    //Labels
    CustomLabel lblTitulo = new CustomLabel(nombrePaciente, 32);

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

    Espaciador espaciadorh1 = new Espaciador(10, 10);


    public DlgDetallesPaciente(Frame parent) {

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
        //insertar cosa de los tratamientos

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

        //setVisible(true);

    }


    public void eliminarPaciente() {

        //----------LÓGICA AQUÍ----------
        //hacer cosa mágica para que se elimine
        System.out.println("Haz de cuenta que se eliminó el paciente " + nombrePaciente);

        //validar que se haya eliminado
        this.dispose();

    }

    public void editarPaciente() {

        //----------LÓGICA AQUÍ----------
        //meter de argumento al paciente

        System.out.println("Desplegar dialog de edición");

        //se utiliza el mismo dialog que al registrar, pero un constructor alternativo con el paciente como argumento
        DlgRegistrarPaciente edicion = new DlgRegistrarPaciente(null);
        edicion.setVisible(true);

        this.dispose();

    }

}