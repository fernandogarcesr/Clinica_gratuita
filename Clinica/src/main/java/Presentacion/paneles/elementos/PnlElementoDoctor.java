package Clinica.src.main.java.Presentacion.paneles.elementos;

import Presentacion.dialogs.detalles.DlgDetallesDoctor;
import Presentacion.paneles.PnlDoctores;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Espaciador;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoDoctor extends JPanel {

    //----------PLACEHOLDER HARCODEADO-----------
    //Dejar valores sin asignar como declaración simple

    String nombre = "Dr. Pancracio López";
    String especialidad = "Cardiólogo";
    String correo = "ola@gmail.com";
    String telefono = "1234-567-890";
    //----------FIN DEL PLACEHOLDER-----------


    Style style = new Style();
    Dimension dimension = new Dimension(Style.frameX - 20, 70);
    PnlDoctores pnlDoctores;
    PnlElementoDoctor pnlElementoDoctor = this;

    boolean testeoColor = false;

    CustomLabel lblNombre = new CustomLabel("  " + nombre, 18);
    CustomLabel lblEspecialidad = new CustomLabel("   " + especialidad, 18);
    CustomLabel lblCorreo = new CustomLabel("   " + correo + "     ", 18);
    CustomLabel lblTelefono = new CustomLabel("   " + telefono + "     ", 18);
    Espaciador espacioh1 = new Espaciador(10, 10);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo doctor

    //public PnlElementoDoctor(PnlDoctores pnlDoctores, Doctor doctor) {
    public PnlElementoDoctor(PnlDoctores pnlDoctores) {

        //Establecimiento de panel
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.grisBase);
        setLayout(new GridLayout(1, 6));

        this.pnlDoctores = pnlDoctores;


        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        nombre = doctor.getNombreDoctor();
        especialidad = doctor.getEspecialidad();
        correo = doctor.getCorreo();
        telefono = doctor.getTelefono();

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
                DlgDetallesDoctor detalles = new DlgDetallesDoctor(null, pnlDoctores, pnlElementoDoctor);
                detalles.setVisible(true);
                //----------FIN DEL PLACEHOLDER-----------


                //----------LÓGICA AQUÍ----------
                /*

                DlgDetallesDoctor detalles = new DlgDetallesDoctor(null, pnlDoctores, pnlElementoDoctor, doctor);
                detalles.setVisible(true);

                */
                //----------FIN DE LÓGICA----------


            }
        });

        add(lblNombre);
        add(lblEspecialidad);
        add(lblCorreo);
        add(lblTelefono);
        //add(espacioh1);

    }

    public void refresh() {

        System.out.println("refresh pnlElementoDoctor");

        revalidate();
        repaint();
    }
}
