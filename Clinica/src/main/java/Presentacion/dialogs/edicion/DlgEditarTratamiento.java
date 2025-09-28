package Clinica.src.main.java.Presentacion.dialogs.edicion;

import Presentacion.paneles.PnlTratamientos;
import Presentacion.paneles.elementos.PnlElementoTratamiento;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DlgEditarTratamiento extends JDialog {

    boolean testeoColor = false;
    Style style = new Style();
    PnlTratamientos pnlTratamientos;
    PnlElementoTratamiento pnlElementoTratamiento;


    //----------PLACEHOLDER HARCODEADO-----------
    //Dejar valores sin asignar como declaración simple
    String medicamento = "paracetamol, 200mg";
    String duracion = "7dias";
    //----------FIN DEL PLACEHOLDER-----------


    //Labels
    CustomLabel lblTitulo = new CustomLabel("Recetar tratamiento", 32);

    CustomLabel lblMedicamento = new CustomLabel("Nombre del tratamiento", 24);
    CustomLabel lblDuracion = new CustomLabel("Duración del tratamiento", 24);

    //Textfields
    TxtFieldPh txtfldTratamiento = new TxtFieldPh("Tratamiento", true, 200, 40, 24);
    TxtFieldPh txtfldDuracion = new TxtFieldPh("Duración", true, 200, 40, 24);

    //Botones
    CustomButton btnGuardar = new CustomButton("Guardar");


    //contenedores
    ContainerPanel contenido = new ContainerPanel(style.dialogX, style.dialogY, style.grisDialog, true);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo tratamiento

    //public DlgEditarTratamiento(Tratamiento tratamiento, PnlTratamientos pnlTratamientos, PnlElementoTratamiento pnlElementoTratamiento) {
    public DlgEditarTratamiento(Frame parent, PnlTratamientos pnlTratamientos, PnlElementoTratamiento pnlElementoTratamiento) {

        //Setup del dialog
        super(parent, "Recetar tratamiento");
        setSize(style.dimensionDialog);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);

        this.pnlTratamientos = pnlTratamientos;
        this.pnlElementoTratamiento = pnlElementoTratamiento;


        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        medicamento = tratamiento.getMedicamento();;
        duracion = tratamiento.getDuracion;

         */
        //----------FIN DE LÓGICA----------


        txtfldTratamiento.setText(medicamento);
        txtfldDuracion.setText(duracion);


        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));

        //Contenido
        contenido.add(espaciadorv1);
        contenido.add(lblTitulo);
        contenido.add(espaciadorv2);

        contenido.add(lblMedicamento);
        contenido.add(txtfldTratamiento);

        contenido.add(lblDuracion);
        contenido.add(txtfldDuracion);


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

    //----------LÓGICA AQUÍ----------
    public void guardarTratamiento() {

        //hacer cosa mágica para que se guarde el tratamiento

        medicamento = txtfldTratamiento.getText();
        duracion = txtfldDuracion.getText();

        System.out.println("Haz de cuenta que se guardó el tratamiento con los datos: " + medicamento + duracion);

        pnlTratamientos.refresh();
        pnlElementoTratamiento.refresh();
        this.dispose();

    }
    //----------FIN DE LÓGICA----------
}

