package Presentacion.paneles;

import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlFavoritos extends JPanel {

    boolean testeoColor = false;

    Style style = new Style();
    PnlPerfil pnlPerfil;

    //Atributos de lógica - cambiar por getters
    String nombreUsuario = "Usuario";

    //Botón volver
    ContainerPanel contenedorVolver = new ContainerPanel(style.frameX, 60, Color.CYAN, testeoColor);
    TitleButton btnVolver = new TitleButton("Volver", 200, 50);

    //Título
    ContainerPanel encabezado = new ContainerPanel(style.frameX, 170, Color.PINK, testeoColor);
    CustomLabel lblUsuario = new CustomLabel(nombreUsuario, 32);
    ContainerPanel contenedor1 = new ContainerPanel(style.frameX, 40, Color.RED, testeoColor);
    CustomLabel lblTitulo = new CustomLabel("Favoritos", 48);
    ContainerPanel contenedor2 = new ContainerPanel(style.frameX, 60, Color.PINK, testeoColor);

    //Menú
    ContainerPanel contenedorMenu = new ContainerPanel(style.frameX, 70, Color.BLUE, testeoColor);
        //Botones
    TitleButton btnArtistas = new TitleButton("Artistas", 130, 40);
    TitleButton btnAlbumes = new TitleButton("Albumes", 130, 40);
    TitleButton btnCanciones = new TitleButton("Canciones", 130, 40);

    //Línea divisoria
    ContainerPanel linea = new ContainerPanel(610, 5, style.lineaGris, testeoColor);

    //Espaciadores
    Espaciador espaciov1 = new Espaciador(style.frameX, 25);
    Espaciador espaciov2 = new Espaciador(style.frameX, 25);
    Espaciador espacioh1 = new Espaciador(50, 20);

    //Declaración de paneles
    PnlCitas artistas;
    PnlTratamientos albumes;
    PnlDoctores canciones;



    public PnlFavoritos(PnlPerfil pnlPerfil) {

        this.pnlPerfil = pnlPerfil;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setSize(style.frameX, style.frameY);

        //Botón volver
        contenedorVolver.setLayout(new BorderLayout());
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                volverAPerfil();
            }
        });
        contenedorVolver.add(btnVolver, BorderLayout.WEST);
        encabezado.add(contenedorVolver);


        //Encabezado
        encabezado.setLayout(new BoxLayout(encabezado, BoxLayout.Y_AXIS));
        encabezado.add(contenedorVolver);
        contenedor1.add(lblUsuario);
        encabezado.add(contenedor1);
        contenedor2.add(lblTitulo);
        encabezado.add(contenedor2);

        add(encabezado);

        //add(espaciov2);

        //Menú
        add(contenedorMenu);
        btnArtistas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarPanelActual();
                tabArtistas();
            }
        });
        contenedorMenu.add(btnArtistas);
        btnAlbumes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarPanelActual();
                tabAlbumes();
            }
        });
        contenedorMenu.add(btnAlbumes);
        btnCanciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                limpiarPanelActual();
                tabCanciones();
            }
        });
        contenedorMenu.add(btnCanciones);

        //Línea divisoria
        add(linea);

        artistas = new PnlCitas();
        //falta agregar la filtración a estrella
        add(artistas);


        setVisible(true);

    }


    public void volverAPerfil() {
        setVisible(false);
        pnlPerfil.volverAPerfil();
    }

    public void mostracionPanelActual(boolean mostrar) {
        if (isAncestorOf(artistas)) {
            artistas.setVisible(mostrar);
        }
        if (isAncestorOf(albumes)) {
            albumes.setVisible(mostrar);
        }
        if (isAncestorOf(canciones)) {
            canciones.setVisible(mostrar);
        }
        revalidate();
        repaint();
    }

    public void limpiarPanelActual(){
        if (isAncestorOf(artistas)) {
            remove(artistas);
        }
        if (isAncestorOf(albumes)) {
            remove(albumes);
        }
        if (isAncestorOf(canciones)) {
            remove(canciones);
        }
        revalidate();
        repaint();
    }


    //Construir como panel normal, pero solo buscando favoritos

    public void tabArtistas() {
        artistas = new PnlCitas();
        add(artistas);
        revalidate();
    }
    public void tabAlbumes() {
        albumes = new PnlTratamientos();
        add(albumes);
        revalidate();
    }
    public void tabCanciones() {
        canciones = new PnlDoctores();
        add(canciones);
        revalidate();
    }

}
