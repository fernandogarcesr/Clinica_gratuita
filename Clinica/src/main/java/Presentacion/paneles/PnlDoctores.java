package Presentacion.paneles;

import Presentacion.paneles.elementos.PnlElementoDoctor;
import Presentacion.styles.*;

import javax.swing.*;
import java.awt.*;

public class PnlDoctores extends JPanel {

    Style style = new Style();
    boolean testeoColor = false;

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


        //add containerpanel con un for que liste las doctores

        //Campo de pruebas
        PnlElementoDoctor ejemplo = new PnlElementoDoctor();
        add(ejemplo);

        //----------LÓGICA AQUÍ----------
        //cambiar este int por el array.length del control con los dtos
        /*int x = 3; //int de ejemplo, cambiar
        for(int i = 0; i < x; i++){
            //PnlElementoDoctor elementoDoctor = new PnlElementoDoctor(doctor);
            //add(elementoDoctor)
        }
         */

        add(btnRegistrarDoctor);

        setOpaque(false);
        setVisible(true);
    }

}
