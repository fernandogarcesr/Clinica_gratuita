package Clinica.src.main.java.Presentacion.dialogs.detalles;

import Presentacion.dialogs.edicion.DlgEditarTratamiento;
import Presentacion.paneles.PnlTratamientos;
import Presentacion.paneles.elementos.PnlElementoTratamiento;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgDetallesTratamiento extends JDialog {

    //----------PLACEHOLDER HARCODEADO-----------
    //Dejar valores sin asignar como declaración simple

    String medicamento = "Paracetamol, 200mg";
    String duracion = "7 días";
    String cita = "C-001";
    String descripcion = "pa que ya no lela pancha";
    //----------FIN DEL PLACEHOLDER-----------


    boolean testeoColor = false;
    Style style = new Style();
    PnlTratamientos pnlTratamientos;
    PnlElementoTratamiento pnlElementoTratamiento;

    //Labels
    CustomLabel lblTitulo = new CustomLabel("Detalles de tratamiento", 32);

    CustomLabel lblColMedicamento = new CustomLabel("        Medicamento      ", 24);
    CustomLabel lblMedicamento = new CustomLabel(medicamento, style.letraSize);

    CustomLabel lblColDuracion = new CustomLabel("      Duración estimada        ", 24);
    CustomLabel lblDuracion = new CustomLabel(duracion, style.letraSize);

    CustomLabel lblColCita = new CustomLabel("     Cita de origen        ", 24);
    CustomLabel lblFechaCita = new CustomLabel(cita, style.letraSize);

    CustomLabel lblColDescripcion = new CustomLabel("        Descripcion      ", 24);
    CustomLabel lblDescripcion = new CustomLabel(descripcion, style.letraSize);


    //Boton
    CustomButton btnEditarTratamiento = new CustomButton("Editar tratamiento", 1, 200, 30);
    CustomButton btnEliminarTratamiento = new CustomButton("Eliminar tratamiento", 1, 200, 30);


    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);
    ContainerPanel columnas = new ContainerPanel(style.dialogX, 50, Color.RED, testeoColor);
    ContainerPanel informacion = new ContainerPanel(style.dialogX, 50, Color.ORANGE, testeoColor);
    ContainerPanel botones = new ContainerPanel(style.dialogX, 50, Color.PINK, testeoColor);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorv3 = new Espaciador(10, 50);
    Espaciador espaciadorv4 = new Espaciador(10, 50);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo tratamiento

    //public DlgDetallesTratamiento(Frame parent, PnlTratamientos pnlTratamientos, PnlElementoTratamiento pnlElementoTratamiento, Tratamiento tratamiento) {
    public DlgDetallesTratamiento(Frame parent, PnlTratamientos pnlTratamientos, PnlElementoTratamiento pnlElementoTratamiento) {

        //Setup del dialog
        super(parent, "Detalles del tratamiento");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);

        this.pnlTratamientos = pnlTratamientos;
        this.pnlElementoTratamiento = pnlElementoTratamiento;

        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        nombreCita = cita.getNombreCita();
        nombre = cita.getNombreDoctor()";
        nombre = cita.getNombrePaciente();;
        cita = cita.getFechaHora();
        estado = cita.getEstado();
        descripcion = cita.getMotivo();

        */
        //----------FIN DE LÓGICA----------


        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Título
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        //Info de la cita
        columnas.setLayout(new GridLayout(1, 0));
        columnas.add(lblColMedicamento);
        columnas.add(lblColDuracion);
        columnas.add(lblColCita);
        //columnas.add(lblColEstado);
        contenido.add(columnas);

        informacion.setLayout(new GridLayout(1, 0));
        informacion.add(lblMedicamento);
        informacion.add(lblDuracion);
        informacion.add(lblFechaCita);
        //informacion.add(lblEstado);
        contenido.add(informacion);

        contenido.add(espaciadorv3);
        contenido.add(lblColDescripcion);
        contenido.add(lblDescripcion);

        contenido.add(espaciadorv4);


        //Botones
        btnEliminarTratamiento.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eliminarTratamiento();
            }
        });
        btnEditarTratamiento.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editarTratamiento();
            }
        });

        contenido.add(btnEditarTratamiento);
        contenido.add(btnEliminarTratamiento);

        add(contenido);

    }


    //----------LÓGICA AQUÍ----------

    public void eliminarTratamiento() {

        //hacer cosa mágica para que se elimine el tratamiento
        System.out.println("Haz de cuenta que se eliminó el tratamiento " + medicamento);

        pnlTratamientos.refresh();
        pnlElementoTratamiento.refresh();
        this.dispose();

    }

    public void editarTratamiento() {

        System.out.println("Editar tratamiento");

        //----------PLACEHOLDER HARCODEADO-----------
        DlgEditarTratamiento editar = new DlgEditarTratamiento(null, pnlTratamientos, pnlElementoTratamiento);
        editar.setVisible(true);
        //----------FIN DEL PLACEHOLDER-----------

        /*
        DlgEditarTratamiento detalles = new DlgEditarTratamiento(null, pnlTratamientos, pnlElementoTratamiento, tratamiento);
        detalles.setVisible(true);
        */

        pnlTratamientos.refresh();
        pnlElementoTratamiento.refresh();
        this.dispose();

    }

    //----------FIN DE LÓGICA----------

}