package Presentacion.frames;

import Presentacion.paneles.*;
import Presentacion.styles.*;

import javax.swing.*;

public class FrmPrincipal extends JFrame {

    Style style = new Style();
    private PnlMenu pnlMenu;

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

}
