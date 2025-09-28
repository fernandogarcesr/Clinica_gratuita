package Clinica.src.main.java.Presentacion.styles;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class CustomComboBox<T> extends JComboBox<T> {

    public CustomComboBox(T[] items) {
        super(items);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                JButton button = new JButton("▼");
                button.setBorder(BorderFactory.createEmptyBorder());
                button.setContentAreaFilled(false);
                button.setForeground(Color.DARK_GRAY);
                button.setFocusPainted(false);
                return button;
            }
        });

        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(FuenteUtil.cargarFuenteInter(16, "Inter_Light"));
        setFocusable(false);
        setOpaque(false);
        setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));

        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                                                          int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(FuenteUtil.cargarFuenteInter(14, "Inter_Light"));
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                label.setOpaque(true);

                label.setBackground(isSelected ? Style.BUTTON_COLOR_HOVER : Color.WHITE);
                label.setForeground(Color.BLACK);
                return label;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.white);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), Style.CORNER_RADIUS_TXT, Style.CORNER_RADIUS_TXT);
        super.paintComponent(g);

    }
}
