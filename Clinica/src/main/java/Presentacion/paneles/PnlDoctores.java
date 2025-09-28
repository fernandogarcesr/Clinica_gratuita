package Clinica.src.main.java.Presentacion.paneles;

import Presentacion.dialogs.registro.DlgRegistrarDoctor;
import Presentacion.paneles.elementos.PnlElementoDoctor;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PnlDoctores extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;
    PnlDoctores pnlDoctores = this;

    //Título
    ContainerPanel titulo = new ContainerPanel(style.frameX, 40, Color.RED, testeoColor);
    CustomLabel lblTitulo = new CustomLabel("           Doctores", 24);

    //Columnas
    ContainerPanel columnas = new ContainerPanel(style.frameX, 50, Color.BLUE, false);
    CustomLabel lblNombre = new CustomLabel("Nombre", style.letraSize);
    CustomLabel lblEspecialidad = new CustomLabel("Especialidad", style.letraSize);
    CustomLabel lblCorreo = new CustomLabel("Correo", style.letraSize);
    CustomLabel lblTelefono = new CustomLabel("Teléfono", style.letraSize);

    //Botón
    CustomButton btnRegistrarDoctor = new CustomButton("Registrar nuevo doctor");

    //Espaciadores
    Espaciador espaciov1 = new Espaciador(10, 30);

    int espacioX = 100;
    Espaciador espacioh1 = new Espaciador(300, 10);
    Espaciador espacioh2 = new Espaciador(10, 10);
    Espaciador espacioh3 = new Espaciador(80, 10);


    public PnlDoctores() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(espaciov1);

        //Encabezado
        titulo.setLayout(new BorderLayout());
        titulo.add(lblTitulo, BorderLayout.WEST);
        add(titulo);

        columnas.setLayout(new GridLayout(1,6));

        columnas.add(espacioh1);
        columnas.add(lblNombre);
        columnas.add(lblEspecialidad);
        columnas.add(lblCorreo);
        columnas.add(lblTelefono);

        add(columnas);


        //----------PLACEHOLDER HARCODEADO-----------
        PnlElementoDoctor ejemplo = new PnlElementoDoctor(pnlDoctores);
        add(ejemplo);
        //----------FIN DE PLACEHOLDER----------


        //----------LÓGICA AQUÍ----------
        /*

        for(int i = 0; i < tratamientos.length; i++){
            PnlElementoDoctor elementoDoctor = new PnlElementoDoctor(pnlDoctores, doctor);
            add(elementoDoctor)
        }

         */
        //----------FIN DE LÓGICA----------


        btnRegistrarDoctor.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DlgRegistrarDoctor registrar = new DlgRegistrarDoctor(null, pnlDoctores);
                registrar.setVisible(true);
            }
        });
        add(btnRegistrarDoctor);

        setOpaque(false);
        setVisible(true);
    }

    public void refresh() {

        System.out.println("refresh pnlDoctores");

        revalidate();
        repaint();
    }

}
