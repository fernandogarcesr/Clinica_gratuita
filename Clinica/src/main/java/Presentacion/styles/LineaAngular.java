package Clinica.src.main.java.Presentacion.styles;

import java.awt.geom.Line2D;

public class LineaAngular {

    int xInicio;
    int yInicio;
    double anguloGrados;
    int longitud;

    public LineaAngular(int xInicio, int yInicio, double anguloGrados, int longitud) {
        this.xInicio = xInicio;
        this.yInicio = yInicio;
        this.anguloGrados = anguloGrados;
        this.longitud = longitud;
    }

    public Line2D obtenerLinea() {
        double rad = Math.toRadians(anguloGrados);
        int xFin = xInicio + (int) (longitud * Math.cos(rad));
        int yFin = yInicio - (int) (longitud * Math.sin(rad));
        return new Line2D.Double(xInicio, yInicio, xFin, yFin);
    }
}
