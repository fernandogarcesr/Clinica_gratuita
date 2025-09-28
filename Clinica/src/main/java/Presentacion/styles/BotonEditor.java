package Clinica.src.main.java.Presentacion.styles;

import javax.swing.*;
import java.awt.*;

public class BotonEditor extends DefaultCellEditor {
    private JButton button;

    public BotonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton("⋮");
        button.setFont(new Font("SansSerif", Font.PLAIN, 18));
        button.setBackground(new Color(60, 63, 83));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.addActionListener(e -> {
            System.out.println("opciones");
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        return button;
    }
}
