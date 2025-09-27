package Presentacion.paneles;

import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlPerfil extends JPanel {

    //Meter usuario pa administrar la lógica

    boolean testeoColor = false;

    PnlMenu menu;
   // PnlFavoritos pnlFavoritos = new PnlFavoritos(this);
    //PnlRestricciones pnlRestricciones = new PnlRestricciones(this);

    Style style = new Style();
    Dimension dimension = new Dimension(style.frameX, style.frameY);

    //Botón volver
    ContainerPanel contenedorVolver = new ContainerPanel(style.frameX, 60, Color.CYAN, testeoColor);
    //placeholder flechita
    TitleButton btnVolver = new TitleButton("Volver", 200, 50);
    //CustomLabel volverTemporal = new CustomLabel("volver", 18);

    //Encabezado
    ContainerPanel encabezado = new ContainerPanel(style.frameX, 180, Color.YELLOW, testeoColor);
    CustomLabel imagen = new CustomLabel("PlaHo", 32);

    ContainerPanel subencabezado1 = new ContainerPanel(400, 150, Color.PINK, testeoColor);
    CustomLabel lblUsuario = new CustomLabel("Usuario", 42);
    ContainerPanel contenedor0 = new ContainerPanel(400, 50, Color.PINK, testeoColor);

    ContainerPanel subencabezado2 = new ContainerPanel(400, 100, Color.GREEN, testeoColor);
    CustomLabel numArtistasFav = new CustomLabel("Artistas", 18);
    CustomLabel numAlbumesFav = new CustomLabel("Álbumes", 18);
    CustomLabel numCancionesFav = new CustomLabel("Canciones", 18);
    CustomLabel numRestricciones = new CustomLabel("Restricciones", 18);

    //Líneas
    ContainerPanel linea1 = new ContainerPanel(style.frameX, 5, style.lineaGris, true);
    ContainerPanel linea2 = new ContainerPanel(style.frameX, 5, style.lineaGris, true);

    //Menú
    ContainerPanel contenedorMenu = new ContainerPanel(style.frameX, 500, Color.MAGENTA, testeoColor);

    int btnX = 300;
    int btnY = 60;
    CustomLabel lblContenido = new CustomLabel("Contenido", 24); //Cambiar fuente
    TitleButton btnFavoritos = new TitleButton("Ver lista de favoritos", btnX, btnY);
    TitleButton btnRestricciones = new TitleButton("Adminsitrar restricciones", btnX, btnY);

    JPanel linea = new JPanel();

    CustomLabel lblCuenta = new CustomLabel("Cuenta", 24); //Cambiar fuente
    TitleButton btnCambiarNombre = new TitleButton("Cambiar nombre de usuario", btnX, btnY);
    TitleButton btnCammbiarContra = new TitleButton("Cambiar contraseña", btnX, btnY);
    TitleButton btnCambiarImagen = new TitleButton("CambiarImagen", btnX, btnY);

    //Containers pa ver si magicamente se arregla el menu
    int menuX = 300, menuY = 70;
    ContainerPanel contenedor1 = new ContainerPanel(menuX + 60, menuY -20, Color.RED, testeoColor);
    ContainerPanel contenedor2 = new ContainerPanel(menuX, menuY, Color.RED, testeoColor);
    ContainerPanel contenedor3 = new ContainerPanel(menuX, menuY, Color.RED, testeoColor);

    ContainerPanel contenedor4 = new ContainerPanel(menuX + 60, menuY - 20, Color.RED, testeoColor);
    ContainerPanel contenedor5 = new ContainerPanel(menuX, menuY, Color.RED, testeoColor);
    ContainerPanel contenedor6 = new ContainerPanel(menuX, menuY, Color.RED, testeoColor);
    ContainerPanel contenedor7 = new ContainerPanel(menuX, menuY, Color.RED, testeoColor);

    //Espacciadores
    Espaciador espaciov1 = new Espaciador(10, 80);
    Espaciador espaciov2 = new Espaciador(10, 80);


    public PnlPerfil(PnlMenu menu) {

        this.menu = menu;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //setLayout(new GridLayout(0,1));
        //setLayout(new BorderLayout());

        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setPreferredSize(dimension);
        setOpaque(false);

        //Botón volver
        contenedorVolver.setLayout(new BorderLayout());
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                salirPerfil();
                //menu.setVisible(true);
            }
        });
        contenedorVolver.add(btnVolver, BorderLayout.WEST);
        //contenedorVolver.add(btnVolver);

        //add(contenedorVolver, BorderLayout.NORTH);
        add(contenedorVolver);


        //Encabezado
        encabezado.add(imagen);
            //Subencabezado 2 (stats del usuario)
        subencabezado2.setLayout(new GridLayout(2, 2));
        subencabezado2.add(numArtistasFav);
        subencabezado2.add(numAlbumesFav);
        subencabezado2.add(numCancionesFav);
        subencabezado2.add(numRestricciones);

            //Subencabezado 1 (contenedor derecha encabezado)
        subencabezado1.setLayout(new BoxLayout(subencabezado1, BoxLayout.Y_AXIS));
        lblUsuario.setHorizontalAlignment(SwingConstants.LEFT);
        contenedor0.setLayout(new FlowLayout(FlowLayout.LEFT));
        contenedor0.add(lblUsuario);
        subencabezado1.add(contenedor0);

        subencabezado1.add(subencabezado2);
        encabezado.add(subencabezado1);

        //add(encabezado, BorderLayout.CENTER);
        add(encabezado);


        //Contenido
        contenedorMenu.setLayout(new BoxLayout(contenedorMenu, BoxLayout.Y_AXIS));
        //contenedorMenu.setLayout(new GridLayout(0,1));
        contenedorMenu.add(linea1);

        contenedorMenu.add(espaciov1);

        contenedor1.add(lblContenido);

        btnFavoritos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verFavoritos();
            }
        });
        btnRestricciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                verRestricciones();
            }
        });

        contenedor1.setLayout(new FlowLayout(FlowLayout.LEFT));
        contenedor2.add(btnFavoritos);
        contenedor3.add(btnRestricciones);

        contenedorMenu.add(contenedor1);
        contenedorMenu.add(contenedor2);
        contenedorMenu.add(contenedor3);

        contenedorMenu.add(linea2);
        contenedorMenu.add(espaciov2);

        btnCambiarNombre.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cambiarNombre();
            }
        });
        btnCammbiarContra.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cambiarContra();
            }
        });
        btnCambiarImagen.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cambiarImagen();
            }
        });
        contenedor4.add(lblCuenta);
        contenedor4.setLayout(new FlowLayout(FlowLayout.LEFT));
        contenedor5.add(btnCambiarNombre);
        contenedor6.add(btnCammbiarContra);
        contenedor7.add(btnCambiarImagen);

        contenedorMenu.add(contenedor4);
        contenedorMenu.add(contenedor5);
        contenedorMenu.add(contenedor6);
        contenedorMenu.add(contenedor7);

        add(contenedorMenu);

        //add(pnlFavoritos);
        //pnlFavoritos.setVisible(false);
        //add(pnlRestricciones);
        //pnlRestricciones.setVisible(false);


        setVisible(true);

    }



    public void salirPerfil() {
        this.setVisible(false);
        menu.volverAInicio();
    }

    public void verFavoritos() {
        visibilidadElementos(false);
        //add(pnlFavoritos);
        //pnlFavoritos.setVisible(true);
    }

    public void verRestricciones() {
        visibilidadElementos(false);
        //pnlRestricciones.setVisible(true);
    }

    public void cambiarNombre() {

    }

    public void cambiarContra() {

    }

    public void cambiarImagen() {

    }


    //-----

    public void volverAPerfil() {
        //pnlFavoritos.setVisible(false);
        visibilidadElementos(true);
    }

    public void visibilidadElementos(Boolean visible){

        contenedorVolver.setVisible(visible);
        encabezado.setVisible(visible);
        contenedorMenu.setVisible(visible);

        revalidate();
        repaint();
    }
}
