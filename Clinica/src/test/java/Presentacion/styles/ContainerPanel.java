package Presentacion.styles;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel {

    public ContainerPanel(int width, int height, Color colorPrueba, boolean fondo) {

        Dimension dimension = new Dimension(width, height);

        //setLayout(new GridLayout(2, 2));
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);

        setBackground(colorPrueba);
        setOpaque(fondo);
    }
}
