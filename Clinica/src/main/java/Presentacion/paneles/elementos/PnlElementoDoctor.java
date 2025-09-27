package Presentacion.paneles.elementos;

import Presentacion.styles.CustomLabel;
import Presentacion.styles.Espaciador;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoDoctor extends JPanel {

    //----------LÓGICA AQUÍ----------
    //Atributos del dr - convertir a getters del dTO
    String nombreDoctor = "Dr. Pancracio López";
    String especialidad = "Cardiólogo";
    String correo = "ola@gmail.com";
    String telefono = "1234-567-890";


    Style style = new Style();
    Dimension dimension = new Dimension(Style.frameX - 20, 70);

    boolean testeoColor = false;

    CustomLabel lblNombre = new CustomLabel("  " + nombreDoctor, 18);
    CustomLabel lblEspecialidad = new CustomLabel("   " + especialidad, 18);
    CustomLabel lblCorreo = new CustomLabel("   " + correo + "     ", 18);
    CustomLabel lblTelefono = new CustomLabel("   " + telefono + "     ", 18);
    Espaciador espacioh1 = new Espaciador(10, 10);


    //----------LÓGICA AQUÍ-----------
    //Agregar de parámetro un objeto DTO cita
    public PnlElementoDoctor() {

        //settear los atributos del album como atributos de este panel

        //Establecimiento de panel
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.grisBase);
        setLayout(new GridLayout(1, 6));

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
                //meter al dr de este panel como argumento pal dialog
                //DlgDetallesDoctor detalles = new DlgDetallesDoctor();
                //detalles.setVisible(true);
            }
        });

        add(lblNombre);
        add(lblEspecialidad);
        add(lblCorreo);
        add(lblTelefono);
        //add(espacioh1);

    }
}
