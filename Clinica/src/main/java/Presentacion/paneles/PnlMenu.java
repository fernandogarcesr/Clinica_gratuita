package Presentacion.paneles;

import javax.swing.*;

import Presentacion.styles.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlMenu extends JPanel {

    boolean testeoColor = false;

    Style style = new Style();
    PnlPerfil pnlPerfil;

    //Encabezado
    ContainerPanel encabezado = new ContainerPanel(style.frameX, 40, Color.PINK, testeoColor);
        //Título
    CustomLabel lblTitulo = new CustomLabel("   Inicio", 32);

    //Menú
    ContainerPanel contenedorMenu = new ContainerPanel(style.frameX, 50, Color.BLUE, testeoColor);
        //Botones
    TitleButton btnCitas = new TitleButton("Citas", 130, 40);
    TitleButton btnTratamientos = new TitleButton("Tratamientos", 130, 40);
    TitleButton btnDoctores = new TitleButton("Doctores", 130, 40);
    TitleButton btnPacientes = new TitleButton("Pacientes", 130, 40);

    //Línea divisoria
    ContainerPanel linea = new ContainerPanel(style.frameX, 3, Color.white, true);

    //Espaciadores
    Espaciador espaciov1 = new Espaciador(style.frameX, 10);
    Espaciador espaciov2 = new Espaciador(style.frameX, 25);
    Espaciador espacioh1 = new Espaciador(50, 20);

    //Declaración de paneles
    PnlCitas citas;
    PnlTratamientos tratamientos;
    PnlDoctores doctores;
    PnlPacientes pacientes;


    public PnlMenu() {

        pnlPerfil = new PnlPerfil(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setSize(style.frameX, style.frameY);

        //Espacio vertical
        add(espaciov1);

        //Encabezado
        encabezado.setLayout(new BorderLayout());
        encabezado.add(lblTitulo, BorderLayout.WEST);
        add(encabezado);


        //Menú
        add(contenedorMenu);

        btnCitas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarPanelActual();
                tabCitas();
            }
        });
        contenedorMenu.add(btnCitas);

        btnTratamientos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarPanelActual();
                tabTratamientos();
            }
        });
        contenedorMenu.add(btnTratamientos);

        btnDoctores.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarPanelActual();
                tabDoctores();
            }
        });
        contenedorMenu.add(btnDoctores);

        btnPacientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarPanelActual();
                tabPacientes();
            }
        });
        contenedorMenu.add(btnPacientes);



        //Línea divisoria
        add(linea);

        citas = new PnlCitas();
        add(citas);


        setVisible(true);

    }



    public void verPerfil(){

        mostracionPanelActual(false);

        encabezado.setVisible(false);
        contenedorMenu.setVisible(false);

        add(pnlPerfil);
        pnlPerfil.setVisible(true);

        revalidate();
        repaint();

    }

    public void volverAInicio() {
        encabezado.setVisible(true);
        contenedorMenu.setVisible(true);

        linea.setVisible(true);
        mostracionPanelActual(true);
    }

    public void mostracionPanelActual(boolean mostrar) {
        if (isAncestorOf(citas)) {
            citas.setVisible(mostrar);
        }
        if (isAncestorOf(tratamientos)) {
            tratamientos.setVisible(mostrar);
        }
        if (isAncestorOf(doctores)) {
            doctores.setVisible(mostrar);
        }
        if (isAncestorOf(pacientes)) {
            pacientes.setVisible(mostrar);
        }
        revalidate();
        repaint();
    }

    public void limpiarPanelActual(){
        if (isAncestorOf(citas)) {
            remove(citas);
        }
        if (isAncestorOf(tratamientos)) {
            remove(tratamientos);
        }
        if (isAncestorOf(doctores)) {
            remove(doctores);
        }
        if (isAncestorOf(pacientes)) {
            remove(pacientes);
        }
        revalidate();
        repaint();
    }


    public void tabCitas() {
        citas = new PnlCitas();
        add(citas);
        revalidate();
    }
    public void tabTratamientos() {
        tratamientos = new PnlTratamientos();
        add(tratamientos);
        revalidate();
    }
    public void tabDoctores() {
        doctores = new PnlDoctores();
        add(doctores);
        revalidate();
    }
    public void tabPacientes() {
        pacientes = new PnlPacientes();
        add(pacientes);
        revalidate();
    }

}
