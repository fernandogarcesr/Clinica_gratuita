package Presentacion.styles;

import java.awt.*;
import java.io.InputStream;

public class FuenteUtil {

    public static Font cargarFuenteInter(float tamaño, String fontType) {
        try {
            InputStream is = FuenteUtil.class.getClassLoader().getResourceAsStream("fonts/" + fontType + ".ttf");
            if (is == null) {
                throw new RuntimeException("No se pudo encontrar la fuente.");
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(tamaño);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) tamaño);
        }
    }
}
