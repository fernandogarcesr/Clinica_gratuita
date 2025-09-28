package Clinica.src.main.java.Presentacion.paneles.elementos;

import Presentacion.dialogs.detalles.DlgDetallesTratamiento;
import Presentacion.paneles.PnlTratamientos;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoTratamiento extends JPanel {


    //----------PLACEHOLDER HARCODEADO-----------
    //Dejar valores sin asignar como declaración simple

    String medicamento = "paracetamol, 200mg";
    String duracion = "7 días";
    String cita = "C-001";
    //----------FIN DEL PLACEHOLDER-----------


    Style style = new Style();
    Dimension dimension = new Dimension(Style.frameX - 20, 70);
    PnlTratamientos pnlTratamientos;
    PnlElementoTratamiento pnlElementoTratamiento = this;

    boolean testeoColor = false;

    CustomLabel lblMedicamento = new CustomLabel("   " + medicamento, 18);
    CustomLabel lblDuracion = new CustomLabel("       " + duracion + "      ", 18);
    CustomLabel lblCita = new CustomLabel("     "  + cita + "        ", 18);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo tratamiento

    //public PnlElementoTratamiento(PnlTratamientos pnlTratamientos, Tratamiento tratamiento) {
    public PnlElementoTratamiento(PnlTratamientos pnlTratamientos) {

        //Establecimiento de panel
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.grisBase);

        this.pnlTratamientos = pnlTratamientos;


        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        medicamento = tratamiento.getMedicamento();
        duracion = tratamiento.getDuracion();
        cita = tratamiento.getCita();

        */
        //----------FIN DE LÓGICA----------


        //Acciones del panel
        addMouseListener(new MouseAdapter() {
            //Hover
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(style.grisBaseHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(style.grisBase);
            }

            //Mostrar info al ser clickeado
            @Override
            public void mouseClicked(MouseEvent e) {

                //----------PLACEHOLDER HARCODEADO-----------
                DlgDetallesTratamiento detalles = new DlgDetallesTratamiento(null, pnlTratamientos, pnlElementoTratamiento);
                detalles.setVisible(true);
                //----------FIN DEL PLACEHOLDER-----------


                //----------LÓGICA AQUÍ----------
                /*

                DlgDetallesTratamiento detalles = new DlgDetallesTratamiento(null, pnlTratamientos, pnlElementoTratamiento, tratamiento);
                detalles.setVisible(true);

                */
                //----------FIN DE LÓGICA----------


            }

        });

        add(lblMedicamento);
        add(lblDuracion);
        add(lblCita);

    }

    public void refresh() {

        System.out.println("refresh pnlElementoTratamiento");

        revalidate();
        repaint();
    }
}
