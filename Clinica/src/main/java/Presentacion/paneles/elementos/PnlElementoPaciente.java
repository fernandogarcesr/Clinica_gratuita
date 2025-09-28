package Clinica.src.main.java.Presentacion.paneles.elementos;

import Presentacion.dialogs.detalles.DlgDetallesPaciente;
import Presentacion.paneles.PnlPacientes;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Espaciador;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoPaciente extends JPanel {

    //----------PLACEHOLDER HARCODEADO-----------
    //Dejar valores sin asignar como declaración simple

    String nombre = "Fulanito D´tal";
    String sexo = "siempre";
    String edad = "23";
    String telefono = "1234-567-890";
    //----------FIN DEL PLACEHOLDER-----------


    Style style = new Style();
    Dimension dimension = new Dimension(Style.frameX - 20, 70);
    PnlPacientes pnlPacientes;
    PnlElementoPaciente pnlElementoPaciente = this;

    boolean testeoColor = false;

    CustomLabel lblNombre = new CustomLabel("  " + nombre, 18);
    CustomLabel lblSexo = new CustomLabel("   " + sexo, 18);
    CustomLabel lblEdad = new CustomLabel("   " + edad + "     ", 18);
    CustomLabel lblTelefono = new CustomLabel("   " + telefono + "     ", 18);
    Espaciador espacioh1 = new Espaciador(10, 10);


    //----------LÓGICA AQUÍ: MODIFICAR CONSTRUCTOR-----------
    //Agregar de parámetro un objeto tipo paciente

    //public PnlElementoPaciente(Paciente paciente) {
    public PnlElementoPaciente(PnlPacientes pnlPacientes) {

        //Establecimiento de panel
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.grisBase);
        setLayout(new GridLayout(1, 6));

        this.pnlPacientes = pnlPacientes;


        //----------LÓGICA AQUÍ----------
        /*
        //Asignación de variables

        nombre = paciente.getNombre();
        sexo = paciente.getSexo();
        edad = paciente.getEdad();
        telefono = paciente.getTelefono();

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
                DlgDetallesPaciente detalles = new DlgDetallesPaciente(null, pnlPacientes, pnlElementoPaciente);
                detalles.setVisible(true);
                //----------FIN DEL PLACEHOLDER-----------


                //----------LÓGICA AQUÍ----------
                /*

                DlgDetallesPaciente detalles = new DlgDetallesPaciente(null, pnlPacientes, pnlElementoPaciente, paciente);
                detalles.setVisible(true);

                */
                //----------FIN DE LÓGICA----------

            }
        });

        add(lblNombre);
        add(lblSexo);
        add(lblEdad);
        add(lblTelefono);
        //add(espacioh1);

    }

    public void refresh() {

        System.out.println("refresh pnlElementoPaciente");

        revalidate();
        repaint();
    }
}
