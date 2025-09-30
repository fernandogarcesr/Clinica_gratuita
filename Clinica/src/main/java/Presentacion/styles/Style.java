package Presentacion.styles;

import java.awt.*;

public class Style {

    //Dimensiones
    public static final int frameX = 1280;
    public static final int frameY = 720;
    public static final Dimension dimensionBase = new Dimension(frameX, frameY);

    public static final int dialogX = frameX - 200;
    public static final int dialogY = frameY - 112;
    public static final Dimension dimensionDialog = new Dimension(dialogX, dialogY);

    //Colores
    public static final Color grisBase = new Color(50, 50, 50);
    public static final Color grisBaseHover = new Color(69, 69, 69);
    public static final Color lineaGris = new Color(69, 69, 69);

    public static final Color aqua = new Color(0, 185, 185);
    public static final Color aquaHover = new Color(0, 160, 160);

    public static final Color hoverBlanco = new Color(236, 236, 236);

    public static final Color grisDialog = new Color(50, 50, 50);

    //Fuentes
    public static final int letraSize = 18;


    //Estos ya estaban
    //public static final Color COLOR = new Color(22, 31, 51);
    //public static final Color COLOR_HOVER = new Color(28, 38, 64);
    public static final Color BUTTON_COLOR_HOVER  = new Color(218, 218, 218);
    public static final Color COLOR_TXT_PH = new Color(97, 97, 97, 125);
    //public static final Color BUTTON_COLOR_BLOQUEADO = new Color(141, 141, 141);

    //public static final int CORNER_RADIUS = 12;
    public static final int CORNER_RADIUS_TXT = 25;
    //public static final int CORNER_RADIUS_BUTTON = 50;
    //public static final int CORNER_RADIUS_BUTTON_2 = 30;

    public Style() {
    }
}
