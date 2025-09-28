package Clinica.src.main.java.Presentacion.paneles.elementos;

import Presentacion.dialogs.detalles.DlgDetallesCita;
import Presentacion.paneles.PnlCitas;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoCita extends JPanel {


    //----------PLACEHOLDER HARCODEADO-----------
    //Dejar valores sin asignar como declaración simple

    String nombreMedico = "Dr. Pancracio López";
    String nombrePaciente = "Fulanito D´tal";
    String fechaHora = "dd/mm/aaaa 00:00hrs";
    String estado = "Programada";
    //----------FIN DEL PLACEHOLDER-----------


    Style style = new Style();
    Dimension dimension = new Dimension(Style.frameX - 20, 70);
    PnlCitas pnlCitas;
    PnlElementoCita pnlElementoCita;

    boolean testeoColor = false;


    CustomLabel lblMedico = new CustomLabel("  " + nombreMedico, 18);
    CustomLabel lblPaciente = new CustomLabel("     " + nombrePaciente, 18);
    CustomLabel lblFechaHora = new CustomLabel(" "  + fechaHora + "        ", 18);
    CustomLabel lblEstado = new CustomLabel(" " + estado + " ", 18);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo cita

    //public PnlElementoCita(PnlCitas pnlCitas, Cita cita) {
    public PnlElementoCita(PnlCitas pnlCitas) {

        //Establecimiento de panel
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.grisBase);

        this.pnlCitas = pnlCitas;
        pnlElementoCita = this;


        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        nombreMedico = cita.getNombreMedico();
        nombre = cita.getNombrePaciente();
        fechaHora = cita.getFechaHora();
        estado = cita.getEstado();

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
                DlgDetallesCita detalles = new DlgDetallesCita(null, pnlCitas, pnlElementoCita);
                detalles.setVisible(true);
                //----------FIN DEL PLACEHOLDER-----------


                //----------LÓGICA AQUÍ----------
                /*

                DlgDetallesCita detalles = new DlgDetallesCita(null, pnlCitas,  pnlElementoCita, cita);
                detalles.setVisible(true);

                */
                //----------FIN DE LÓGICA----------

            }
        });

        add(lblMedico);
        add(lblPaciente);
        add(lblFechaHora);
        add(lblEstado);


    }

    public void refresh() {
        /*
        DlgRegistrarCita registrar = new DlgRegistrarCita(null);
        registrar.setVisible(true);*/

        System.out.println("refresh pnlElementoCitas");

        revalidate();
        repaint();
    }


}
