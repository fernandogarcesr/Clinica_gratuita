package Presentacion.paneles;

import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlRestricciones extends JPanel {

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
    CustomLabel lblTitulo = new CustomLabel("Restricciones", 48);
    ContainerPanel contenedor2 = new ContainerPanel(style.frameX, 60, Color.PINK, testeoColor);

    //Contenido
    ContainerPanel contenedorContenido = new ContainerPanel(style.frameX - 200, 600, Color.CYAN, testeoColor);
    CustomLabel lblRestriccion = new CustomLabel("Restricción", 24);
    CustomLabel lblGenero = new CustomLabel("Género", 24);

    //Botón aplicar
    ContainerPanel contenedorBtnGuardar = new ContainerPanel(200, 60, Color.RED, testeoColor);
    CustomButton btnGuardarCambios = new CustomButton("Guardar cambios", 1, 200, 50);

    //Línea divisoria
    ContainerPanel linea = new ContainerPanel(610, 5, style.lineaGris, testeoColor);


    //Espaciadores
    Espaciador espaciov1 = new Espaciador(style.frameX, 25);
    Espaciador espaciov2 = new Espaciador(style.frameX, 25);
    Espaciador espacioh1 = new Espaciador(50, 20);



    public PnlRestricciones(PnlPerfil pnlPerfil) {

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
        encabezado.add(linea);

        add(encabezado);

        //add(espaciov2);

        contenedorContenido.setLayout(new GridLayout(0, 2));
        contenedorContenido.add(lblRestriccion);
        contenedorContenido.add(lblGenero);

        add(contenedorContenido);

        btnGuardarCambios.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                guardarCambios();
            }
        });
        contenedorBtnGuardar.add(btnGuardarCambios);
        add(contenedorBtnGuardar);


        setVisible(true);

    }


    public void volverAPerfil() {
        setVisible(false);
        pnlPerfil.volverAPerfil();
    }

    public void guardarCambios() {
        //if seleccionado es compartido con favoritos
        //soltar dialog de advertencia
        //guardar
    }

}
