package Presentacion.paneles.elementos;

import Presentacion.dialogs.detalles.DlgDetallesCita;
import Presentacion.paneles.PnlCitas;
import Presentacion.styles.CustomLabel;
import Presentacion.styles.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlElementoCita extends JPanel {

    //----------LÓGICA AQUÍ-----------
    //Atributos de la cita - convertir a getters del DTO
    //Cita cita;
    String nombreMedico = "Dr. Pancracio López";
    String nombrePaciente = "Fulanito D´tal";
    String fechaHora = "dd/mm/aaaa 00:00hrs";
    String estado = "Programada";


    Style style = new Style();
    Dimension dimension = new Dimension(Style.frameX - 20, 70);
    PnlCitas pnlCitas;

    boolean testeoColor = false;


    CustomLabel lblMedico = new CustomLabel("  " + nombreMedico, 18);
    CustomLabel lblPaciente = new CustomLabel("     " + nombrePaciente, 18);
    CustomLabel lblFechaHora = new CustomLabel(" "  + fechaHora + "        ", 18);
    CustomLabel lblEstado = new CustomLabel(" " + estado + " ", 18);


    //----------LÓGICA AQUÍ-----------
    //Agregar de parámetro un objeto DTO cita
    public PnlElementoCita(PnlCitas pnlCitas) {

        pnlCitas = this.pnlCitas;

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

            //Mostrar info al ser clickeado
            @Override
            public void mouseClicked(MouseEvent e) {
                //meter la cita de este panel como argumento pal dialog
                DlgDetallesCita detalles = new DlgDetallesCita(null);
                detalles.setVisible(true);
            }
        });

        add(lblMedico);
        add(lblPaciente);
        add(lblFechaHora);
        add(lblEstado);


    }


}
