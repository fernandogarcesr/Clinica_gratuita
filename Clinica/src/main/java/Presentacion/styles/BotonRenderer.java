package Presentacion.styles;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class BotonRenderer extends JButton implements TableCellRenderer {
    public BotonRenderer() {
        setOpaque(true);
        setText("⋮");
        setFont(new Font("SansSerif", Font.PLAIN, 18));
        setBackground(new Color(60, 63, 83));
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setBorderPainted(false);
        setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                   boolean hasFocus, int row, int column) {
        return this;
    }
}

