package Presentacion.frames;

import javax.swing.*;
import java.awt.*;
import Presentacion.styles.*;
import Presentacion.paneles.*;

public class FrmPrincipal extends JFrame {

    //agregar menú principal como panel interno

    Style style = new Style();
    private PnlMenu pnlMenu;
    //private JPanel panelSur;



    public FrmPrincipal( ) {

        //Establecimiento del frame
        super("Sistema clínica");
        setSize(style.frameX, style.frameY);
        getContentPane().setBackground(Style.grisBase);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Inicializar inicio
        PnlMenu pnlMenu = new PnlMenu();
        add(pnlMenu);

        repaint();
        setVisible(true);
    }

    public void volverInicio() {
        //showPanelContenedorNuevo(mainFramePanel);
    }
}
