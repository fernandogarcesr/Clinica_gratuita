package Presentacion.styles;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {

    //int size;
    //Font baseArial = new Font("Arial", Font.BOLD, 32);

    public CustomLabel(String texto, int size) {

        setFont(new Font("Arial", Font.BOLD, size));
        setForeground(Color.WHITE);
        setText(texto);
    }

    public CustomLabel(String texto, int size, int negro) {

        setFont(new Font("Arial", Font.BOLD, size));
        setForeground(Color.BLACK);
        setText(texto);
    }

}
