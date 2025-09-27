package Presentacion.paneles.elementos;

import Presentacion.styles.ContainerPanel;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoTratamiento extends JPanel {

    //-----------LÓGICA AQUÍ-----------
    //Atributos del tratamiento - convertir a getters del DTO
    String medicamento = "paracetamol, 200mg";
    String duracion = "7";
    String cita = "C-001";


    Style style = new Style();
    Dimension dimension = new Dimension(Style.frameX - 20, 70);

    boolean testeoColor = false;

    CustomLabel lblMedicamento = new CustomLabel("   " + medicamento, 18);
    CustomLabel lblDuracion = new CustomLabel("       " + duracion + "dias      ", 18);
    CustomLabel lblCita = new CustomLabel("     "  + cita + "        ", 18);


    //----------LÓGICA AQUÍ-----------
    //Agregar de parámetro un objeto DTO cita
    public PnlElementoTratamiento() {

        //settear los atributos del album como atributos de este panel

        //Establecimiento de panel
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setBackground(style.grisBase);

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

        });

        add(lblMedicamento);
        add(lblDuracion);
        add(lblCita);

    }
}
