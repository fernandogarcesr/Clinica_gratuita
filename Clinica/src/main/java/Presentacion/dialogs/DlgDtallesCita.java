package Presentacion.dialogs;

import Presentacion.paneles.PnlCitas;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;

public class DlgDtallesCita extends JDialog {

    //WIP


    //------------LÓGICA AQUÍ-----------
    //cambiar por getters del DTO
    String nombreCita = "C-002";
    String nombreDoctor = "Dr.Pancracio Lopez";
    String nombrePaciente = "Fulanito D´tal";
    String fechaHora = "dd/mm/aaaa 00:00hrs";
    String estado = "En curso";

    boolean testeoColor = false;

    Style style = new Style();
    int dlgX = 500, dlgY = 600;

    //Atributos para lógica
    String nombreArtista = "Nombre artista";


    //Labels
    //agregar toda la info

    //Boton
    //agregar boton eliminar

    //Contenido
    ContainerPanel contenedorContenido = new ContainerPanel(dlgX, 400, Color.CYAN, testeoColor);

    //Espacios
    Espaciador espaciadorv1 = new Espaciador(10, 50);
    Espaciador espaciadorv2 = new Espaciador(10, 50);
    Espaciador espaciadorh1 = new Espaciador(10, 10);



    public DlgDtallesCita(Frame parent) {

        //Setup del dialog
        super(parent, "Contenido del artista", true);
        //setLayout(new BorderLayout());
        setSize(dlgX, dlgY);
        setLocationRelativeTo(parent);
        setBackground(style.grisDialog);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));



    }

}
